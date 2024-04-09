package com.lsj.core.spring.grpc.discovery.config;

import com.lsj.core.spring.grpc.discovery.config.properties.LsjGRpcProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author lishangjian
 * @date 2024/3/30 17:24
 */
public class LsjGRpcBeanConfig {

    @Bean
    @ConditionalOnMissingBean
    public LsjGRpcProperties lsjGRpcProperties() {
        return new LsjGRpcProperties();
    }
}
