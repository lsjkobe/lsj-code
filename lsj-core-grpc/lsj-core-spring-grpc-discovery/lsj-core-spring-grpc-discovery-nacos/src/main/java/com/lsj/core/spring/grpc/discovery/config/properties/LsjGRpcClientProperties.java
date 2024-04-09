package com.lsj.core.spring.grpc.discovery.config.properties;

import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import lombok.Data;

/**
 * @author lishangjian
 * @date 2024/3/29 17:16
 */
@Data
public class LsjGRpcClientProperties {

    /**
     * 客户端使用的服务发现类型.
     */
    private EDiscoveryType discoveryType;

}
