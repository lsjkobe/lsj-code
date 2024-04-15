package com.lsj.core.spring.grpc.client.discovery.loadbalancer.handler;

import com.lsj.core.spring.grpc.core.enums.ELoadBalancerType;
import com.lsj.core.spring.grpc.core.enums.LoadBalancerType;
import com.lsj.core.spring.grpc.core.model.LsjGRpcBaseServiceInstance;
import com.lsj.core.spring.grpc.core.model.LsjGRpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lishangjian
 * @date 2024/4/10 下午5:38
 */
@Slf4j
public class LsjGRpcRoundLoadBalancerHandler
        extends LsjGRpcBaseLoadBalancerHandler<LsjGRpcBaseServiceInstance> {

    final Integer position;

    final ConcurrentMap<String, AtomicInteger> positionMap;

    public LsjGRpcRoundLoadBalancerHandler() {
        this(new Random().nextInt(1000));
    }

    public LsjGRpcRoundLoadBalancerHandler(int seedPosition) {
        this.position = seedPosition;
        this.positionMap = new ConcurrentHashMap<>();
    }

    @Override
    public LsjGRpcResponse<LsjGRpcBaseServiceInstance> choose(
            String serviceId, List<LsjGRpcBaseServiceInstance> instanceList) {
        return getInstanceResponse(serviceId, instanceList);
    }


    @Override
    public LoadBalancerType loadBalancerType() {
        return ELoadBalancerType.ROUND;
    }

    private LsjGRpcResponse<LsjGRpcBaseServiceInstance> getInstanceResponse(
            String serviceId, List<LsjGRpcBaseServiceInstance> instances) {
        if (instances.isEmpty()) {
            return LsjGRpcResponse.buildEmpty();
        }
        // Do not move position when there is only 1 instance, especially some suppliers
        // have already filtered instances
        if (instances.size() == 1) {
            return LsjGRpcResponse.buildDefault(instances.getFirst());
        }
        // Ignore the sign bit, this allows pos to loop sequentially from 0 to
        // Integer.MAX_VALUE
        AtomicInteger positionAtomic = positionMap.computeIfAbsent(serviceId, k -> new AtomicInteger(position));
        int pos = positionAtomic.incrementAndGet() & Integer.MAX_VALUE;
        LsjGRpcBaseServiceInstance instance = instances.get(pos % instances.size());
        return LsjGRpcResponse.buildDefault(instance);
    }
}
