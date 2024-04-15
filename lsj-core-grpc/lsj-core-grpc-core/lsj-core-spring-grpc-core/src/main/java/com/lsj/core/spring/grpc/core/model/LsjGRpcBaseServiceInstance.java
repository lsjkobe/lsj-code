package com.lsj.core.spring.grpc.core.model;

import lombok.Data;

import java.net.URI;
import java.util.Map;

/**
 * @author lishangjian
 * @date 2024/4/10 下午4:29
 */
@Data
public class LsjGRpcBaseServiceInstance implements LsjGRpcServiceInstance {

    private String serviceId;

    private String host;

    private int port;

    /**
     * instance weight.
     */
    private double weight = 1.0D;

    private boolean isSecure;

    private URI uri;

    private Map<String, String> metadata;

    private boolean isAvailable;
}
