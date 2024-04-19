package com.lsj.core.spring.grpc.server.nacos.serviceregistry;

import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.lsj.core.spring.grpc.core.enums.EDiscoveryType;
import com.lsj.core.spring.grpc.discovery.nacos.config.LsjGRpcNacosNameServiceManager;
import com.lsj.core.spring.grpc.discovery.nacos.config.properties.LsjGRpcServerDiscoveryNacosProperties;
import com.lsj.core.spring.grpc.server.serviceregistry.LsjGRpcBaseServiceRegistrant;
import com.lsj.core.spring.grpc.server.serviceregistry.LsjGRpcRegistration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

/**
 * @author lishangjian
 * @date 2024/3/30 17:56
 */

public class LsjGRpcNacosServiceRegistrant extends LsjGRpcBaseServiceRegistrant<LsjGRpcRegistration> {

    private final static Logger logger = LoggerFactory.getLogger(LsjGRpcNacosServiceRegistrant.class);

    private final LsjGRpcServerDiscoveryNacosProperties discoveryNacosProperties;

    private final LsjGRpcNacosNameServiceManager lsjGRpcNacosNameServiceManager;

    public LsjGRpcNacosServiceRegistrant(
            LsjGRpcServerDiscoveryNacosProperties discoveryNacosProperties,
            LsjGRpcNacosNameServiceManager lsjGRpcNacosNameServiceManager) {
        this.discoveryNacosProperties = discoveryNacosProperties;
        this.lsjGRpcNacosNameServiceManager = lsjGRpcNacosNameServiceManager;
    }

    @Override
    protected void doRegister(LsjGRpcRegistration registration,
                              Consumer<LsjGRpcRegistration> offlineCallback) {
        if (StringUtils.isEmpty(registration.getServiceId())) {
//            logger.warn("No service to register for nacos client...");
            return;
        }

        NamingService namingService = namingService();
        String serviceId = registration.getServiceId();
        String group = discoveryNacosProperties.getGroup();

        Instance instance = getNacosInstanceFromRegistration(registration);

        try {
            namingService.registerInstance(serviceId, group, instance);
            logger.info("nacos registry, {} {} {}:{} register finished", group, serviceId,
                    instance.getIp(), instance.getPort());
        } catch (Exception e) {
            logger.error("nacos registry, {} register failed...{},", serviceId,
                    registration, e);
            rethrowRuntimeException(e);
        }
    }

    private Instance getNacosInstanceFromRegistration(LsjGRpcRegistration registration) {
        Instance instance = new Instance();
        instance.setIp(registration.getHost());
        instance.setPort(registration.getPort());
        instance.setWeight(discoveryNacosProperties.getWeight());
        instance.setClusterName(discoveryNacosProperties.getClusterName());
        instance.setEnabled(discoveryNacosProperties.isInstanceEnabled());
        instance.setMetadata(registration.getMetadata());
        instance.setEphemeral(discoveryNacosProperties.isEphemeral());
        return instance;
    }

    protected NamingService namingService() {
        return lsjGRpcNacosNameServiceManager.getNamingService();
    }

    @Override
    protected void doDeregister(LsjGRpcRegistration registration) {

    }

    @Override
    protected LsjGRpcRegistration getCustomRegistration(LsjGRpcRegistration registration) {
        registration.setHost(discoveryNacosProperties.getIp());
        return registration;
    }

    /**
     * 类型.
     *
     * @return .
     */
    @Override
    public EDiscoveryType type() {
        return EDiscoveryType.NACOS;
    }
}
