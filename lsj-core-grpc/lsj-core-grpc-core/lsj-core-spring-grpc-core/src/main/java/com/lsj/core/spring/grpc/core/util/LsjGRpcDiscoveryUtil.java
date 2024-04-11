package com.lsj.core.spring.grpc.core.util;

import com.lsj.core.spring.grpc.core.properties.LsjGRpcProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

/**
 * @author lishangjian
 * @date 2024/4/8 17:05
 */
public class LsjGRpcDiscoveryUtil {

    public static String buildServiceId(String name, Environment env, LsjGRpcProperties properties) {
        String appName = env.getProperty("spring.application.name");
        return buildServiceId(StringUtils.firstNonBlank(appName, properties.getDiscovery().getName()), name);
    }

    public static String buildServiceId(String serviceName, String componentId) {
        return serviceName + "::" + componentId;
    }
}
