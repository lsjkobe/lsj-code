package com.lsj.core.spring.grpc.server.config;

import com.lsj.core.spring.grpc.server.config.properties.LsjGRpcServerProperties;
import com.lsj.core.spring.grpc.server.serviceregistry.LsjGRpcServiceRegistryConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * @author lishangjian
 * @date 2024/3/30 17:24
 */
@Import({LsjGRpcServiceRegistryConfig.class, LsjGRpcComponentConfiguration.class,
        LsjGRpcBeanConfiguration.class, LsjGRpcServiceConfig.class})
@EnableConfigurationProperties({LsjGRpcServerProperties.class})
public class LsjGRpcAllConfig {
}
