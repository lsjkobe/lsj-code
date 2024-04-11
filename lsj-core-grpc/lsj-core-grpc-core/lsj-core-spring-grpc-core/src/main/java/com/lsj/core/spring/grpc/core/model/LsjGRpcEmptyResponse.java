package com.lsj.core.spring.grpc.core.model;

/**
 * @author lishangjian
 * @date 2024/4/10 下午5:57
 */
public class LsjGRpcEmptyResponse implements LsjGRpcResponse<LsjGRpcBaseServiceInstance> {

    @Override
    public boolean hasServer() {
        return false;
    }

    @Override
    public LsjGRpcBaseServiceInstance getServer() {
        return null;
    }
}
