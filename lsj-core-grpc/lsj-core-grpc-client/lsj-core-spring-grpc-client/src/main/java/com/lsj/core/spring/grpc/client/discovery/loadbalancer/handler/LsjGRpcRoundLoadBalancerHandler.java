package com.lsj.core.spring.grpc.client.discovery.loadbalancer.handler;

import com.lsj.core.spring.grpc.core.enums.ELoadBalancerType;
import com.lsj.core.spring.grpc.core.enums.LoadBalancerType;
import com.lsj.core.spring.grpc.core.model.LsjGRpcResponse;
import com.lsj.core.spring.grpc.core.model.LsjGRpcServiceInstance;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lishangjian
 * @date 2024/4/10 下午5:38
 */
@Slf4j
public class LsjGRpcRoundLoadBalancerHandler
        extends LsjGRpcBaseLoadBalancerHandler<LsjGRpcServiceInstance> {

    final AtomicInteger position;

    public LsjGRpcRoundLoadBalancerHandler(String serviceId) {
        this(serviceId, new Random().nextInt(1000));
    }

    public LsjGRpcRoundLoadBalancerHandler(String serviceId, int seedPosition) {
        super(serviceId);
        this.position = new AtomicInteger(seedPosition);
    }

    @Override
    public LsjGRpcResponse<LsjGRpcServiceInstance> choose(List<LsjGRpcServiceInstance> instanceList) {
        return null;
    }


    @Override
    public LoadBalancerType loadBalancerType() {
        return ELoadBalancerType.ROUND;
    }

    private LsjGRpcResponse<LsjGRpcServiceInstance> getInstanceResponse(List<LsjGRpcServiceInstance> instances) {
        if (instances.isEmpty()) {
            if (log.isWarnEnabled()) {
                log.warn("No servers available for service: " + serviceId);
            }
            return LsjGRpcResponse.buildEmpty();
        }
        // Do not move position when there is only 1 instance, especially some suppliers
        // have already filtered instances
        if (instances.size() == 1) {
            return LsjGRpcResponse.buildDefault(instances.getFirst());
        }
        // Ignore the sign bit, this allows pos to loop sequentially from 0 to
        // Integer.MAX_VALUE
        int pos = this.position.incrementAndGet() & Integer.MAX_VALUE;
        LsjGRpcServiceInstance instance = instances.get(pos % instances.size());
        return LsjGRpcResponse.buildDefault(instance);
    }
}
