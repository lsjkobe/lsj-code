package com.lsj.core.spring.grpc.discovery.config;

import com.lsj.core.spring.grpc.discovery.config.properties.LsjGRpcServerDiscoveryNacosProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * @author lishangjian
 * @date 2024/4/11 上午11:21
 */
@Import({LsjGRpcNacosDiscoveryBeanConfig.class})
@EnableConfigurationProperties(LsjGRpcServerDiscoveryNacosProperties.class)
public class LsjGRpcNacosDiscoveryConfig {
}
