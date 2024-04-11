package com.lsj.core.spring.grpc.client.discovery.stub.handler;

import com.lsj.core.spring.grpc.core.model.LsjGRpcServiceInstance;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.AbstractBlockingStub;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author lishangjian
 * @date 2024/4/11 下午5:01
 */
public abstract class LsjGRpcStubClientBaseHandler<T extends AbstractBlockingStub<T>>
        implements ILsjGRpcStubClientHandler<T> {

    private final ConcurrentMap<String, ManagedChannel> managedChannelMap = new ConcurrentHashMap<>();

    private final ConcurrentMap<String, T> stubMap = new ConcurrentHashMap<>();

    @Override
    public T handle(LsjGRpcServiceInstance serviceInstance, Class<T> stubClass) {
        ManagedChannel managedChannel = getManagedChannel(serviceInstance);
        return getStub(serviceInstance, managedChannel, stubClass);
    }

    protected ManagedChannel getManagedChannel(LsjGRpcServiceInstance serviceInstance) {
        String managedChannelKey = buildManagedChannelKey(serviceInstance);
        return managedChannelMap.computeIfAbsent(managedChannelKey, s -> {
            return ManagedChannelBuilder.forAddress(serviceInstance.getHost(), serviceInstance.getPort())
                    .usePlaintext().build();
        });
    }

    protected T getStub(LsjGRpcServiceInstance serviceInstance, ManagedChannel managedChannel, Class<T> stubClass) {
        String stubKey = buildStubKey(serviceInstance);
        return stubMap.computeIfAbsent(stubKey, s -> {
            return T.newStub((channel, callOptions) -> {
                try {
                    Constructor<T> constructor = stubClass.getDeclaredConstructor(Channel.class, CallOptions.class);
                    constructor.setAccessible(Boolean.TRUE);
                    return constructor.newInstance(channel, callOptions);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, managedChannel);
        });
    }

    private String buildManagedChannelKey(LsjGRpcServiceInstance serviceInstance) {
        return serviceInstance.getHost() + serviceInstance.getPort();
    }

    private String buildStubKey(LsjGRpcServiceInstance serviceInstance) {
        return serviceInstance.getHost() + serviceInstance.getPort() + serviceInstance.getServiceId();
    }
}
