package com.lsj.core.spring.grpc.server.config.properties;

/**
 * @author lishangjian
 * @date 2024/3/29 17:16
 */
public class LsjGRpcServerDiscoveryInfoProperties {

    private Boolean enabled;

    private String host;

    private String userName;

    private String userPwd;

    /**
     * instance weight.
     */
    private double weight = 1.0D;

    /**
     * The ip address your want to register for your service instance, needn't to set it
     * if the auto detect ip works well.
     */
    private String ip;

    /**
     * The port your want to register for your service instance, needn't to set it if the
     * auto detect port works well.
     */
    private int port = -1;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
