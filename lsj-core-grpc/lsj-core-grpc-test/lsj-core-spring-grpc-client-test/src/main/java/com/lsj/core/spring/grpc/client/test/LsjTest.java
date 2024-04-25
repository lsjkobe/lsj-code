package com.lsj.core.spring.grpc.client.test;

import com.lsj.core.grpc.server.test.HelloReply;
import com.lsj.core.grpc.server.test.HelloRequest;
import com.lsj.core.grpc.server.test.LsjReply;
import com.lsj.core.grpc.server.test.LsjRequest;
import com.lsj.core.spring.grpc.client.test.stubproxy.GreeterBlockingStubProxy;
import com.lsj.core.spring.grpc.client.test.stubproxy.LsjTestBlockingStubProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lishangjian
 * @date 2024/4/8 18:03
 */
@RestController
@RequestMapping("test")
public class LsjTest {

    @Autowired
    private GreeterBlockingStubProxy greeterBlockingStubProxy;

    @Autowired
    private LsjTestBlockingStubProxy lsjTestBlockingStubProxy;

    @GetMapping("test")
    public String sendMessage() {
        LsjReply lsjReply = lsjTestBlockingStubProxy.doTest(LsjRequest.newBuilder().build());
        HelloReply helloReply = greeterBlockingStubProxy.sayHello(HelloRequest.newBuilder().build());
        return "";
    }
}
