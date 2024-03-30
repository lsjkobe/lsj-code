package com.lsj.core.grpc.server.test;

import com.lsj.core.spring.grpc.server.annotation.EnableLsjGRpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableLsjGRpc(grpcServicePath = "com.lsj.core.grpc.server.test")
@SpringBootApplication
public class LsjCoreGrpcServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LsjCoreGrpcServerTestApplication.class, args);
    }

}
