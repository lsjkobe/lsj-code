package com.lsj.core.spring.grpc.server.config.properties;

import com.lsj.core.spring.grpc.server.consts.LsjGRpcServerConst;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lishangjian
 * @date 2024/3/29 17:16
 */
@ConfigurationProperties(value = LsjGRpcServerConst.CONST_BASE_PROPERTIES_PATH)
public class LsjGRpcServerProperties {

    /**
     * grpc端口.
     */
    private Integer port = 9092;

    private LsjGRpcServerDiscoveryProperties discovery = new LsjGRpcServerDiscoveryProperties();

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public LsjGRpcServerDiscoveryProperties getDiscovery() {
        return discovery;
    }

    public void setDiscovery(LsjGRpcServerDiscoveryProperties discovery) {
        this.discovery = discovery;
    }
}
