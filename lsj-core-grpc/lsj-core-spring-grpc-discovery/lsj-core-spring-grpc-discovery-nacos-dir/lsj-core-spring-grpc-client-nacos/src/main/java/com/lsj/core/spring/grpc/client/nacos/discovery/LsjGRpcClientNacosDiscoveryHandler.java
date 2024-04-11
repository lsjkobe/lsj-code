package com.lsj.core.spring.grpc.client.nacos.discovery;

import com.lsj.core.spring.grpc.client.discovery.LsjGRpcClientBaseDiscoveryHandler;
import com.lsj.core.spring.grpc.client.discovery.loadbalancer.LsjGRpcLoadBalancerClientFactory;
import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import com.lsj.core.spring.grpc.core.properties.LsjGRpcProperties;

/**
 * @author lishangjian
 * @date 2024/4/10 上午11:56
 */
public class LsjGRpcClientNacosDiscoveryHandler extends LsjGRpcClientBaseDiscoveryHandler {

    public LsjGRpcClientNacosDiscoveryHandler(
            LsjGRpcLoadBalancerClientFactory loadBalancerClientFactory,
            LsjGRpcProperties gRpcProperties) {
        super(loadBalancerClientFactory, gRpcProperties);
    }

    /**
     * 类型.
     *
     * @return .
     */
    @Override
    public EDiscoveryType type() {
        return EDiscoveryType.NACOS;
    }
}
