package com.lsj.core.spring.grpc.client.config;

import org.springframework.context.annotation.Import;

/**
 * @author lishangjian
 * @date 2024/4/8 17:25
 */
@Import({LsjGRpcClientBeanConfiguration.class, LsjGRpcClientLbConfiguration.class, LsjGRpcDiscoveryConfig.class})
public class LsjGRpcClientAllConfig {
}
