package com.lsj.core.spring.grpc.discovery.etcd.config;

import com.lsj.core.spring.grpc.discovery.etcd.config.properties.LsjGRpcServerDiscoveryEtcdProperties;
import com.lsj.core.spring.grpc.discovery.etcd.util.LsjGRpcEtcdUtil;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author lishangjian
 * @date 2024/4/1 14:41
 */
public class LsjGRpcEtcdClientManager {

    private volatile Client client;

    private final LsjGRpcServerDiscoveryEtcdProperties discoveryEtcdProperties;

    public LsjGRpcEtcdClientManager(LsjGRpcServerDiscoveryEtcdProperties discoveryEtcdProperties) {
        this.discoveryEtcdProperties = discoveryEtcdProperties;
    }

    public Client getClient() {
        if (Objects.isNull(this.client)) {
            synchronized (LsjGRpcEtcdClientManager.class) {
                if (Objects.isNull(this.client)) {
                    this.client = createNewClient();
                }
            }
        }
        return this.client;
    }

    private Client createNewClient() {
        checkProperties(discoveryEtcdProperties);
        ByteSequence namespaceBs = LsjGRpcEtcdUtil.bytesOf(
                LsjGRpcEtcdUtil.buildNamespace(discoveryEtcdProperties.getNamespace()));
        return Client.builder().endpoints(discoveryEtcdProperties.getHost().split(","))
                .namespace(namespaceBs).build();
    }

    private void checkProperties(LsjGRpcServerDiscoveryEtcdProperties discoveryEtcdProperties) {
        if (StringUtils.isBlank(discoveryEtcdProperties.getHost())) {
            throw new RuntimeException("Etcd discovery host property is empty");
        }
    }

}
