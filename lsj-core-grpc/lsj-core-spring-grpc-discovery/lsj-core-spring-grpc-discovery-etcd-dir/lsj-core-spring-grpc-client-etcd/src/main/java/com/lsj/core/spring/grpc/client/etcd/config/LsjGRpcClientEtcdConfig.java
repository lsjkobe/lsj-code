package com.lsj.core.spring.grpc.client.etcd.config;

import com.lsj.core.spring.grpc.client.discovery.LsjGRpcClientDiscoveryHandler;
import com.lsj.core.spring.grpc.client.discovery.loadbalancer.LsjGRpcLoadBalancerClientFactory;
import com.lsj.core.spring.grpc.client.discovery.loadbalancer.handler.LsjGRpcLoadBalancerFactory;
import com.lsj.core.spring.grpc.client.discovery.stub.LsjGRpcStubClientFactory;
import com.lsj.core.spring.grpc.client.etcd.discovery.LsjGRpcClientEtcdDiscoveryHandler;
import com.lsj.core.spring.grpc.client.etcd.discovery.stub.handler.LsjGRpcStubClientEtcdHandler;
import com.lsj.core.spring.grpc.client.etcd.discovery.loadbalancer.LsjGRpcEtcdLoadBalancerClient;
import com.lsj.core.spring.grpc.core.consts.LsjGRpcConst;
import com.lsj.core.spring.grpc.core.properties.LsjGRpcProperties;
import com.lsj.core.spring.grpc.discovery.etcd.config.LsjGRpcEtcdClientManager;
import com.lsj.core.spring.grpc.discovery.etcd.config.properties.LsjGRpcServerDiscoveryEtcdProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lishangjian
 * @date 2024/4/19 17:57
 */
@Configuration
@ConditionalOnProperty(prefix = LsjGRpcConst.CONST_DISCOVERY_PROPERTIES_PATH, value = {"discovery-type"}, havingValue = "ETCD")
public class LsjGRpcClientEtcdConfig {

    @Bean
    public LsjGRpcClientDiscoveryHandler lsjGRpcClientEtcdDiscoveryHandler(
            LsjGRpcLoadBalancerClientFactory loadBalancerClientFactory,
            LsjGRpcStubClientFactory clientFactory,
            LsjGRpcProperties gRpcProperties) {
        return new LsjGRpcClientEtcdDiscoveryHandler(loadBalancerClientFactory, clientFactory, gRpcProperties);
    }

    @Bean
    public LsjGRpcEtcdLoadBalancerClient lsjGRpcEtcdLoadBalancerClient(
            LsjGRpcLoadBalancerFactory loadBalancerFactory,
            LsjGRpcEtcdClientManager etcdClientManager,
            LsjGRpcServerDiscoveryEtcdProperties discoveryProperties) {
        return new LsjGRpcEtcdLoadBalancerClient(loadBalancerFactory, etcdClientManager, discoveryProperties);
    }

    @Bean
    public LsjGRpcStubClientEtcdHandler<?> lsjGRpcStubClientEtcdHandler(
            LsjGRpcEtcdClientManager etcdClientManager,
            LsjGRpcServerDiscoveryEtcdProperties discoveryProperties) {
        return new LsjGRpcStubClientEtcdHandler<>(etcdClientManager, discoveryProperties);
    }
}
