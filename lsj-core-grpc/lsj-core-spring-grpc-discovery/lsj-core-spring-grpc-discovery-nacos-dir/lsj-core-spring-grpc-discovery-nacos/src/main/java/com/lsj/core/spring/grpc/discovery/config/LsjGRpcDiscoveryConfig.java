package com.lsj.core.spring.grpc.discovery.config;

import com.lsj.core.spring.grpc.core.config.LsjGRpcBeanConfig;
import com.lsj.core.spring.grpc.core.util.SpringContextUtil;
import org.springframework.context.annotation.Import;

/**
 * @author lishangjian
 * @date 2024/3/30 17:24
 */
@Import({LsjGRpcBeanConfig.class, SpringContextUtil.class})
public class LsjGRpcDiscoveryConfig {
}
