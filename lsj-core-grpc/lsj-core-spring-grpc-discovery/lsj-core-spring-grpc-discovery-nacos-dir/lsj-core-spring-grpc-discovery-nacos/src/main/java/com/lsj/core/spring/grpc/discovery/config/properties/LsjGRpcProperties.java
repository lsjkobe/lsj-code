package com.lsj.core.spring.grpc.discovery.config.properties;

import com.lsj.core.spring.grpc.discovery.consts.LsjGRpcConst;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lishangjian
 * @date 2024/3/29 17:16
 */
@Data
@ConfigurationProperties(value = LsjGRpcConst.CONST_BASE_PROPERTIES_PATH)
public class LsjGRpcProperties {

    /**
     * grpc端口.
     */
    private Integer port = 9092;

    private LsjGRpcDiscoveryProperties discovery = new LsjGRpcDiscoveryProperties();

    private LsjGRpcClientProperties client = new LsjGRpcClientProperties();
}
