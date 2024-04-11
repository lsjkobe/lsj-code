package com.lsj.core.spring.grpc.discovery.nacos;

import com.lsj.core.spring.grpc.discovery.nacos.config.LsjGRpcNacosDiscoveryConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author lishangjian
 * @date 2024/4/11 上午10:51
 */
@AutoConfiguration
@Import({LsjGRpcNacosDiscoveryConfig.class})
public class LsjGRpcDiscoveryNacosConfiguration {
}
