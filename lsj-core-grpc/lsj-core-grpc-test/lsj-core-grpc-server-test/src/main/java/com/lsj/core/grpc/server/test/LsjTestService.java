package com.lsj.core.grpc.server.test;

import com.lsj.core.spring.grpc.core.annotation.LsjGRpcService;
import io.grpc.stub.StreamObserver;

/**
 * @author lishangjian
 * @date 2024/3/29 15:53
 */
@LsjGRpcService("lsjTestService")
public class LsjTestService extends LsjTestGrpc.LsjTestImplBase {
    /**
     * <pre>
     * Sends a greeting
     * </pre>
     *
     * @param request .
     * @param responseObserver .
     */
    @Override
    public void doTest(LsjRequest request, StreamObserver<LsjReply> responseObserver) {
        System.out.println("这是测试");
        responseObserver.onNext(LsjReply.newBuilder().setMessage("这是测试").build());
        responseObserver.onCompleted();
    }
}
