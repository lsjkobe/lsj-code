package com.lsj.core.spring.grpc.discovery.config.properties;

import lombok.Data;

/**
 * @author lishangjian
 * @date 2024/3/29 17:16
 */
@Data
public class LsjGRpcDiscoveryProperties {

    private Boolean enabled;

    /**
     * 客户
     */
    private String name;

}
