package com.lsj.core.spring.grpc.server.nacos.serviceregistry;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.lsj.core.spring.grpc.server.nacos.config.properties.LsjGRpcServerDiscoveryNacosProperties;

import java.util.Objects;
import java.util.Properties;

/**
 * @author lishangjian
 * @date 2024/4/1 14:41
 */
public class LsjGRpcNacosNameServiceManager {

    private volatile NamingService namingService;

    private final LsjGRpcServerDiscoveryNacosProperties discoveryNacosProperties;

    public LsjGRpcNacosNameServiceManager(LsjGRpcServerDiscoveryNacosProperties discoveryNacosProperties) {
        this.discoveryNacosProperties = discoveryNacosProperties;
    }

    public NamingService getNamingService() {
        if (Objects.isNull(this.namingService)) {
            buildNamingService(discoveryNacosProperties.getNacosProperties());
        }
        return namingService;
    }

    private NamingService buildNamingService(Properties properties) {
        if (Objects.isNull(namingService)) {
            synchronized (LsjGRpcNacosNameServiceManager.class) {
                if (Objects.isNull(namingService)) {
                    namingService = createNewNamingService(properties);
                }
            }
        }
        return namingService;
    }

    private NamingService createNewNamingService(Properties properties) {
        try {
            return createNamingService(properties);
        }
        catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Create naming service.
     *
     * @param properties init param
     * @return Naming
     * @throws NacosException Exception
     */
    public static NamingService createNamingService(Properties properties) throws NacosException {
        return NamingFactory.createNamingService(properties);
    }
}
