package com.lsj.core.spring.grpc.server.config;

import com.lsj.core.spring.grpc.server.config.properties.LsjGRpcServerProperties;
import com.lsj.core.spring.grpc.server.helper.LsjGRpcRegistryHelper;
import com.lsj.core.spring.grpc.server.helper.LsjGRpcStarterHelper;
import com.lsj.core.spring.grpc.server.serviceregistry.ILsjGRpcServiceRegistrant;
import com.lsj.core.spring.grpc.server.serviceregistry.LsjGRpcServiceRegistryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.List;

/**
 * @author lishangjian
 * @date 2024/3/29 17:15
 */
@Configuration
public class LsjGRpcBeanConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public LsjGRpcStarterHelper lsjGRpcStarterHelper(
            LsjGRpcServerProperties properties,
            LsjGRpcRegistryHelper lsjGRpcRegistryHelper) {
        return new LsjGRpcStarterHelper(properties, lsjGRpcRegistryHelper);
    }

    @Bean
    public LsjGRpcRegistryHelper lsjGRpcRegistryHelper(
            LsjGRpcServerProperties properties,
            LsjGRpcServiceRegistryManager lsjGRpcServiceRegistryManager) {
        return new LsjGRpcRegistryHelper(properties, lsjGRpcServiceRegistryManager, env);
    }

    @Bean
    public LsjGRpcServiceRegistryManager lsjGRpcServiceRegistryManager(
            LsjGRpcServerProperties properties,
            List<ILsjGRpcServiceRegistrant> registrantList) {
        return new LsjGRpcServiceRegistryManager(properties, registrantList);
    }
}
