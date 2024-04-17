package com.lsj.core.spring.grpc.server.etcd;

import com.lsj.core.spring.grpc.server.etcd.config.LsjGRpcServerEtcdConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author lishangjian
 * @date 2024/3/30 18:14
 */
@AutoConfiguration
@Import({LsjGRpcServerEtcdConfig.class})
public class LsjGRpcServerEtcdConfiguration {
}
