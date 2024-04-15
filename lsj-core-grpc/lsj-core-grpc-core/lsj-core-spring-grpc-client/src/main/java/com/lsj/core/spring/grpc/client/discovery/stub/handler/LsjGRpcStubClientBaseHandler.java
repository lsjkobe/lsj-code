package com.lsj.core.spring.grpc.client.discovery.stub.handler;

import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import com.lsj.core.spring.grpc.core.model.LsjGRpcBaseServiceInstance;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.AbstractBlockingStub;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @author lishangjian
 * @date 2024/4/11 下午5:01
 */
@Slf4j
public abstract class LsjGRpcStubClientBaseHandler<T extends AbstractBlockingStub<T>>
        implements ILsjGRpcStubClientHandler<T> {

    private final ConcurrentMap<String, ManagedChannel> managedChannelMap = new ConcurrentHashMap<>();

    private final ConcurrentMap<String, ConcurrentMap<String, T>> stubMap = new ConcurrentHashMap<>();

    @Override
    public T handle(LsjGRpcBaseServiceInstance serviceInstance, Class<T> stubClass) {
        ManagedChannel managedChannel = getManagedChannel(serviceInstance);
        return getStub(serviceInstance, managedChannel, stubClass);
    }

    protected ManagedChannel getManagedChannel(LsjGRpcBaseServiceInstance serviceInstance) {
        String managedChannelKey = buildManagedChannelKey(serviceInstance);
        ManagedChannel managedChannel = managedChannelMap.computeIfAbsent(managedChannelKey,
                s -> ManagedChannelBuilder.forAddress(serviceInstance.getHost(), serviceInstance.getPort())
                        .usePlaintext().build());
        if (managedChannel.isShutdown()) {
            synchronized (LsjGRpcStubClientBaseHandler.class) {
                managedChannel = managedChannelMap.get(managedChannelKey);
                if (managedChannel.isShutdown()) {
                    managedChannel = ManagedChannelBuilder.forAddress(serviceInstance.getHost(), serviceInstance.getPort())
                            .usePlaintext().build();
                    managedChannelMap.put(managedChannelKey, managedChannel);
                }
            }
        }
        return managedChannel;
    }

    protected T getStub(LsjGRpcBaseServiceInstance serviceInstance, ManagedChannel managedChannel, Class<T> stubClass) {
        ConcurrentMap<String, T> serviceIdMap =
                stubMap.computeIfAbsent(serviceInstance.getServiceId(), s -> new ConcurrentHashMap<>());
        String stubKey = buildStubKey(serviceInstance);
        return serviceIdMap.computeIfAbsent(stubKey, s -> {
            T stub = T.newStub((channel, callOptions) -> {
                try {
                    Constructor<T> constructor = stubClass.getDeclaredConstructor(Channel.class, CallOptions.class);
                    constructor.setAccessible(Boolean.TRUE);
                    return constructor.newInstance(channel, callOptions);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, managedChannel);
            try {
                subscribe(serviceInstance);
            } catch (Exception e) {
                log.error("[{}][{}]服务订阅失败", discoveryTypeName(), serviceInstance.getServiceId());
                throw new RuntimeException(e);
            }
            return stub;
        });


    }

    protected abstract void subscribe(LsjGRpcBaseServiceInstance serviceInstance) throws Exception;

    protected void handleSubscribe(String serviceId, List<LsjGRpcBaseServiceInstance> serviceInstanceList) {
        ConcurrentMap<String, T> serviceIdMap = stubMap.get(serviceId);
        if (serviceIdMap == null) {
            return;
        }
        Map<String, LsjGRpcBaseServiceInstance> serviceInstanceMap =
                serviceInstanceList.stream().collect(Collectors.toMap(this::buildStubKey, x -> x));

        Iterator<Map.Entry<String, T>> iterator = serviceIdMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, T> entry = iterator.next();
            String stubKey = entry.getKey();
            LsjGRpcBaseServiceInstance serviceInstance = serviceInstanceMap.get(stubKey);
            if (serviceInstance == null) {
                iterator.remove();
            } else {
                if (!serviceInstance.isAvailable()) {
                    iterator.remove();
                }
            }
        }
    }

    private String buildManagedChannelKey(LsjGRpcBaseServiceInstance serviceInstance) {
        return serviceInstance.getHost() + serviceInstance.getPort();
    }

    private String buildStubKey(LsjGRpcBaseServiceInstance serviceInstance) {
        return serviceInstance.getHost() + serviceInstance.getPort() + serviceInstance.getServiceId();
    }

    protected String discoveryTypeName() {
        EDiscoveryType discoveryType = discoveryType();
        return discoveryType == null ? "通用" : discoveryType.name();
    }
}
