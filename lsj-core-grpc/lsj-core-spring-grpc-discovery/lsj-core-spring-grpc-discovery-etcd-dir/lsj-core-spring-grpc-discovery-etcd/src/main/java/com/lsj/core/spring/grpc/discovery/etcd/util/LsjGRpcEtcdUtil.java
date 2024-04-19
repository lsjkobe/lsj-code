package com.lsj.core.spring.grpc.discovery.etcd.util;

import com.lsj.commonutil.util.common.JsonUtil;
import io.etcd.jetcd.ByteSequence;

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


    public static String buildServicePrefix(String namespace, String group) {
        return "/" + namespace + "/" + group + "/";
    }

    public static String buildServiceKey(String namespace, String group, String serviceId) {
        return buildServicePrefix(namespace, group) + serviceId;
    }
}
