package com.lsj.core.spring.grpc.core.model;

/**
 * @author lishangjian
 * @date 2024/4/10 下午5:57
 */
public class LsjGRpcEmptyResponse implements LsjGRpcResponse<LsjGRpcServiceInstance> {

    @Override
    public boolean hasServer() {
        return false;
    }

    @Override
    public LsjGRpcServiceInstance getServer() {
        return null;
    }
}
