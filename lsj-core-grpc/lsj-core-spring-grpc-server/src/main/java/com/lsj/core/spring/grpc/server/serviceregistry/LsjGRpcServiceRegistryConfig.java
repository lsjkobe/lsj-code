package com.lsj.core.spring.grpc.server.serviceregistry;

import com.lsj.core.spring.grpc.server.serviceregistry.nacos.LsjGRpcServiceRegistryNacosConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;

/**
 * @author lishangjian
 * @date 2024/3/30 15:56
 */
@ConditionalOnProperty(value = {"lsj.grpc.discovery.enabled"})
@Import({LsjGRpcServiceRegistryNacosConfig.class})
public class LsjGRpcServiceRegistryConfig {

}
