package com.lsj.core.spring.grpc.client.discovery.loadbalancer;

import com.lsj.core.spring.grpc.core.model.LsjGRpcServiceInstance;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author lishangjian
 * @date 2024/4/10 下午6:11
 */
public class LsjGRpcBaseLoadBalancerFlux<T extends LsjGRpcServiceInstance> extends Flux<List<T>> {

    private final Supplier<List<T>> instanceSupplier;

    public LsjGRpcBaseLoadBalancerFlux(Supplier<List<T>> instanceSupplier) {
        this.instanceSupplier = instanceSupplier;
    }

    @Override
    public void subscribe(CoreSubscriber<? super List<T>> subscriber) {
        try {
            instanceSupplier.get();
            subscriber.onComplete();
        } catch (Exception e) {
            subscriber.onError(e);
        }
    }
}
