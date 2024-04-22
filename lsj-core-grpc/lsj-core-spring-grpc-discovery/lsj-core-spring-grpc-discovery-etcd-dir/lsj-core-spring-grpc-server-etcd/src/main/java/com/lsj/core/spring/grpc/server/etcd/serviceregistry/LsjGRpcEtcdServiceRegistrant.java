package com.lsj.core.spring.grpc.server.etcd.serviceregistry;

import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import com.lsj.core.spring.grpc.core.properties.LsjGRpcDiscoveryInfoProperties;
import com.lsj.core.spring.grpc.discovery.etcd.config.LsjGRpcEtcdClientManager;
import com.lsj.core.spring.grpc.discovery.etcd.config.properties.LsjGRpcServerDiscoveryEtcdProperties;
import com.lsj.core.spring.grpc.discovery.etcd.util.LsjGRpcEtcdUtil;
import com.lsj.core.spring.grpc.server.serviceregistry.LsjGRpcBaseServiceRegistrant;
import com.lsj.core.spring.grpc.server.serviceregistry.LsjGRpcRegistration;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.Lease;
import io.etcd.jetcd.kv.PutResponse;
import io.etcd.jetcd.lease.LeaseGrantResponse;
import io.etcd.jetcd.lease.LeaseKeepAliveResponse;
import io.etcd.jetcd.options.PutOption;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

/**
 * @author lishangjian
 * @date 2024/3/30 17:56
 */
@Slf4j
public class LsjGRpcEtcdServiceRegistrant extends LsjGRpcBaseServiceRegistrant<LsjGRpcRegistration> {

    private final LsjGRpcServerDiscoveryEtcdProperties discoveryProperties;

    private final LsjGRpcEtcdClientManager lsjGRpcEtcdClientManager;

    public LsjGRpcEtcdServiceRegistrant(LsjGRpcServerDiscoveryEtcdProperties discoveryProperties, LsjGRpcEtcdClientManager lsjGRpcEtcdClientManager) {
        this.discoveryProperties = discoveryProperties;
        this.lsjGRpcEtcdClientManager = lsjGRpcEtcdClientManager;
    }


    @Override
    protected void doRegister(LsjGRpcRegistration registration,
                              Consumer<LsjGRpcRegistration> offlineCallback) {
        if (StringUtils.isEmpty(registration.getServiceId())) {
            log.warn("No service to register for etcd ...");
            return;
        }
        try {
            Client etchClient = lsjGRpcEtcdClientManager.getClient();
            String serviceId = registration.getServiceId();
            String group = discoveryProperties.getGroup();
            Lease leaseClient = etchClient.getLeaseClient();
            long ttl = discoveryProperties.getTtl() == null ?
                    LsjGRpcDiscoveryInfoProperties.DEFAULT_TTL.toSeconds() : discoveryProperties.getTtl().toSeconds();
            // put操作时的可选项，在这里指定租约ID
            LeaseGrantResponse leaseGrantResponse = leaseClient.grant(ttl).get(10, TimeUnit.SECONDS);
            long leaseId = leaseGrantResponse.getID();
            PutOption putOption = PutOption.builder().withLeaseId(leaseId).build();
            KV kvClient = etchClient.getKVClient();
            ByteSequence key = LsjGRpcEtcdUtil.bytesOf(LsjGRpcEtcdUtil.buildServiceKey(group, serviceId, registration.getHost(), registration.getPort()));
            ByteSequence value = LsjGRpcEtcdUtil.bytesOf(registration);
            PutResponse putResponse = kvClient.put(key, value, putOption).get(10, TimeUnit.SECONDS);
            leaseClient.keepAlive(leaseId, new StreamObserver<>() {
                @Override
                public void onNext(LeaseKeepAliveResponse value1) {
                }

                @Override
                public void onError(Throwable t) {
                    log.error("[{}][{}]ETCD服务续租失败", group, serviceId, t);
                }

                @Override
                public void onCompleted() {
                    log.info("[{}][{}]ETCD服务续租断开连接", group, serviceId);
                    offlineCallback.accept(registration);
                }
            });
        } catch (TimeoutException e) {
            throw new RuntimeException("服务注册失败:连接超时", e);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("服务注册失败", e);
        }
    }

    @Override
    protected void doDeregister(LsjGRpcRegistration registration) {

    }

    @Override
    protected LsjGRpcRegistration getCustomRegistration(LsjGRpcRegistration registration) {
        registration.setHost(discoveryProperties.getIp());
        return registration;
    }

    /**
     * 类型.
     *
     * @return .
     */
    @Override
    public EDiscoveryType type() {
        return EDiscoveryType.ETCD;
    }
}
