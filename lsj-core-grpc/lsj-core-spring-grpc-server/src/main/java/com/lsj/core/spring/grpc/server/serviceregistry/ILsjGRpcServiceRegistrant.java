package com.lsj.core.spring.grpc.server.serviceregistry;

/**
 * @author lishangjian
 * @date 2024/3/30 16:07
 */
public interface ILsjGRpcServiceRegistrant {

    /**
     * 注册.
     * @param registration .
     */
    void register(LsjGRpcRegistration registration);

    /**
     * 注销.
     * @param registration .
     */
    void deregister(LsjGRpcRegistration registration);

}
