package com.lsj.core.spring.grpc.client.test;

import com.lsj.core.grpc.server.test.LsjReply;
import com.lsj.core.grpc.server.test.LsjRequest;
import com.lsj.core.grpc.server.test.LsjTestGrpc;
import com.lsj.core.spring.grpc.client.discovery.stub.LsjGrpcStubBaseProxy;

/**
 * @author lishangjian
 * @date 2024/4/9 11:31
 */
public class LsjTestBlockingStubProxy extends LsjGrpcStubBaseProxy<LsjTestGrpc.LsjTestBlockingStub> {

    protected LsjTestBlockingStubProxy(String serviceName, String componentId) {
        super(serviceName, componentId);
    }

    @Override
    protected Class<LsjTestGrpc.LsjTestBlockingStub> getStubClass() {
        return LsjTestGrpc.LsjTestBlockingStub.class;
    }

    public String doTest() {
        LsjRequest request = LsjRequest.newBuilder().build();
        LsjReply reply = getStub().doTest(request);
        return reply.getMessage();
    }
}
