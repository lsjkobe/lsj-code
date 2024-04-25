package com.lsj.core.spring.grpc.client.test.stubproxy;

import com.lsj.core.grpc.server.test.LsjReply;
import com.lsj.core.grpc.server.test.LsjRequest;
import com.lsj.core.grpc.server.test.LsjTestGrpc;
import com.lsj.core.spring.grpc.client.discovery.stub.LsjGrpcStubBaseProxy;
import com.lsj.core.spring.grpc.core.annotation.LsjGRpcClient;

/**
 * @author lishangjian
 * @date 2024/4/9 11:31
 */
@LsjGRpcClient(serviceName = "core-grpc-server-test", componentId = "lsjTestService")
public class LsjTestBlockingStubProxy extends LsjGrpcStubBaseProxy<LsjTestGrpc.LsjTestBlockingStub> {

    public LsjReply doTest(LsjRequest request) {
        return getStub().doTest(request);
    }
}
