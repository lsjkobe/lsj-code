package com.lsj.core.spring.grpc.discovery.etcd.config;

import com.lsj.core.spring.grpc.discovery.etcd.config.properties.LsjGRpcServerDiscoveryEtcdProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author lishangjian
 * @date 2024/4/16 17:53
 */
public class LsjGRpcEtcdDiscoveryBeanConfig {

    @Bean
    public LsjGRpcEtcdClientManager lsjGRpcEtcdClientManager(LsjGRpcServerDiscoveryEtcdProperties discoveryProperties) {
        return new LsjGRpcEtcdClientManager(discoveryProperties);
    }
}
