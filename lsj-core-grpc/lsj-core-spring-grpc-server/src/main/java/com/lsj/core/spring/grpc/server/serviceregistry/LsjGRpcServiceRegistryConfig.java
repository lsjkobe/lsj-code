package com.lsj.core.spring.grpc.server.serviceregistry;

import com.lsj.core.spring.grpc.server.consts.LsjGRpcServerConst;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * @author lishangjian
 * @date 2024/3/30 15:56
 */
@ConditionalOnProperty(prefix = LsjGRpcServerConst.CONST_DISCOVERY_PROPERTIES_PATH, value = {"enabled"})
public class LsjGRpcServiceRegistryConfig {

}
