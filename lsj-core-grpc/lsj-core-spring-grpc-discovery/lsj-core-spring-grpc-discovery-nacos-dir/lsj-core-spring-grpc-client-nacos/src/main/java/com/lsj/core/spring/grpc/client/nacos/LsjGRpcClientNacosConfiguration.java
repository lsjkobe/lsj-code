package com.lsj.core.spring.grpc.client.nacos;

import com.lsj.core.spring.grpc.client.nacos.config.LsjGRpcClientNacosConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author lishangjian
 * @date 2024/4/11 上午10:51
 */
@AutoConfiguration
@Import({LsjGRpcClientNacosConfig.class})
public class LsjGRpcClientNacosConfiguration {
}
