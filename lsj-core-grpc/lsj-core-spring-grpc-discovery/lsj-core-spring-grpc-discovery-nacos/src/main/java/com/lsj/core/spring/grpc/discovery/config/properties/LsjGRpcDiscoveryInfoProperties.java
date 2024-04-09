package com.lsj.core.spring.grpc.discovery.config.properties;

import lombok.Data;

/**
 * @author lishangjian
 * @date 2024/3/29 17:16
 */
@Data
public class LsjGRpcDiscoveryInfoProperties {

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

}
