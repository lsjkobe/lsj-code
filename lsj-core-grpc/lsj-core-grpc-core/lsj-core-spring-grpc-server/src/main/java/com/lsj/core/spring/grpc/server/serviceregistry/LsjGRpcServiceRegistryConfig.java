package com.lsj.core.spring.grpc.server.serviceregistry;

import com.lsj.core.spring.grpc.core.consts.LsjGRpcConst;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * @author lishangjian
 * @date 2024/3/30 15:56
 */
@ConditionalOnProperty(prefix = LsjGRpcConst.CONST_DISCOVERY_PROPERTIES_PATH, value = {"enabled"})
public class LsjGRpcServiceRegistryConfig {

}
