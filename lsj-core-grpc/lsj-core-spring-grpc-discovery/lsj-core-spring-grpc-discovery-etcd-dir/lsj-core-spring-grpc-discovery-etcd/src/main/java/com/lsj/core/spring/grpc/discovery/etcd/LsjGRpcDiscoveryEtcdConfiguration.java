package com.lsj.core.spring.grpc.discovery.etcd;

import com.lsj.core.spring.grpc.discovery.etcd.config.LsjGRpcEtcdDiscoveryConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author lishangjian
 * @date 2024/4/11 上午10:51
 */
@AutoConfiguration
@Import({LsjGRpcEtcdDiscoveryConfig.class})
public class LsjGRpcDiscoveryEtcdConfiguration {
}
