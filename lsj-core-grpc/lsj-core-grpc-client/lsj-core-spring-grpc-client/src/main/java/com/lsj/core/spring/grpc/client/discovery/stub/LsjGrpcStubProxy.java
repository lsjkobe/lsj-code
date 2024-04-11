package com.lsj.core.spring.grpc.client.discovery.stub;

import io.grpc.stub.AbstractBlockingStub;

/**
 * @author lishangjian
 * @date 2024/4/9 11:31
 */
public interface LsjGrpcStubProxy<T extends AbstractBlockingStub<T>> {

    T getStub();
}
