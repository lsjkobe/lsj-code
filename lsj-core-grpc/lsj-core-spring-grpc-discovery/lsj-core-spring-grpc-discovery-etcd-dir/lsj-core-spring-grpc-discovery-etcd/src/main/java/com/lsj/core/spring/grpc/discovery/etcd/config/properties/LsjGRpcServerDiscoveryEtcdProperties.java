package com.lsj.core.spring.grpc.discovery.etcd.config.properties;


import com.lsj.core.spring.grpc.core.consts.LsjGRpcConst;
import com.lsj.core.spring.grpc.core.properties.LsjGRpcDiscoveryInfoProperties;
import com.lsj.core.spring.grpc.core.util.InetUtils;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

/**
 * @author lishangjian
 * @date 2024/3/30 18:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ConfigurationProperties(value = LsjGRpcConst.CONST_DISCOVERY_PROPERTIES_PATH + ".etcd")
public class LsjGRpcServerDiscoveryEtcdProperties extends LsjGRpcDiscoveryInfoProperties {

    @Autowired
    private InetUtils inetUtils;

    @PostConstruct
    public void init() {
        String serverAddr = Objects.toString(getHost(), "");
        if (serverAddr.endsWith("/")) {
            serverAddr = serverAddr.substring(0, serverAddr.length() - 1);
        }
        setHost(serverAddr);
        if (StringUtils.isEmpty(getIp())) {
            String ip = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
            setIp(ip);
        }
    }
}
