package com.lsj.core.spring.grpc.client.model;

import com.lsj.core.spring.grpc.core.util.LsjGRpcDiscoveryUtil;
import lombok.Data;

/**
 * @author lishangjian
 * @date 2024/4/9 14:45
 */
@Data
public class DiscoveryBuildStubParam {

    /**
     * 服务名.
     */
    private String serviceName;

    /**
     * 组件id.
     */
    private String componentId;

    public String buildServiceId() {
        return LsjGRpcDiscoveryUtil.buildServiceId(serviceName, componentId);
    }
}
