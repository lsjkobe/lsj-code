package com.lsj.core.spring.grpc.core.properties;

import lombok.Data;

import java.time.Duration;

/**
 * @author lishangjian
 * @date 2024/3/29 17:16
 */
@Data
public class LsjGRpcDiscoveryInfoProperties {

    public static final Duration DEFAULT_TTL = Duration.ofSeconds(30);

    private Boolean enabled;

    /**
     * 多个逗号隔开.
     */
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

    /**
     * group name.
     */
    private String group = "DEFAULT_GROUP";

    /**
     * namespace, separation registry of different environments.
     */
    private String namespace = "DEFAULT_NAMESPACE";

    /**
     * 保活 默认30秒.
     * 服务30秒没有保持心跳，则剔除服务
     */
    private Duration ttl = DEFAULT_TTL;
}
