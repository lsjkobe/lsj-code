package com.lsj.core.spring.grpc.client.discovery.loadbalancer;

import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import com.lsj.core.spring.grpc.core.model.LsjGRpcResponse;
import com.lsj.core.spring.grpc.core.model.LsjGRpcServiceInstance;
import org.reactivestreams.Publisher;

/**
 * @author lishangjian
 * @date 2024/4/10 下午4:55
 */
public interface LsjGRpcServiceInstanceChooser<T extends LsjGRpcServiceInstance> {

    /**
     * 选择.
     * @param serviceId .
     * @return .
     */
    T choose(String serviceId);

    Publisher<LsjGRpcResponse<T>> reactiveChoose(String serviceId);

    /**
     * 服务发现类型.
     * @return .
     */
    EDiscoveryType discoveryType();
}
