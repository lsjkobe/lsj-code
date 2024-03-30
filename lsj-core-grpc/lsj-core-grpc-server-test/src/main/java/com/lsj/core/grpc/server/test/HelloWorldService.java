package com.lsj.core.grpc.server.test;

import com.lsj.core.grpc.core.annotation.LsjGRpcService;
import io.grpc.stub.StreamObserver;

/**
 * @author lishangjian
 * @date 2024/3/29 15:53
 */
@LsjGRpcService
public class HelloWorldService extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        System.out.println("你好");
        responseObserver.onNext(HelloReply.newBuilder().setMessage("你好").build());
        responseObserver.onCompleted();
    }

    @Override
    public void sayHelloAgain(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        System.out.println("你再好");
        responseObserver.onNext(HelloReply.newBuilder().setMessage("你再好").build());
        responseObserver.onCompleted();
    }
}
