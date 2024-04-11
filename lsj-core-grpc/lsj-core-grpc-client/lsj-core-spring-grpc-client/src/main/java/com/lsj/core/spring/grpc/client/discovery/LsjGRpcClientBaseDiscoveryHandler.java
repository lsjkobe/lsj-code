package com.lsj.core.spring.grpc.client.discovery;

import com.lsj.core.spring.grpc.client.discovery.loadbalancer.LsjGRpcLoadBalancerClientFactory;
import com.lsj.core.spring.grpc.client.discovery.loadbalancer.LsjGRpcServiceInstanceChooser;
import com.lsj.core.spring.grpc.client.model.DiscoveryBuildStubParam;
import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import com.lsj.core.spring.grpc.core.model.LsjGRpcServiceInstance;
import com.lsj.core.spring.grpc.core.properties.LsjGRpcProperties;
import io.grpc.stub.AbstractBlockingStub;

/**
 * @author lishangjian
 * @date 2024/4/9 14:24
 */
public abstract class LsjGRpcClientBaseDiscoveryHandler implements LsjGRpcClientDiscoveryHandler {

    private final LsjGRpcLoadBalancerClientFactory loadBalancerClientFactory;

    private final LsjGRpcProperties gRpcProperties;

    public LsjGRpcClientBaseDiscoveryHandler(
            LsjGRpcLoadBalancerClientFactory loadBalancerClientFactory,
            LsjGRpcProperties gRpcProperties) {
        this.loadBalancerClientFactory = loadBalancerClientFactory;
        this.gRpcProperties = gRpcProperties;
    }

    @Override
    public <T extends AbstractBlockingStub<T>> T buildStub(DiscoveryBuildStubParam param) {
        LsjGRpcServiceInstanceChooser<?> serviceInstanceChooser =
                loadBalancerClientFactory.getInstance(param, gRpcProperties.getClient().getDiscoveryType());
        LsjGRpcServiceInstance serviceInstance = serviceInstanceChooser.choose(param);
        return null;
    }

    /**
     * 类型.
     *
     * @return .
     */
    @Override
    public EDiscoveryType type() {
        return null;
    }
}
