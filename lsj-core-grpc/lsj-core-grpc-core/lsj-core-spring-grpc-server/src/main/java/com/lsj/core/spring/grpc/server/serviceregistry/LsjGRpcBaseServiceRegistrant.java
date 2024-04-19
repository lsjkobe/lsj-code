package com.lsj.core.spring.grpc.server.serviceregistry;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author lishangjian
 * @date 2024/4/1 11:52
 */
@Slf4j
public abstract class LsjGRpcBaseServiceRegistrant<T extends LsjGRpcRegistration>
        implements ILsjGRpcServiceRegistrant {

    protected Set<T> reRegisterSet = new CopyOnWriteArraySet<>();

    private volatile ScheduledExecutorService reRegisterScheduler;

    @Override
    public void register(LsjGRpcRegistration registration) {
        T t = getCustomRegistration(registration);
        doRegister(t, this::onOffline);
    }

    @Override
    public void deregister(LsjGRpcRegistration registration) {
        T t = getCustomRegistration(registration);
        doDeregister(t);
    }

    /**
     * 下线时触发.
     *
     * @param t .
     */
    protected void onOffline(T t) {
        reRegisterSet.add(t);
        if (reRegisterScheduler == null) {
            synchronized (this) {
                if (reRegisterScheduler == null) {
                    reRegisterScheduler = new ScheduledThreadPoolExecutor(1);
                    reRegisterScheduler.scheduleWithFixedDelay(() -> {
                        if (CollectionUtils.isEmpty(reRegisterSet)) {
                            return;
                        }
                        Set<T> elementsToRemove = new CopyOnWriteArraySet<>();
                        for (T t1 : reRegisterSet) {
                            try {
                                register(t1);
                                elementsToRemove.add(t1);
                                log.info("[{}][{}]重试注册成功", type().name(), t1.getServiceId());// 安全移除元素
                            } catch (Exception e) {
                                log.error("[{}][{}]重试注册失败", type().name(), t1.getServiceId(), e);
                            }
                        }
                        // 从原始集合中移除需要移除的元素
                        reRegisterSet.removeAll(elementsToRemove);
                    }, 10, 10, TimeUnit.SECONDS);
                }
            }
        }
    }

    protected abstract void doRegister(T t, Consumer<T> offlineCallback);

    protected abstract void doDeregister(T t);

    protected abstract T getCustomRegistration(LsjGRpcRegistration registration);
}
