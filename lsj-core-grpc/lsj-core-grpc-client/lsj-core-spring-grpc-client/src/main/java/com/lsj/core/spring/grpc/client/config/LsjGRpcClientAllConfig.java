package com.lsj.core.spring.grpc.client.config;

import com.lsj.core.spring.grpc.client.LsjGRpcClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * @author lishangjian
 * @date 2024/4/8 17:25
 */
@Import({LsjGRpcClientBeanConfiguration.class})
@EnableConfigurationProperties({LsjGRpcClientProperties.class})
public class LsjGRpcClientAllConfig {
}
