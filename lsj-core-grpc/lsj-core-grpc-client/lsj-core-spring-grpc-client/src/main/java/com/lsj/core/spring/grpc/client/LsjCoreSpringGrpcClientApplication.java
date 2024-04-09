package com.lsj.core.spring.grpc.client;

import com.lsj.core.spring.grpc.client.annotation.EnableLsjGRpcClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableLsjGRpcClient
@SpringBootApplication
public class LsjCoreSpringGrpcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(LsjCoreSpringGrpcClientApplication.class, args);
    }

}
