package com.lsj.core.spring.grpc.server.nacos.config;

import com.lsj.core.spring.grpc.server.consts.LsjGRpcServerConst;
import com.lsj.core.spring.grpc.server.nacos.config.properties.LsjGRpcServerDiscoveryNacosProperties;
import com.lsj.core.spring.grpc.server.nacos.serviceregistry.LsjGRpcNacosNameServiceManager;
import com.lsj.core.spring.grpc.server.nacos.serviceregistry.LsjGRpcNacosServiceRegistrant;
import com.lsj.core.spring.grpc.server.serviceregistry.ILsjGRpcServiceRegistrant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author lishangjian
 * @date 2024/3/30 15:56
 */
@Configuration
@ConditionalOnProperty(prefix = LsjGRpcServerConst.CONST_DISCOVERY_PROPERTIES_PATH, value = {"enabled", "nacos.enabled"}, havingValue = "true")
public class LsjGRpcServiceRegistryNacosConfig {

    @Autowired
    private Environment env;

    @Bean
    public ILsjGRpcServiceRegistrant lsjGRpcNacosServiceRegistrant(
            LsjGRpcServerDiscoveryNacosProperties discoveryNacosProperties,
            LsjGRpcNacosNameServiceManager lsjGRpcNacosNameServiceManager) {
        return new LsjGRpcNacosServiceRegistrant(discoveryNacosProperties,
                lsjGRpcNacosNameServiceManager, env.getProperty("server.port", Integer.class));
    }

    @Bean
    public LsjGRpcNacosNameServiceManager lsjGRpcNacosNameServiceManager(
            LsjGRpcServerDiscoveryNacosProperties discoveryNacosProperties) {
        return new LsjGRpcNacosNameServiceManager(discoveryNacosProperties);
    }
}
