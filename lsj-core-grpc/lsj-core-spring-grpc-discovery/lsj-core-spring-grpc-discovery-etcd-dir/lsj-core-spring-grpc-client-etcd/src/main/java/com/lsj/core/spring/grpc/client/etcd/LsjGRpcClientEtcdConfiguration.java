package com.lsj.core.spring.grpc.client.etcd;

import com.lsj.core.spring.grpc.client.etcd.config.LsjGRpcClientEtcdConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author lishangjian
 * @date 2024/4/11 上午10:51
 */
@AutoConfiguration
@Import({LsjGRpcClientEtcdConfig.class})
public class LsjGRpcClientEtcdConfiguration {
}
