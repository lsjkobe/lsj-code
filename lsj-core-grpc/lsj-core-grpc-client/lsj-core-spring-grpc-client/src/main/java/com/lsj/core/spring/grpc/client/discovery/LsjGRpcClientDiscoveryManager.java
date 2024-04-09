package com.lsj.core.spring.grpc.client.discovery;

import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import com.lsj.core.spring.grpc.core.util.SpringContextUtil;
import com.lsj.core.spring.grpc.discovery.config.properties.LsjGRpcProperties;

import java.util.Collection;
import java.util.Map;

/**
 * @author lishangjian
 * @date 2024/4/9 14:21
 */

public class LsjGRpcClientDiscoveryManager {

    private static Map<EDiscoveryType, LsjGRpcClientDiscoveryHandler> HANDLER;

    private static LsjGRpcProperties properties = null;

    public LsjGRpcClientDiscoveryManager(LsjGRpcProperties properties) {
        LsjGRpcClientDiscoveryManager.properties = properties;
    }

    public void init() {
        Collection<LsjGRpcClientDiscoveryHandler> list = SpringContextUtil.getBeansOfType(LsjGRpcClientDiscoveryHandler.class).values();
        list.forEach(item -> HANDLER.put(item.type(), item));
    }

    public static LsjGRpcClientDiscoveryHandler getDiscoveryHandler() {
        return HANDLER.get(properties.getClient().getDiscoveryType());
    }
}
