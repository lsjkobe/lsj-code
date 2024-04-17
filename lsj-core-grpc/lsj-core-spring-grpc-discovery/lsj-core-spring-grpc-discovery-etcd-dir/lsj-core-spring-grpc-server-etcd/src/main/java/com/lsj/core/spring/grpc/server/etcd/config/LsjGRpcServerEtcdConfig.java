package com.lsj.core.spring.grpc.server.etcd.config;


import com.lsj.core.spring.grpc.core.consts.LsjGRpcConst;
import com.lsj.core.spring.grpc.discovery.etcd.config.LsjGRpcEtcdClientManager;
import com.lsj.core.spring.grpc.discovery.etcd.config.properties.LsjGRpcServerDiscoveryEtcdProperties;
import com.lsj.core.spring.grpc.server.etcd.serviceregistry.LsjGRpcEtcdServiceRegistrant;
import com.lsj.core.spring.grpc.server.serviceregistry.ILsjGRpcServiceRegistrant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lishangjian
 * @date 2024/3/30 15:56
 */
@Configuration
@ConditionalOnProperty(prefix = LsjGRpcConst.CONST_DISCOVERY_PROPERTIES_PATH, value = {"enabled", "etcd.enabled"}, havingValue = "true")
public class LsjGRpcServerEtcdConfig {

    @Bean
    public ILsjGRpcServiceRegistrant lsjGRpcEtcdServiceRegistrant(
            LsjGRpcServerDiscoveryEtcdProperties discoveryProperties,
            LsjGRpcEtcdClientManager lsjGRpcEtcdClientManager) {
        return new LsjGRpcEtcdServiceRegistrant(discoveryProperties, lsjGRpcEtcdClientManager);
    }
}
