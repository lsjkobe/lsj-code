package com.lsj.core.spring.grpc.discovery.etcd.config;

import com.lsj.core.spring.grpc.discovery.etcd.config.properties.LsjGRpcServerDiscoveryEtcdProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * @author lishangjian
 * @date 2024/4/16 17:52
 */
@Import({LsjGRpcEtcdDiscoveryBeanConfig.class})
@EnableConfigurationProperties(LsjGRpcServerDiscoveryEtcdProperties.class)
public class LsjGRpcEtcdDiscoveryConfig {
}
