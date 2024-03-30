package com.lsj.core.spring.grpc.server.serviceregistry.nacos;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * @author lishangjian
 * @date 2024/3/30 15:56
 */
@ConditionalOnProperty(prefix = "lsj.grpc.discovery", value = {"enabled", "nacos.enabled"}, havingValue = "true")
public class LsjGRpcServiceRegistryNacosConfig {

}
