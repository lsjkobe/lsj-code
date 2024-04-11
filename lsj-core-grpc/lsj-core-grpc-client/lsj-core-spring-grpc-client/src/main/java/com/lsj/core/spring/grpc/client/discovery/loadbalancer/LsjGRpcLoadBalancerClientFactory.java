package com.lsj.core.spring.grpc.client.discovery.loadbalancer;

import com.lsj.core.spring.grpc.client.model.DiscoveryBuildStubParam;
import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import com.lsj.core.spring.grpc.core.model.LsjGRpcBaseServiceInstance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lishangjian
 * @date 2024/4/10 下午2:42
 */
public class LsjGRpcLoadBalancerClientFactory {

    private final Map<EDiscoveryType, LsjGRpcServiceInstanceChooser<?>> serviceInstanceChooserMap;

    public LsjGRpcLoadBalancerClientFactory(List<LsjGRpcServiceInstanceChooser<?>> handlerList) {
        serviceInstanceChooserMap = new HashMap<>();
        for (LsjGRpcServiceInstanceChooser<?> handler : handlerList) {
            serviceInstanceChooserMap.put(handler.discoveryType(), handler);
        }
    }

    public <T extends LsjGRpcBaseServiceInstance> LsjGRpcServiceInstanceChooser<T> getInstance(
            DiscoveryBuildStubParam param, EDiscoveryType discoveryType) {
        LsjGRpcServiceInstanceChooser<?> handler = serviceInstanceChooserMap.get(discoveryType);
        return (LsjGRpcServiceInstanceChooser<T>) handler;
    }

}
