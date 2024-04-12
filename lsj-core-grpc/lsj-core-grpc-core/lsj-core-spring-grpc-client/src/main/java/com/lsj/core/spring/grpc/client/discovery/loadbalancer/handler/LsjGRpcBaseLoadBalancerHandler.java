package com.lsj.core.spring.grpc.client.discovery.loadbalancer.handler;

import com.lsj.core.spring.grpc.client.discovery.loadbalancer.LsjGRpcLoadBalancerHandler;
import com.lsj.core.spring.grpc.core.model.LsjGRpcBaseServiceInstance;

/**
 * @author lishangjian
 * @date 2024/4/10 下午5:56
 */
public abstract class LsjGRpcBaseLoadBalancerHandler<T extends LsjGRpcBaseServiceInstance>
        implements LsjGRpcLoadBalancerHandler<T> {

}
