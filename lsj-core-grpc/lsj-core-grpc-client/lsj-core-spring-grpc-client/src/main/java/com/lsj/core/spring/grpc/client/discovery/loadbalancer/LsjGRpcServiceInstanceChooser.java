package com.lsj.core.spring.grpc.client.discovery.loadbalancer;

import com.lsj.core.spring.grpc.client.model.DiscoveryBuildStubParam;
import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import com.lsj.core.spring.grpc.core.model.LsjGRpcBaseServiceInstance;
import com.lsj.core.spring.grpc.core.model.LsjGRpcResponse;
import org.reactivestreams.Publisher;

/**
 * @author lishangjian
 * @date 2024/4/10 下午4:55
 */
public interface LsjGRpcServiceInstanceChooser<T extends LsjGRpcBaseServiceInstance> {

    /**
     * 选择.
     * @param param .
     * @return .
     */
    T choose(DiscoveryBuildStubParam param);

    Publisher<LsjGRpcResponse<T>> reactiveChoose(DiscoveryBuildStubParam param);

    /**
     * 服务发现类型.
     * @return .
     */
    EDiscoveryType discoveryType();
}
