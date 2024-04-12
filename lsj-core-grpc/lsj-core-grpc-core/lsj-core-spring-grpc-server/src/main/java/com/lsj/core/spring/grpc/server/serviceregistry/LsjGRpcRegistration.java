package com.lsj.core.spring.grpc.server.serviceregistry;

import java.util.Map;

/**
 * @author lishangjian
 * @date 2024/3/30 11:50
 */
public class LsjGRpcRegistration {
    private String serviceId;

    private String instanceId;

    private String host;

    private int port;

    private boolean secure;

    private Map<String, String> metadata;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
