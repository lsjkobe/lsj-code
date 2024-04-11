package com.lsj.core.spring.grpc.client.discovery.loadbalancer;

import com.lsj.core.spring.grpc.core.enums.LoadBalancerType;
import com.lsj.core.spring.grpc.core.model.LsjGRpcResponse;
import com.lsj.core.spring.grpc.core.model.LsjGRpcServiceInstance;

import java.util.List;

/**
 * @author lishangjian
 * @date 2024/4/10 下午4:21
 */
public interface LsjGRpcLoadBalancerHandler<T extends LsjGRpcServiceInstance> {

    LsjGRpcResponse<T> choose(List<T> instanceList);

    LoadBalancerType loadBalancerType();
}
