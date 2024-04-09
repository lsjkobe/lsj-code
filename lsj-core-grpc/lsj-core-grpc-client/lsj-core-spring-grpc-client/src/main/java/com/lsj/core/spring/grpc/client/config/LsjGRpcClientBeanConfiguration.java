package com.lsj.core.spring.grpc.client.config;

import com.lsj.core.spring.grpc.client.discovery.LsjGRpcClientDiscoveryManager;
import com.lsj.core.spring.grpc.core.util.SpringContextUtil;
import com.lsj.core.spring.grpc.discovery.config.properties.LsjGRpcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

/**
 * @author lishangjian
 * @date 2024/4/9 14:23
 */
@DependsOn(SpringContextUtil.BEAN_NAME)
public class LsjGRpcClientBeanConfiguration {

    @Bean
    public LsjGRpcClientDiscoveryManager lsjGRpcClientDiscoveryManager(
            LsjGRpcProperties properties) {
        return new LsjGRpcClientDiscoveryManager(properties);
    }
}
