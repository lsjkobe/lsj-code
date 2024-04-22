package com.lsj.core.spring.grpc.client.etcd.discovery.loadbalancer;

import com.lsj.core.spring.grpc.client.discovery.loadbalancer.LsjGRpcLoadBalancerClient;
import com.lsj.core.spring.grpc.client.discovery.loadbalancer.handler.LsjGRpcLoadBalancerFactory;
import com.lsj.core.spring.grpc.client.model.DiscoveryBuildStubParam;
import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import com.lsj.core.spring.grpc.core.model.LsjGRpcBaseServiceInstance;
import com.lsj.core.spring.grpc.discovery.etcd.config.LsjGRpcEtcdClientManager;
import com.lsj.core.spring.grpc.discovery.etcd.config.properties.LsjGRpcServerDiscoveryEtcdProperties;
import com.lsj.core.spring.grpc.discovery.etcd.util.LsjGRpcEtcdUtil;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.options.GetOption;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author lishangjian
 * @date 2024/4/11 上午11:38
 */
public class LsjGRpcEtcdLoadBalancerClient extends LsjGRpcLoadBalancerClient<LsjGRpcBaseServiceInstance> {

    private final LsjGRpcEtcdClientManager etcdClientManager;

    private final LsjGRpcServerDiscoveryEtcdProperties discoveryProperties;

    public LsjGRpcEtcdLoadBalancerClient(
            LsjGRpcLoadBalancerFactory lsjGRpcLoadBalancerFactory,
            LsjGRpcEtcdClientManager etcdClientManager, LsjGRpcServerDiscoveryEtcdProperties discoveryProperties) {
        super(lsjGRpcLoadBalancerFactory);
        this.etcdClientManager = etcdClientManager;
        this.discoveryProperties = discoveryProperties;
    }

    @Override
    protected List<LsjGRpcBaseServiceInstance> getInstanceList(DiscoveryBuildStubParam param) {
        String servicePrefix = LsjGRpcEtcdUtil.buildServicePrefix(discoveryProperties.getGroup(), param.buildServiceId());
        ByteSequence servicePrefixBs = LsjGRpcEtcdUtil.bytesOf(servicePrefix);
        try {
            KV kvClient = etcdClientManager.getClient().getKVClient();
            GetResponse response =
                    kvClient.get(servicePrefixBs, GetOption.builder().isPrefix(Boolean.TRUE).build()).get();
            return LsjGRpcEtcdUtil.buildServiceInstanceList(response.getKvs());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 服务发现类型.
     *
     * @return .
     */
    @Override
    public EDiscoveryType discoveryType() {
        return EDiscoveryType.ETCD;
    }
}
