package com.lsj.core.spring.grpc.client.config;

import com.lsj.core.spring.grpc.client.discovery.LsjGRpcClientDiscoveryManager;
import com.lsj.core.spring.grpc.client.discovery.loadbalancer.LsjGRpcLoadBalancerClientFactory;
import com.lsj.core.spring.grpc.client.discovery.loadbalancer.LsjGRpcLoadBalancerHandler;
import com.lsj.core.spring.grpc.client.discovery.loadbalancer.LsjGRpcServiceInstanceChooser;
import com.lsj.core.spring.grpc.client.discovery.loadbalancer.handler.LsjGRpcLoadBalancerFactory;
import com.lsj.core.spring.grpc.core.properties.LsjGRpcProperties;
import com.lsj.core.spring.grpc.core.util.SpringContextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import java.util.List;

/**
 * @author lishangjian
 * @date 2024/4/9 14:23
 */
@DependsOn(SpringContextUtil.BEAN_NAME)
public class LsjGRpcClientBeanConfiguration {

    @Bean
    public LsjGRpcClientDiscoveryManager lsjGRpcClientDiscoveryManager(
            LsjGRpcProperties properties) {
        return new LsjGRpcClientDiscoveryManager(properties);
    }

    @Bean
    public LsjGRpcLoadBalancerClientFactory lsjGRpcLoadBalancerClientFactory(
            List<LsjGRpcServiceInstanceChooser<?>> handlerList) {
        return new LsjGRpcLoadBalancerClientFactory(handlerList);
    }

    @Bean
    public LsjGRpcLoadBalancerFactory lsjGRpcLoadBalancerFactory(
            List<LsjGRpcLoadBalancerHandler<?>> handlerList) {
        return new LsjGRpcLoadBalancerFactory(handlerList);
    }
}
