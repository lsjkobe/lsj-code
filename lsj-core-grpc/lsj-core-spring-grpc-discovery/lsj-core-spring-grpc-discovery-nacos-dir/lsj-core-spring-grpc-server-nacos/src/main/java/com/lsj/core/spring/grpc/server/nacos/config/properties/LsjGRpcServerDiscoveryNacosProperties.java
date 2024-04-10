package com.lsj.core.spring.grpc.server.nacos.config.properties;

import com.alibaba.nacos.api.naming.PreservedMetadataKeys;
import com.alibaba.nacos.client.naming.utils.UtilAndComs;
import com.lsj.commonutil.util.common.InetUtils;
import com.lsj.core.spring.grpc.core.properties.LsjGRpcDiscoveryInfoProperties;
import com.lsj.core.spring.grpc.server.consts.LsjGRpcServerConst;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import static com.alibaba.nacos.api.PropertyKeyConst.*;

/**
 * @author lishangjian
 * @date 2024/3/30 18:18
 */
@ConfigurationProperties(value = LsjGRpcServerConst.CONST_DISCOVERY_PROPERTIES_PATH + ".nacos")
public class LsjGRpcServerDiscoveryNacosProperties extends LsjGRpcDiscoveryInfoProperties {

    /**
     * the domain name of a service, through which the server address can be dynamically
     * obtained.
     */
    private String endpoint;

    /**
     * namespace, separation registry of different environments.
     */
    private String namespace;

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
     * group name for nacos.
     */
    private String group = "DEFAULT_GROUP";

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
        properties.put(SERVER_ADDR, getHost());
        properties.put(USERNAME, Objects.toString(getUserName(), ""));
        properties.put(PASSWORD, Objects.toString(getUserPwd(), ""));
        properties.put(NAMESPACE, namespace);
        properties.put(UtilAndComs.NACOS_NAMING_LOG_NAME, logName);

        if (endpoint.contains(":")) {
            int index = endpoint.indexOf(":");
            properties.put(ENDPOINT, endpoint.substring(0, index));
            properties.put(ENDPOINT_PORT, endpoint.substring(index + 1));
        } else {
            properties.put(ENDPOINT, endpoint);
        }

        properties.put(ACCESS_KEY, accessKey);
        properties.put(SECRET_KEY, secretKey);
        properties.put(CLUSTER_NAME, clusterName);
        properties.put(NAMING_LOAD_CACHE_AT_START, namingLoadCacheAtStart);

        return properties;
    }

    @PostConstruct
    public void init() throws Exception {
        metadata.put(PreservedMetadataKeys.REGISTER_SOURCE, "SPRING_CLOUD");
        if (secure) {
            metadata.put("secure", "true");
        }

        String serverAddr = Objects.toString(getHost(), "");
        if (serverAddr.endsWith("/")) {
            serverAddr = serverAddr.substring(0, serverAddr.length() - 1);
        }
        setHost(serverAddr);
        endpoint = Objects.toString(endpoint, "");
        namespace = Objects.toString(namespace, "");
        logName = Objects.toString(logName, "");
        accessKey = Objects.toString(accessKey, "");
        secretKey = Objects.toString(secretKey, "");
        if (StringUtils.isEmpty(getIp())) {
            String ip = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
            setIp(ip);
        }
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getNamingLoadCacheAtStart() {
        return namingLoadCacheAtStart;
    }

    public void setNamingLoadCacheAtStart(String namingLoadCacheAtStart) {
        this.namingLoadCacheAtStart = namingLoadCacheAtStart;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public boolean isEphemeral() {
        return ephemeral;
    }

    public void setEphemeral(boolean ephemeral) {
        this.ephemeral = ephemeral;
    }

    public boolean isInstanceEnabled() {
        return instanceEnabled;
    }

    public void setInstanceEnabled(boolean instanceEnabled) {
        this.instanceEnabled = instanceEnabled;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
