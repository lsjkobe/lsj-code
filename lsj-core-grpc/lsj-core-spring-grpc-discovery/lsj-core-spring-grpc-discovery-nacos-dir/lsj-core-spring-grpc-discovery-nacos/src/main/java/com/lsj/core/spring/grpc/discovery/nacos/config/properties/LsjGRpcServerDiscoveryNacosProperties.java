package com.lsj.core.spring.grpc.discovery.nacos.config.properties;


import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.naming.PreservedMetadataKeys;
import com.alibaba.nacos.client.naming.utils.UtilAndComs;
import com.lsj.core.spring.grpc.core.consts.LsjGRpcConst;
import com.lsj.core.spring.grpc.core.properties.LsjGRpcDiscoveryInfoProperties;
import com.lsj.core.spring.grpc.core.util.InetUtils;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * @author lishangjian
 * @date 2024/3/30 18:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ConfigurationProperties(value = LsjGRpcConst.CONST_DISCOVERY_PROPERTIES_PATH + ".nacos")
public class LsjGRpcServerDiscoveryNacosProperties extends LsjGRpcDiscoveryInfoProperties {

    /**
     * the domain name of a service, through which the server address can be dynamically
     * obtained.
     */
    private String endpoint;

    private String logName;

    /**
     * whether your service is a https service.
     */
    private boolean secure = false;

    /**
     * access key for namespace.
     */
    private String accessKey;

    /**
     * secret key for namespace.
     */
    private String secretKey;

    /**
     * cluster name for nacos .
     */
    private String clusterName = "DEFAULT";

    /**
     * naming load from local cache at application start. true is load.
     */
    private String namingLoadCacheAtStart = "false";

    /**
     * If instance is ephemeral.
     *
     * @since 1.0.0
     */
    private boolean ephemeral = true;


    /**
     * If instance is enabled to accept request. The default value is true.
     */
    private boolean instanceEnabled = true;

    /**
     * extra metadata to register.
     */
    private Map<String, String> metadata = new HashMap<>();

    @Autowired
    private InetUtils inetUtils;

    public Properties getNacosProperties() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, getHost());
        properties.put(PropertyKeyConst.USERNAME, Objects.toString(getUserName(), ""));
        properties.put(PropertyKeyConst.PASSWORD, Objects.toString(getUserPwd(), ""));
        properties.put(PropertyKeyConst.NAMESPACE, getNamespace());
        properties.put(UtilAndComs.NACOS_NAMING_LOG_NAME, logName);

        if (endpoint.contains(":")) {
            int index = endpoint.indexOf(":");
            properties.put(PropertyKeyConst.ENDPOINT, endpoint.substring(0, index));
            properties.put(PropertyKeyConst.ENDPOINT_PORT, endpoint.substring(index + 1));
        } else {
            properties.put(PropertyKeyConst.ENDPOINT, endpoint);
        }

        properties.put(PropertyKeyConst.ACCESS_KEY, accessKey);
        properties.put(PropertyKeyConst.SECRET_KEY, secretKey);
        properties.put(PropertyKeyConst.CLUSTER_NAME, clusterName);
        properties.put(PropertyKeyConst.NAMING_LOAD_CACHE_AT_START, namingLoadCacheAtStart);

        return properties;
    }

    @PostConstruct
    public void init() throws Exception {
        metadata.put(PreservedMetadataKeys.REGISTER_SOURCE, "LSJ_GRPC");
        if (secure) {
            metadata.put("secure", "true");
        }

        String serverAddr = Objects.toString(getHost(), "");
        if (serverAddr.endsWith("/")) {
            serverAddr = serverAddr.substring(0, serverAddr.length() - 1);
        }
        setHost(serverAddr);
        endpoint = Objects.toString(endpoint, "");
        String namespace = Objects.toString(getNamespace(), "");
        setNamespace(namespace);
        logName = Objects.toString(logName, "");
        accessKey = Objects.toString(accessKey, "");
        secretKey = Objects.toString(secretKey, "");
        if (StringUtils.isEmpty(getIp())) {
            String ip = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
            setIp(ip);
        }
    }

}
