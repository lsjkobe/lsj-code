package com.lsj.core.spring.grpc.client.discovery;

import com.lsj.core.spring.grpc.client.discovery.loadbalancer.LsjGRpcLoadBalancerClientFactory;
import com.lsj.core.spring.grpc.client.discovery.loadbalancer.LsjGRpcServiceInstanceChooser;
import com.lsj.core.spring.grpc.client.discovery.stub.LsjGRpcStubClientFactory;
import com.lsj.core.spring.grpc.client.discovery.stub.handler.ILsjGRpcStubClientHandler;
import com.lsj.core.spring.grpc.client.model.DiscoveryBuildStubParam;
import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import com.lsj.core.spring.grpc.core.model.LsjGRpcBaseServiceInstance;
import com.lsj.core.spring.grpc.core.properties.LsjGRpcProperties;
import io.grpc.stub.AbstractBlockingStub;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lishangjian
 * @date 2024/4/9 14:24
 */
public abstract class LsjGRpcClientBaseDiscoveryHandler implements LsjGRpcClientDiscoveryHandler {

    private static final Logger log = LoggerFactory.getLogger(LsjGRpcClientBaseDiscoveryHandler.class);
    private final LsjGRpcLoadBalancerClientFactory loadBalancerClientFactory;
    private final LsjGRpcStubClientFactory stubClientFactory;

    private final LsjGRpcProperties gRpcProperties;

    public LsjGRpcClientBaseDiscoveryHandler(
            LsjGRpcLoadBalancerClientFactory loadBalancerClientFactory,
            LsjGRpcStubClientFactory stubClientFactory,
            LsjGRpcProperties gRpcProperties) {
        this.loadBalancerClientFactory = loadBalancerClientFactory;
        this.stubClientFactory = stubClientFactory;
        this.gRpcProperties = gRpcProperties;
    }

    @Override
    public <T extends AbstractBlockingStub<T>> T buildStub(DiscoveryBuildStubParam param, Class<T> stubClass) {
        LsjGRpcServiceInstanceChooser<?> serviceInstanceChooser =
                loadBalancerClientFactory.getInstance(param, gRpcProperties.getClient().getDiscoveryType());
        LsjGRpcBaseServiceInstance serviceInstance = serviceInstanceChooser.choose(param);
        if (serviceInstance == null) {
            log.error("[][] [{}]/[{}] 服务不存在", param.getServiceName(), param.getComponentId());
            throw new RuntimeException("GRPC服务提供者不存在[" + param.buildServiceId() + "]");
        }
        if (StringUtils.isBlank(serviceInstance.getServiceId())) {
            serviceInstance.setServiceId(param.buildServiceId());
        }
        ILsjGRpcStubClientHandler<T> stubClientHandler =
                stubClientFactory.getInstance(gRpcProperties.getClient().getDiscoveryType());
        return stubClientHandler.handle(serviceInstance, stubClass);
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
