package com.lsj.core.spring.grpc.client.test;

import com.lsj.core.grpc.server.test.GreeterGrpc;
import com.lsj.core.grpc.server.test.HelloReply;
import com.lsj.core.grpc.server.test.HelloRequest;
import com.lsj.core.spring.grpc.client.discovery.LsjGRpcClientDiscoveryHandler;
import com.lsj.core.spring.grpc.client.discovery.LsjGRpcClientDiscoveryManager;
import com.lsj.core.spring.grpc.client.model.DiscoveryBuildStubParam;

/**
 * @author lishangjian
 * @date 2024/4/9 11:31
 */
public class GreeterBlockingStubProxy implements LsjGrpcStubBaseProxy<GreeterGrpc.GreeterBlockingStub> {

    private GreeterGrpc.GreeterBlockingStub stub;

    private final String componentId;

    public GreeterBlockingStubProxy(String componentId) {
        this.componentId = componentId;
    }

    @Override
    public GreeterGrpc.GreeterBlockingStub getStub() {
        if (stub == null) {
            //todo lsj 锁
            LsjGRpcClientDiscoveryHandler discoveryHandler =
                    LsjGRpcClientDiscoveryManager.getDiscoveryHandler();
            DiscoveryBuildStubParam param = new DiscoveryBuildStubParam();
            param.setComponentId(componentId);
            stub = discoveryHandler.buildStub(param);
        }
        return stub;
    }

    public String sayHello() {
        HelloRequest request = HelloRequest.newBuilder().build();
        HelloReply reply = getStub().sayHello(request);
        return reply.getMessage();
    }
}
