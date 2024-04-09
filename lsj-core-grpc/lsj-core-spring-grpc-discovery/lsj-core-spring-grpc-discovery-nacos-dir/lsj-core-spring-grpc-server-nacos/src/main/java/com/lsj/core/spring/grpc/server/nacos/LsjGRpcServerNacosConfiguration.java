package com.lsj.core.spring.grpc.server.nacos;

import com.lsj.core.spring.grpc.server.nacos.config.LsjGRpcServiceRegistryNacosConfig;
import com.lsj.core.spring.grpc.server.nacos.config.properties.LsjGRpcServerDiscoveryNacosProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * @author lishangjian
 * @date 2024/3/30 18:14
 */
@AutoConfiguration
@Import({LsjGRpcServiceRegistryNacosConfig.class})
@EnableConfigurationProperties(LsjGRpcServerDiscoveryNacosProperties.class)
public class LsjGRpcServerNacosConfiguration {
}
