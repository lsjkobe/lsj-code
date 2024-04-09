package com.lsj.core.spring.grpc.client.test;

import io.grpc.stub.AbstractBlockingStub;

/**
 * @author lishangjian
 * @date 2024/4/9 11:31
 */
public interface LsjGrpcStubBaseProxy<T extends AbstractBlockingStub<T>> {

    T getStub();
}
