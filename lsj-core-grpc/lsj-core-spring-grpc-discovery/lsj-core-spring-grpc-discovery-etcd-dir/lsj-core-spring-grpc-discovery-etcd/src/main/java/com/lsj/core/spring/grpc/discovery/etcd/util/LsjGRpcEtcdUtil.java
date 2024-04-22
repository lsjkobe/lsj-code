package com.lsj.core.spring.grpc.discovery.etcd.util;

import com.lsj.commonutil.util.common.JsonUtil;
import com.lsj.core.spring.grpc.core.model.LsjGRpcBaseServiceInstance;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.KeyValue;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author lishangjian
 * @date 2024/4/17 10:56
 */
public class LsjGRpcEtcdUtil {

    /**
     * 将字符串转为客户端所需的ByteSequence实例
     * @param val .
     * @return .
     */
    public static ByteSequence bytesOf(String val) {
        return ByteSequence.from(val, UTF_8);
    }

    /**
     * 将字符串转为客户端所需的ByteSequence实例
     * @param obj .
     * @return .
     */
    public static ByteSequence bytesOf(Object obj) {
        return bytesOf(JsonUtil.toJson(obj));
    }

    public static String buildNamespace(String namespace) {
        return "/" + namespace;
    }

    public static String buildServicePrefix(String group, String serviceId) {
        return "/" + group + "/" + serviceId;
    }

    public static String buildServiceKey(String group, String serviceId, String host, Integer port) {
        return buildServicePrefix(group, serviceId) + "/" + host + ":" + port;
    }

    public static List<LsjGRpcBaseServiceInstance> buildServiceInstanceList(List<KeyValue> serviceKvList) {
        if (CollectionUtils.isEmpty(serviceKvList)) {
            return List.of();
        }
        return serviceKvList.stream().map(LsjGRpcEtcdUtil::buildServiceInstance).toList();
    }

    public static LsjGRpcBaseServiceInstance buildServiceInstance(KeyValue serviceKv) {
        String serviceKey = serviceKv.getKey().toString(UTF_8);
        String[] serviceKeys = serviceKey.split("/");
        String group = serviceKeys[1];
        String serviceId = serviceKeys[2];
        String hostPort = serviceKeys[3];
        String[] hostPorts = hostPort.split(":");
        LsjGRpcBaseServiceInstance serviceInstance = new LsjGRpcBaseServiceInstance();
        serviceInstance.setHost(hostPorts[0]);
        serviceInstance.setPort(Integer.parseInt(hostPorts[1]));
        serviceInstance.setServiceId(serviceId);
        serviceInstance.setAvailable(Boolean.TRUE);
        return serviceInstance;
    }
}
