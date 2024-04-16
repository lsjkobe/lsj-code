package com.lsj.core.spring.grpc.client.test;

import com.lsj.core.spring.grpc.client.test.stubproxy.GreeterBlockingStubProxy;
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
        lsjTestBlockingStubProxy.doTest();
        greeterBlockingStubProxy.sayHello();
        return "";
    }
}
