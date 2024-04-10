package com.lsj.core.spring.grpc.core.config;

import com.lsj.commonutil.util.common.InetUtils;
import com.lsj.core.spring.grpc.core.properties.LsjGRpcProperties;
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

    @Bean
    public InetUtils inetUtils() {
        return new InetUtils();
    }

}
