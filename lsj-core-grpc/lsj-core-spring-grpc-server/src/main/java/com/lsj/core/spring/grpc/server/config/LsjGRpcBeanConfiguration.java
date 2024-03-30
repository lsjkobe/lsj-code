package com.lsj.core.spring.grpc.server.config;

import com.lsj.core.spring.grpc.server.config.properties.LsjGRpcServerProperties;
import com.lsj.core.spring.grpc.server.helper.LsjGRpcStarterHelper;
import com.lsj.core.spring.grpc.server.serviceregistry.LsjGRpcBaseServiceRegistrant;
import com.lsj.core.spring.grpc.server.serviceregistry.LsjGRpcRegistration;
import com.lsj.core.spring.grpc.server.serviceregistry.LsjGRpcServiceRegistryManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author lishangjian
 * @date 2024/3/29 17:15
 */
@Configuration
public class LsjGRpcBeanConfiguration {

    @Bean
    public LsjGRpcStarterHelper lsjGRpcStarterHelper(LsjGRpcServerProperties properties) {
        return new LsjGRpcStarterHelper(properties);
    }

    @Bean
    public LsjGRpcServiceRegistryManager lsjGRpcServiceRegistryManager(
            LsjGRpcServerProperties properties,
            List<LsjGRpcBaseServiceRegistrant<? extends LsjGRpcRegistration>> registrantList) {
        return new LsjGRpcServiceRegistryManager(properties, registrantList);
    }
}
