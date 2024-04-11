package com.lsj.core.spring.grpc.discovery.config;

import com.lsj.core.spring.grpc.discovery.config.properties.LsjGRpcServerDiscoveryNacosProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author lishangjian
 * @date 2024/4/10 上午11:41
 */
public class LsjGRpcNacosDiscoveryBeanConfig {

    @Bean
    public LsjGRpcNacosNameServiceManager lsjGRpcNacosNameServiceManager(
            LsjGRpcServerDiscoveryNacosProperties discoveryNacosProperties) {
        return new LsjGRpcNacosNameServiceManager(discoveryNacosProperties);
    }
}
