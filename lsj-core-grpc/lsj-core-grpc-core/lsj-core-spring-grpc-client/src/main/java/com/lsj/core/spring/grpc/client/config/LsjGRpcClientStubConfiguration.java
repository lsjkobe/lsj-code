package com.lsj.core.spring.grpc.client.config;

import com.lsj.core.spring.grpc.client.discovery.stub.handler.LsjGRpcStubClientCommonHandler;
import org.springframework.context.annotation.Bean;

/**
 * 负载均衡相关.
 * @author lishangjian
 * @date 2024/4/11 下午2:20
 */
public class LsjGRpcClientStubConfiguration {

    @Bean
    public LsjGRpcStubClientCommonHandler<?> lsjGRpcStubClientCommonHandler() {
        return new LsjGRpcStubClientCommonHandler<>();
    }
}
