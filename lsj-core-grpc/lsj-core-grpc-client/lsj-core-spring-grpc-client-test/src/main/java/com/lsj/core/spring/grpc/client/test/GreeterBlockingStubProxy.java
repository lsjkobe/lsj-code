package com.lsj.core.spring.grpc.client.test;

import com.lsj.core.grpc.server.test.GreeterGrpc;
import com.lsj.core.grpc.server.test.HelloReply;
import com.lsj.core.grpc.server.test.HelloRequest;

/**
 * @author lishangjian
 * @date 2024/4/9 11:31
 */
public class GreeterBlockingStubProxy implements LsjGrpcStubBaseProxy<GreeterGrpc.GreeterBlockingStub> {

    private GreeterGrpc.GreeterBlockingStub stub;

    @Override
    public GreeterGrpc.GreeterBlockingStub getStub() {
        if (stub == null) {

        }
        return this.stub;
    }

    public String sayHello() {
        HelloRequest request = HelloRequest.newBuilder().build();
        HelloReply reply = getStub().sayHello(request);
        return reply.getMessage();
    }
}
