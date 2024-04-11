package com.lsj.core.spring.grpc.client.discovery.stub.handler;

import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import io.grpc.stub.AbstractBlockingStub;

/**
 * @author lishangjian
 * @date 2024/4/11 下午5:01
 */
public class LsjGRpcStubClientCommonHandler<T extends AbstractBlockingStub<T>>
        extends LsjGRpcStubClientBaseHandler<T> {

    @Override
    public EDiscoveryType discoveryType() {
        //通用的
        return null;
    }
}
