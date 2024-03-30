package com.lsj.core.spring.grpc.server.config.properties;

/**
 * @author lishangjian
 * @date 2024/3/29 17:16
 */
public class LsjGRpcServerDiscoveryProperties {

    private Boolean enabled = Boolean.FALSE;

    /**
     * grpc端口.
     */
    private LsjGRpcServerDiscoveryInfoProperties nacos = new LsjGRpcServerDiscoveryInfoProperties();

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public LsjGRpcServerDiscoveryInfoProperties getNacos() {
        return nacos;
    }

    public void setNacos(LsjGRpcServerDiscoveryInfoProperties nacos) {
        this.nacos = nacos;
    }
}
