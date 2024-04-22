package com.lsj.core.spring.grpc.client.etcd.discovery.stub.handler;

import com.lsj.core.spring.grpc.client.discovery.stub.handler.LsjGRpcStubClientBaseHandler;
import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import com.lsj.core.spring.grpc.core.model.LsjGRpcBaseServiceInstance;
import com.lsj.core.spring.grpc.discovery.etcd.config.LsjGRpcEtcdClientManager;
import com.lsj.core.spring.grpc.discovery.etcd.config.properties.LsjGRpcServerDiscoveryEtcdProperties;
import com.lsj.core.spring.grpc.discovery.etcd.util.LsjGRpcEtcdUtil;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.Watch;
import io.etcd.jetcd.options.WatchOption;
import io.etcd.jetcd.watch.WatchEvent;
import io.grpc.stub.AbstractBlockingStub;

import java.util.List;
import java.util.Objects;

/**
 * @author lishangjian
 * @date 2024/4/15 15:56
 */

public class LsjGRpcStubClientEtcdHandler<T extends AbstractBlockingStub<T>>
        extends LsjGRpcStubClientBaseHandler<T> {

    private final LsjGRpcEtcdClientManager etcdClientManager;
    private final LsjGRpcServerDiscoveryEtcdProperties discoveryProperties;

    public LsjGRpcStubClientEtcdHandler(LsjGRpcEtcdClientManager etcdClientManager,
                                        LsjGRpcServerDiscoveryEtcdProperties discoveryProperties) {
        this.etcdClientManager = etcdClientManager;
        this.discoveryProperties = discoveryProperties;
    }

    @Override
    protected void subscribe(LsjGRpcBaseServiceInstance serviceInstance) {
        String servicePrefix = LsjGRpcEtcdUtil.buildServicePrefix(
                discoveryProperties.getGroup(), serviceInstance.getServiceId());
        ByteSequence servicePrefixBs = LsjGRpcEtcdUtil.bytesOf(servicePrefix);
        Watch watchClient = etcdClientManager.getClient().getWatchClient();
        watchClient.watch(servicePrefixBs, WatchOption.builder().isPrefix(Boolean.TRUE).build(), watchResponse -> {
            List<WatchEvent> watchEventList = watchResponse.getEvents();
            //把删除的剔除
            List<KeyValue> kvList = watchEventList.stream().filter(item ->
                    Objects.equals(item.getEventType(), WatchEvent.EventType.DELETE))
                    .map(WatchEvent::getKeyValue).toList();
            List<LsjGRpcBaseServiceInstance> serviceInstanceList =
                    LsjGRpcEtcdUtil.buildServiceInstanceList(kvList);
            handleSubscribeDel(serviceInstance.getServiceId(), serviceInstanceList);
        });
    }

    @Override
    public EDiscoveryType discoveryType() {
        return EDiscoveryType.ETCD;
    }
}
