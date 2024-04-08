package com.lsj.core.spring.grpc.server.config.properties;

/**
 * @author lishangjian
 * @date 2024/3/29 17:16
 */
public class LsjGRpcServerDiscoveryProperties {

    private Boolean enabled;

    /**
     * 客户
     */
    private String name;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
