package com.lsj.core.spring.grpc.client.nacos.config;

import com.lsj.core.spring.grpc.client.discovery.LsjGRpcClientDiscoveryHandler;
import com.lsj.core.spring.grpc.client.discovery.loadbalancer.LsjGRpcLoadBalancerClientFactory;
import com.lsj.core.spring.grpc.client.discovery.loadbalancer.handler.LsjGRpcLoadBalancerFactory;
import com.lsj.core.spring.grpc.client.discovery.stub.LsjGRpcStubClientFactory;
import com.lsj.core.spring.grpc.client.nacos.discovery.LsjGRpcClientNacosDiscoveryHandler;
import com.lsj.core.spring.grpc.client.nacos.discovery.loadbalancer.LsjGRpcNacosLoadBalancerClient;
import com.lsj.core.spring.grpc.core.properties.LsjGRpcProperties;
import com.lsj.core.spring.grpc.discovery.nacos.config.LsjGRpcNacosNameServiceManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lishangjian
 * @date 2024/4/11 上午10:52
 */
@Configuration
//@ConditionalOnProperty(prefix = LsjGRpcConst.CONST_DISCOVERY_PROPERTIES_PATH, value = {"client.discoveryType"}, havingValue = "NACOS")
public class LsjGRpcClientNacosConfig {

    @Bean
    public LsjGRpcClientDiscoveryHandler lsjGRpcClientNacosDiscoveryHandler(
            LsjGRpcLoadBalancerClientFactory loadBalancerClientFactory,
            LsjGRpcStubClientFactory clientFactory,
            LsjGRpcProperties gRpcProperties) {
        return new LsjGRpcClientNacosDiscoveryHandler(loadBalancerClientFactory, clientFactory, gRpcProperties);
    }

    @Bean
    public LsjGRpcNacosLoadBalancerClient lsjGRpcNacosLoadBalancerClient(
            LsjGRpcLoadBalancerFactory loadBalancerFactory,
            LsjGRpcNacosNameServiceManager nacosNameServiceManager) {
        return new LsjGRpcNacosLoadBalancerClient(loadBalancerFactory, nacosNameServiceManager);
    }
}
