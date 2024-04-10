package com.lsj.core.spring.grpc.discovery.config;

import com.lsj.core.spring.grpc.core.config.LsjGRpcBeanConfig;
import com.lsj.core.spring.grpc.core.util.SpringContextUtil;
import com.lsj.core.spring.grpc.discovery.config.properties.LsjGRpcServerDiscoveryNacosProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * @author lishangjian
 * @date 2024/3/30 17:24
 */
@Import({LsjGRpcDiscoveryBeanConfig.class, LsjGRpcBeanConfig.class, SpringContextUtil.class})
@EnableConfigurationProperties(LsjGRpcServerDiscoveryNacosProperties.class)
public class LsjGRpcDiscoveryConfig {
}
