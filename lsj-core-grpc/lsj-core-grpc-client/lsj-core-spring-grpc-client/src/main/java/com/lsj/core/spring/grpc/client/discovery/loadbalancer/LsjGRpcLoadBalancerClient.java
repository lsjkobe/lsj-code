package com.lsj.core.spring.grpc.client.discovery.loadbalancer;

import com.lsj.core.spring.grpc.client.discovery.loadbalancer.handler.LsjGRpcLoadBalancerFactory;
import com.lsj.core.spring.grpc.client.model.DiscoveryBuildStubParam;
import com.lsj.core.spring.grpc.core.model.LsjGRpcBaseServiceInstance;
import com.lsj.core.spring.grpc.core.model.LsjGRpcResponse;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author lishangjian
 * @date 2024/4/10 下午2:42
 */
public abstract class LsjGRpcLoadBalancerClient<T extends LsjGRpcBaseServiceInstance> implements LsjGRpcServiceInstanceChooser<T> {

    private final LsjGRpcLoadBalancerFactory lsjGRpcLoadBalancerFactory;

    public LsjGRpcLoadBalancerClient(
            LsjGRpcLoadBalancerFactory lsjGRpcLoadBalancerFactory) {
        this.lsjGRpcLoadBalancerFactory = lsjGRpcLoadBalancerFactory;
    }

    /**
     * 选择.
     *
     * @param param .
     * @return .
     */
    @Override
    public T choose(DiscoveryBuildStubParam param) {
        LsjGRpcResponse<T> resp = Mono.from(reactiveChoose(param)).block();
        if (resp == null) {
            return null;
        }
        return resp.getServer();
    }

    @Override
    public Publisher<LsjGRpcResponse<T>> reactiveChoose(DiscoveryBuildStubParam param) {
        Mono<List<T>> mono = Mono.create(listMonoSink -> {
            try {
                List<T> instanceList = getInstanceList(param);
                listMonoSink.success(instanceList);
            } catch (Exception e) {
                listMonoSink.error(e);
            }
        });
        LsjGRpcLoadBalancerHandler<T> handler = lsjGRpcLoadBalancerFactory.getInstance(param);
        return mono.map(handler::choose);
    }

    protected abstract List<T> getInstanceList(DiscoveryBuildStubParam param);
}
