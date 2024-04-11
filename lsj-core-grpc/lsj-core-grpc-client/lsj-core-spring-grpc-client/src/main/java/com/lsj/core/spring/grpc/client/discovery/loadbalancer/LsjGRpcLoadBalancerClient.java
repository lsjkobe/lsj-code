package com.lsj.core.spring.grpc.client.discovery.loadbalancer;

import com.lsj.core.spring.grpc.client.discovery.loadbalancer.handler.LsjGRpcLoadBalancerFactory;
import com.lsj.core.spring.grpc.core.model.LsjGRpcResponse;
import com.lsj.core.spring.grpc.core.model.LsjGRpcServiceInstance;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author lishangjian
 * @date 2024/4/10 下午2:42
 */
public abstract class LsjGRpcLoadBalancerClient<T extends LsjGRpcServiceInstance> implements LsjGRpcServiceInstanceChooser<T> {

    private final LsjGRpcLoadBalancerFactory lsjGRpcLoadBalancerFactory;

    public LsjGRpcLoadBalancerClient(
            LsjGRpcLoadBalancerFactory lsjGRpcLoadBalancerFactory) {
        this.lsjGRpcLoadBalancerFactory = lsjGRpcLoadBalancerFactory;
    }

    /**
     * 选择.
     *
     * @param serviceId .
     * @return .
     */
    @Override
    public T choose(String serviceId) {
        LsjGRpcResponse<T> resp = Mono.from(reactiveChoose(serviceId)).block();
        if (resp == null) {
            return null;
        }
        return resp.getServer();
    }

    @Override
    public Publisher<LsjGRpcResponse<T>> reactiveChoose(String serviceId) {
        LsjGRpcBaseLoadBalancerFlux<T> flux = new LsjGRpcBaseLoadBalancerFlux<>(
                () -> getInstanceList(serviceId));
        LsjGRpcLoadBalancerHandler<T> handler = lsjGRpcLoadBalancerFactory.getInstance(serviceId);
        return flux.next().map(handler::choose);
    }

    protected abstract List<T> getInstanceList(String serviceId);
}
