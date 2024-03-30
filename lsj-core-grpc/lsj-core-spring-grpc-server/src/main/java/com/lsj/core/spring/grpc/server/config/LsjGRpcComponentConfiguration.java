package com.lsj.core.spring.grpc.server.config;

import com.lsj.core.spring.grpc.server.util.SpringContextUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author lishangjian
 * @date 2024/3/29 17:20
 */
@Configuration
@Import(SpringContextUtil.class)
public class LsjGRpcComponentConfiguration {
}
