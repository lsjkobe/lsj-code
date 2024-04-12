package com.lsj.core.spring.grpc.client.config;

import com.lsj.core.spring.grpc.client.discovery.loadbalancer.handler.LsjGRpcRoundLoadBalancerHandler;
import org.springframework.context.annotation.Bean;

/**
 * 负载均衡相关.
 * @author lishangjian
 * @date 2024/4/11 下午2:20
 */
public class LsjGRpcClientLbConfiguration {

    @Bean
    public LsjGRpcRoundLoadBalancerHandler lsjGRpcRoundLoadBalancerHandler() {
        return new LsjGRpcRoundLoadBalancerHandler();
    }
}
