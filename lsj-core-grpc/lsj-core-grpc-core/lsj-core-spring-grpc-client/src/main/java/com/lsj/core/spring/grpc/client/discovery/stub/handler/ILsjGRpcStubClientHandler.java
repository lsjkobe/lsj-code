package com.lsj.core.spring.grpc.client.discovery.stub.handler;

import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import com.lsj.core.spring.grpc.core.model.LsjGRpcBaseServiceInstance;
import io.grpc.stub.AbstractBlockingStub;

/**
 * @author lishangjian
 * @date 2024/4/11 下午4:53
 */
public interface ILsjGRpcStubClientHandler<T extends AbstractBlockingStub<T>> {

    T handle(LsjGRpcBaseServiceInstance serviceInstance, Class<T> stubClass);

    EDiscoveryType discoveryType();
}
