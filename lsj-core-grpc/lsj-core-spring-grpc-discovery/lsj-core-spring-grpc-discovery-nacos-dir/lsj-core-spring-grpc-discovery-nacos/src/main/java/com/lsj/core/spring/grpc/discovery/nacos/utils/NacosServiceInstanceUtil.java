package com.lsj.core.spring.grpc.discovery.nacos.utils;

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.lsj.core.spring.grpc.core.model.LsjGRpcBaseServiceInstance;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author lishangjian
 * @date 2024/4/15 16:11
 */
public class NacosServiceInstanceUtil {

    public static List<LsjGRpcBaseServiceInstance> buildServiceInstanceList(String serviceId, List<Instance> instanceList) {
        if (CollectionUtils.isEmpty(instanceList)) {
            return List.of();
        }
        return instanceList.stream().map(item -> buildServiceInstance(serviceId, item)).toList();
    }

    public static LsjGRpcBaseServiceInstance buildServiceInstance(String serviceId, Instance instance) {
        LsjGRpcBaseServiceInstance serviceInstance = new LsjGRpcBaseServiceInstance();
        serviceInstance.setServiceId(serviceId);
        serviceInstance.setHost(instance.getIp());
        serviceInstance.setPort(instance.getPort());
        serviceInstance.setWeight(instance.getWeight());
        serviceInstance.setAvailable(instance.isHealthy() && instance.isEnabled());
        return serviceInstance;
    }
}
