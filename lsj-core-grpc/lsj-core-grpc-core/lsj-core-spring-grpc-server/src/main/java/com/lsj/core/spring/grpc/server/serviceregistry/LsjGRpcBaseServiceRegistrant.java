package com.lsj.core.spring.grpc.server.serviceregistry;

/**
 * @author lishangjian
 * @date 2024/4/1 11:52
 */
public abstract class LsjGRpcBaseServiceRegistrant<T extends LsjGRpcRegistration>
        implements ILsjGRpcServiceRegistrant {
    @Override
    public void register(LsjGRpcRegistration registration) {
        T t = getCustomRegistration(registration);
        doRegister(t);
    }
    @Override
    public void deregister(LsjGRpcRegistration registration) {
        T t = getCustomRegistration(registration);
        doDeregister(t);
    }

    protected abstract void doRegister(T t);

    protected abstract void doDeregister(T t);

    protected abstract T getCustomRegistration(LsjGRpcRegistration registration);
}
