package com.lsj.core.spring.grpc.client.discovery.stub;

import com.lsj.core.spring.grpc.client.discovery.LsjGRpcClientDiscoveryHandler;
import com.lsj.core.spring.grpc.client.discovery.LsjGRpcClientDiscoveryManager;
import com.lsj.core.spring.grpc.client.model.DiscoveryBuildStubParam;
import io.grpc.stub.AbstractBlockingStub;
import lombok.Getter;

/**
 * @author lishangjian
 * @date 2024/4/11 下午2:09
 */
@Getter
public abstract class LsjGrpcStubBaseProxy<T extends AbstractBlockingStub<T>> implements LsjGrpcStubProxy<T> {

    protected final String serviceName;

    protected final String componentId;

    protected LsjGrpcStubBaseProxy(String serviceName, String componentId) {
        this.serviceName = serviceName;
        this.componentId = componentId;
    }

    @Override
    public T getStub() {
        LsjGRpcClientDiscoveryHandler discoveryHandler =
                LsjGRpcClientDiscoveryManager.getDiscoveryHandler();
        DiscoveryBuildStubParam param = new DiscoveryBuildStubParam();
        param.setComponentId(componentId);
        param.setServiceName(serviceName);
        return discoveryHandler.buildStub(param, getStubClass());
    }

    protected abstract Class<T> getStubClass();
}
