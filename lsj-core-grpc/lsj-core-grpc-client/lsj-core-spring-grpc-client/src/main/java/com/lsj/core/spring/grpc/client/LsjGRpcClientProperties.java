package com.lsj.core.spring.grpc.client;

import com.lsj.core.spring.grpc.client.consts.LsjGRpcClientConst;
import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lishangjian
 * @date 2024/3/29 17:16
 */
@ConfigurationProperties(value = LsjGRpcClientConst.CONST_BASE_PROPERTIES_PATH)
public class LsjGRpcClientProperties {

    /**
     * 使用的服务发现类型.
     */
    private EDiscoveryType discoveryType;


    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public EDiscoveryType getDiscoveryType() {
        return discoveryType;
    }

    public void setDiscoveryType(EDiscoveryType discoveryType) {
        this.discoveryType = discoveryType;
    }
}
