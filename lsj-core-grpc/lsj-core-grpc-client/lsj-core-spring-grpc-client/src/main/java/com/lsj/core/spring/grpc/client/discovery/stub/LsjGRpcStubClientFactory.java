package com.lsj.core.spring.grpc.client.discovery.stub;

import com.lsj.core.spring.grpc.client.discovery.stub.handler.ILsjGRpcStubClientHandler;
import com.lsj.core.spring.grpc.client.discovery.stub.handler.LsjGRpcStubClientCommonHandler;
import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import io.grpc.stub.AbstractBlockingStub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lishangjian
 * @date 2024/4/11 下午4:52
 */
public class LsjGRpcStubClientFactory {

    private final Map<EDiscoveryType, ILsjGRpcStubClientHandler<?>> stubClientHandlerMap;

    public LsjGRpcStubClientFactory(List<ILsjGRpcStubClientHandler<?>> handlerList) {
        stubClientHandlerMap = new HashMap<>();
        for (ILsjGRpcStubClientHandler<?> handler : handlerList) {
            stubClientHandlerMap.put(handler.discoveryType(), handler);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractBlockingStub<T>> ILsjGRpcStubClientHandler<T> getInstance(EDiscoveryType discoveryType) {
        ILsjGRpcStubClientHandler<?> handler = stubClientHandlerMap.get(discoveryType);
        if (handler == null) {
            return new LsjGRpcStubClientCommonHandler<>();
        }
        return (ILsjGRpcStubClientHandler<T>) handler;
    }
}
