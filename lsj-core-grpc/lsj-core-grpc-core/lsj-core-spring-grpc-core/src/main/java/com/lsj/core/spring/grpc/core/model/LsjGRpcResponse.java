package com.lsj.core.spring.grpc.core.model;

/**
 * @author lishangjian
 * @date 2024/4/10 下午4:31
 */
public interface LsjGRpcResponse<T extends LsjGRpcBaseServiceInstance> {
    boolean hasServer();

    T getServer();

    static LsjGRpcResponse<LsjGRpcBaseServiceInstance> buildEmpty() {
        return new LsjGRpcResponse<>() {
            @Override
            public boolean hasServer() {
                return false;
            }

            @Override
            public LsjGRpcBaseServiceInstance getServer() {
                return null;
            }
        };
    }

    static <T extends LsjGRpcBaseServiceInstance> LsjGRpcResponse<T> buildDefault(T instance) {
        return new LsjGRpcResponse<>() {
            @Override
            public boolean hasServer() {
                return instance != null;
            }

            @Override
            public T getServer() {
                return instance;
            }
        };
    }
}
