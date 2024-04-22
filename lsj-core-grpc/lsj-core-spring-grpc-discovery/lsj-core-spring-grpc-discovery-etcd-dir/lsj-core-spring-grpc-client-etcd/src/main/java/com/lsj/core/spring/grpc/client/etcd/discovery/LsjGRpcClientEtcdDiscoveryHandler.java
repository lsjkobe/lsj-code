package com.lsj.core.spring.grpc.client.etcd.discovery;

import com.lsj.core.spring.grpc.client.discovery.LsjGRpcClientBaseDiscoveryHandler;
import com.lsj.core.spring.grpc.client.discovery.loadbalancer.LsjGRpcLoadBalancerClientFactory;
import com.lsj.core.spring.grpc.client.discovery.stub.LsjGRpcStubClientFactory;
import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import com.lsj.core.spring.grpc.core.properties.LsjGRpcProperties;

/**
 * @author lishangjian
 * @date 2024/4/10 上午11:56
 */
public class LsjGRpcClientEtcdDiscoveryHandler extends LsjGRpcClientBaseDiscoveryHandler {

    public LsjGRpcClientEtcdDiscoveryHandler(
            LsjGRpcLoadBalancerClientFactory loadBalancerClientFactory,
            LsjGRpcStubClientFactory clientFactory,
            LsjGRpcProperties gRpcProperties) {
        super(loadBalancerClientFactory, clientFactory, gRpcProperties);
    }

    /**
     * 类型.
     *
     * @return .
     */
    @Override
    public EDiscoveryType type() {
        return EDiscoveryType.ETCD;
    }
}
