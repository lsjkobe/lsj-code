package com.lsj.core.spring.grpc.client.nacos.discovery.stub.handler;

import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.lsj.core.spring.grpc.client.discovery.stub.handler.LsjGRpcStubClientBaseHandler;
import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import com.lsj.core.spring.grpc.core.model.LsjGRpcBaseServiceInstance;
import com.lsj.core.spring.grpc.discovery.nacos.config.LsjGRpcNacosNameServiceManager;
import com.lsj.core.spring.grpc.discovery.nacos.utils.NacosServiceInstanceUtil;
import io.grpc.stub.AbstractBlockingStub;

import java.util.List;

/**
 * @author lishangjian
 * @date 2024/4/15 15:56
 */

public class LsjGRpcStubClientNacosHandler<T extends AbstractBlockingStub<T>>
        extends LsjGRpcStubClientBaseHandler<T> {

    private final LsjGRpcNacosNameServiceManager nacosNameServiceManager;

    public LsjGRpcStubClientNacosHandler(LsjGRpcNacosNameServiceManager nacosNameServiceManager) {
        this.nacosNameServiceManager = nacosNameServiceManager;
    }

    @Override
    protected void subscribe(LsjGRpcBaseServiceInstance serviceInstance) throws Exception {
        String serviceId = serviceInstance.getServiceId();
        nacosNameServiceManager.getNamingService().subscribe(serviceId, event -> {
            if (event instanceof NamingEvent namingEvent) {
                List<Instance> instanceList =  namingEvent.getInstances();
                List<LsjGRpcBaseServiceInstance> serviceInstanceList =
                        NacosServiceInstanceUtil.buildServiceInstanceList(namingEvent.getServiceName(), instanceList);
                handleSubscribe(namingEvent.getServiceName(), serviceInstanceList);
            }
        });
    }

    @Override
    public EDiscoveryType discoveryType() {
        return EDiscoveryType.NACOS;
    }
}
