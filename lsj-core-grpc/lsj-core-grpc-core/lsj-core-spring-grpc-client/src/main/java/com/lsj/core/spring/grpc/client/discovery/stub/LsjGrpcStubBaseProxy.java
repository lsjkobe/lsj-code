package com.lsj.core.spring.grpc.client.discovery.stub;

import com.lsj.core.spring.grpc.client.discovery.LsjGRpcClientDiscoveryHandler;
import com.lsj.core.spring.grpc.client.discovery.LsjGRpcClientDiscoveryManager;
import com.lsj.core.spring.grpc.client.model.DiscoveryBuildStubParam;
import io.grpc.stub.AbstractBlockingStub;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author lishangjian
 * @date 2024/4/11 下午2:09
 */
@Slf4j
@Setter
@Getter
public abstract class LsjGrpcStubBaseProxy<T extends AbstractBlockingStub<T>> implements LsjGrpcStubProxy<T> {

    private final Class<T> stubClass;

    protected String serviceName;

    protected String componentId;

    protected LsjGrpcStubBaseProxy() {
        ParameterizedType stubParameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        //需要获取的泛型是第一位，所以获取0
        Type stubType = stubParameterizedType.getActualTypeArguments()[0];
        stubClass = (Class<T>) stubType;
    }

    @Override
    public T getStub() {
        LsjGRpcClientDiscoveryHandler discoveryHandler =
                LsjGRpcClientDiscoveryManager.getDiscoveryHandler();
        if (discoveryHandler == null) {
            log.error("未指定客户端服务发现类型");
            throw new RuntimeException("未指定客户端服务发现类型");
        }
        DiscoveryBuildStubParam param = new DiscoveryBuildStubParam();
        param.setComponentId(componentId);
        param.setServiceName(serviceName);
        return discoveryHandler.buildStub(param, stubClass);
    }

}
