package com.lsj.core.spring.grpc.core.model;

/**
 * @author lishangjian
 * @date 2024/4/10 下午4:31
 */
public interface LsjGRpcRequest<R> {
    default R getContext() {
        return null;
    }
}
