package com.lsj.core.spring.grpc.client.discovery.loadbalancer.handler;

import com.lsj.core.spring.grpc.client.discovery.loadbalancer.LsjGRpcLoadBalancerHandler;
import com.lsj.core.spring.grpc.client.model.DiscoveryBuildStubParam;
import com.lsj.core.spring.grpc.core.enums.ELoadBalancerType;
import com.lsj.core.spring.grpc.core.enums.LoadBalancerType;
import com.lsj.core.spring.grpc.core.model.LsjGRpcBaseServiceInstance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lishangjian
 * @date 2024/4/10 下午2:42
 */
public class LsjGRpcLoadBalancerFactory {

    private final Map<LoadBalancerType, LsjGRpcLoadBalancerHandler<?>> loadBalancerHandlerMap;

    public LsjGRpcLoadBalancerFactory(List<LsjGRpcLoadBalancerHandler<?>> handlerList) {
        loadBalancerHandlerMap = new HashMap<>();
        for (LsjGRpcLoadBalancerHandler<?> handler : handlerList) {
            loadBalancerHandlerMap.put(handler.loadBalancerType(), handler);
        }
    }

    public <T extends LsjGRpcBaseServiceInstance> LsjGRpcLoadBalancerHandler<T> getInstance(
            DiscoveryBuildStubParam param, LoadBalancerType loadBalancerType) {
        LsjGRpcLoadBalancerHandler<?> handler =  loadBalancerHandlerMap.get(loadBalancerType);
        return (LsjGRpcLoadBalancerHandler<T>) handler;
    }

    public <T extends LsjGRpcBaseServiceInstance> LsjGRpcLoadBalancerHandler<T> getInstance(
            DiscoveryBuildStubParam param) {
        return getInstance(param, ELoadBalancerType.ROUND);
    }
}
