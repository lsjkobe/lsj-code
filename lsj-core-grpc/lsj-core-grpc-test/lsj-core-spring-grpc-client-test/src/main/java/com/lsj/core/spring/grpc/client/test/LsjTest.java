package com.lsj.core.spring.grpc.client.test;

import com.lsj.core.spring.grpc.core.annotation.LsjGRpcClient;
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

    @LsjGRpcClient(serviceName = "test", componentId = "12345")
    private ILsjTest2 test;

    @LsjGRpcClient(serviceName = "core-grpc-server-test", componentId = "helloWorldService")
    private GreeterBlockingStubProxy greeterBlockingStubProxy;

    @GetMapping("test")
    public String sendMessage() {
        test.test();
        greeterBlockingStubProxy.sayHello();
        return "";
    }
}
