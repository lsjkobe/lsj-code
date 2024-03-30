package com.lsj.core.spring.grpc.server.config.properties;

/**
 * @author lishangjian
 * @date 2024/3/29 17:16
 */
public class LsjGRpcServerDiscoveryInfoProperties {

    private Boolean enabled = Boolean.FALSE;

    private String host;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
