package com.lsj.core.spring.grpc.core.model;

import java.net.URI;
import java.util.Map;

/**
 * @author lishangjian
 * @date 2024/4/10 下午4:29
 */
public interface LsjGRpcServiceInstance {

    /**
     * @return The unique instance ID as registered.
     */
    default String getInstanceId() {
        return null;
    }

    /**
     * @return The service ID as registered.
     */
    String getServiceId();

    /**
     * @return The hostname of the registered service instance.
     */
    String getHost();

    /**
     * @return The port of the registered service instance.
     */
    int getPort();

    /**
     * @return Whether the port of the registered service instance uses HTTPS.
     */
    boolean isSecure();

    /**
     * @return The service URI address.
     */
    URI getUri();

    /**
     * @return The key / value pair metadata associated with the service instance.
     */
    Map<String, String> getMetadata();

    /**
     * @return The scheme of the service instance.
     */
    default String getScheme() {
        return null;
    }
}
