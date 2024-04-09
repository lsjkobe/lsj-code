package com.lsj.core.spring.grpc.client.discovery;

import com.lsj.core.spring.grpc.client.model.DiscoveryBuildStubParam;
import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import io.grpc.stub.AbstractBlockingStub;

/**
 * @author lishangjian
 * @date 2024/4/9 14:24
 */
public interface LsjGRpcClientDiscoveryHandler {

    <T extends AbstractBlockingStub<T>> T buildStub(DiscoveryBuildStubParam param);

    /**
     * 类型.
     * @return .
     */
    EDiscoveryType type();
}
