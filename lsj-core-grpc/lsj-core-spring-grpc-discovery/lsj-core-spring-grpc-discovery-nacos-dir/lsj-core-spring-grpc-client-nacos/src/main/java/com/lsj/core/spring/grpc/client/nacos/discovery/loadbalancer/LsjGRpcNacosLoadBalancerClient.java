package com.lsj.core.spring.grpc.client.nacos.discovery.loadbalancer;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.lsj.core.spring.grpc.client.discovery.loadbalancer.LsjGRpcLoadBalancerClient;
import com.lsj.core.spring.grpc.client.discovery.loadbalancer.handler.LsjGRpcLoadBalancerFactory;
import com.lsj.core.spring.grpc.client.model.DiscoveryBuildStubParam;
import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import com.lsj.core.spring.grpc.core.model.LsjGRpcBaseServiceInstance;
import com.lsj.core.spring.grpc.discovery.nacos.config.LsjGRpcNacosNameServiceManager;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author lishangjian
 * @date 2024/4/11 上午11:38
 */
public class LsjGRpcNacosLoadBalancerClient extends LsjGRpcLoadBalancerClient<LsjGRpcBaseServiceInstance> {

    private final LsjGRpcNacosNameServiceManager nacosNameServiceManager;

    public LsjGRpcNacosLoadBalancerClient(
            LsjGRpcLoadBalancerFactory lsjGRpcLoadBalancerFactory,
            LsjGRpcNacosNameServiceManager nacosNameServiceManager) {
        super(lsjGRpcLoadBalancerFactory);
        this.nacosNameServiceManager = nacosNameServiceManager;
    }

    @Override
    protected List<LsjGRpcBaseServiceInstance> getInstanceList(DiscoveryBuildStubParam param) {
        try {
            String serviceId = param.buildServiceId();
            List<Instance> instanceList = nacosNameServiceManager.getNamingService().selectInstances(serviceId, true);
            return buildServiceInstanceList(instanceList);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

    private List<LsjGRpcBaseServiceInstance> buildServiceInstanceList(List<Instance> instanceList) {
        if (CollectionUtils.isEmpty(instanceList)) {
            return List.of();
        }
        return instanceList.stream().map(this::buildServiceInstance).toList();
    }

    private LsjGRpcBaseServiceInstance buildServiceInstance(Instance instance) {
        LsjGRpcBaseServiceInstance serviceInstance = new LsjGRpcBaseServiceInstance();
        serviceInstance.setHost(instance.getIp());
        serviceInstance.setPort(instance.getPort());
        serviceInstance.setWeight(instance.getWeight());
        return serviceInstance;
    }

    /**
     * 服务发现类型.
     *
     * @return .
     */
    @Override
    public EDiscoveryType discoveryType() {
        return EDiscoveryType.NACOS;
    }
}
