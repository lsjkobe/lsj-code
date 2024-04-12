package com.lsj.core.spring.grpc.client.discovery.stub;

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
}
