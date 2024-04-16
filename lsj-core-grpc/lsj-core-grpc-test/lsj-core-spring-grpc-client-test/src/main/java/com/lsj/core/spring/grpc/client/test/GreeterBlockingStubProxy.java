package com.lsj.core.spring.grpc.client.test;

import com.lsj.core.grpc.server.test.GreeterGrpc;
import com.lsj.core.grpc.server.test.HelloReply;
import com.lsj.core.grpc.server.test.HelloRequest;
import com.lsj.core.spring.grpc.client.discovery.stub.LsjGrpcStubBaseProxy;

/**
 * @author lishangjian
 * @date 2024/4/9 11:31
 */
public class GreeterBlockingStubProxy extends LsjGrpcStubBaseProxy<GreeterGrpc.GreeterBlockingStub> {

    protected GreeterBlockingStubProxy(String serviceName, String componentId) {
        super(serviceName, componentId);
    }

    @Override
    protected Class<GreeterGrpc.GreeterBlockingStub> getStubClass() {
        return GreeterGrpc.GreeterBlockingStub.class;
    }


    public String sayHello() {
        HelloRequest request = HelloRequest.newBuilder().build();
        HelloReply reply = getStub().sayHello(request);
        return reply.getMessage();
    }
}
