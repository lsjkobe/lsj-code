package com.lsj.core.spring.grpc.client.test.stubproxy;

import com.lsj.core.grpc.server.test.GreeterGrpc;
import com.lsj.core.grpc.server.test.HelloReply;
import com.lsj.core.grpc.server.test.HelloRequest;
import com.lsj.core.spring.grpc.client.discovery.stub.LsjGrpcStubBaseProxy;
import com.lsj.core.spring.grpc.core.annotation.LsjGRpcClient;

/**
 * @author lishangjian
 * @date 2024/4/9 11:31
 */
@LsjGRpcClient(serviceName = "core-grpc-server-test", componentId = "helloWorldService")
public class GreeterBlockingStubProxy extends LsjGrpcStubBaseProxy<GreeterGrpc.GreeterBlockingStub> {


    public String sayHello() {
        HelloRequest request = HelloRequest.newBuilder().build();
        HelloReply reply = getStub().sayHello(request);
        return reply.getMessage();
    }
}
