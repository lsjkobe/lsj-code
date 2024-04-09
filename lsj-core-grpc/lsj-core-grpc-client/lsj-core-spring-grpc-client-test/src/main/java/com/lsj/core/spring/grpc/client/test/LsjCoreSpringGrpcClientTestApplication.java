package com.lsj.core.spring.grpc.client.test;

import com.lsj.core.spring.grpc.client.annotation.EnableLsjGRpcClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableLsjGRpcClient
@SpringBootApplication
public class LsjCoreSpringGrpcClientTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LsjCoreSpringGrpcClientTestApplication.class, args);
    }

}
