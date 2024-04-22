package com.lsj.core.spring.grpc.client.discovery;

import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import com.lsj.core.spring.grpc.core.properties.LsjGRpcProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lishangjian
 * @date 2024/4/9 14:21
 */

public class LsjGRpcClientDiscoveryManager {

    private static final Map<EDiscoveryType, LsjGRpcClientDiscoveryHandler> HANDLER = new HashMap<>();;

    private static LsjGRpcProperties properties = null;

    private List<LsjGRpcClientDiscoveryHandler> discoveryHandlerList;

    public LsjGRpcClientDiscoveryManager(LsjGRpcProperties properties,
                                         List<LsjGRpcClientDiscoveryHandler> discoveryHandlerList) {
        LsjGRpcClientDiscoveryManager.properties = properties;
        this.discoveryHandlerList = discoveryHandlerList;
        this.discoveryHandlerList.forEach(item -> HANDLER.put(item.type(), item));
    }

    public static LsjGRpcClientDiscoveryHandler getDiscoveryHandler() {
        return HANDLER.get(properties.getDiscovery().getDiscoveryType());
    }
}
