package com.lsj.core.spring.grpc.server.helper;

import com.lsj.core.spring.grpc.core.properties.LsjGRpcProperties;
import com.lsj.core.spring.grpc.server.serviceregistry.LsjGRpcRegistration;
import com.lsj.core.spring.grpc.server.serviceregistry.LsjGRpcServiceRegistryManager;
import com.lsj.core.spring.grpc.core.util.LsjGRpcDiscoveryUtil;
import io.grpc.BindableService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lishangjian
 * @date 2024/3/29 17:29
 */
public class LsjGRpcRegistryHelper {

    private final static Log logger = LogFactory.getLog(LsjGRpcRegistryHelper.class);

    private final LsjGRpcProperties properties;

    private final LsjGRpcServiceRegistryManager lsjGRpcServiceRegistryManager;

    private final Environment env;

    public LsjGRpcRegistryHelper(
            LsjGRpcProperties properties,
            LsjGRpcServiceRegistryManager lsjGRpcServiceRegistryManager,
            Environment env) {
        this.properties = properties;
        this.lsjGRpcServiceRegistryManager = lsjGRpcServiceRegistryManager;
        this.env = env;
    }

    public void registry(Integer port, Map<String, BindableService> curGrpcServiceMap) {
        List<LsjGRpcRegistration> registrationList = toRegistration(port, curGrpcServiceMap);
        lsjGRpcServiceRegistryManager.setRegistrationList(registrationList);
        lsjGRpcServiceRegistryManager.register();
    }

    protected List<LsjGRpcRegistration> toRegistration(Integer port, Map<String, BindableService> curGrpcServiceMap) {
        List<LsjGRpcRegistration> registrationList = new ArrayList<>();
        for (String name : curGrpcServiceMap.keySet()) {
            BindableService bindableService = curGrpcServiceMap.get(name);
            registrationList.add(toRegistration(name, port, bindableService));
        }
        return registrationList;
    }

    protected LsjGRpcRegistration toRegistration(String name, Integer port, BindableService service) {
        LsjGRpcRegistration registration = new LsjGRpcRegistration();
        String serviceId = LsjGRpcDiscoveryUtil.buildServiceId(name, env, properties);
        registration.setServiceId(serviceId);
        registration.setPort(port);
        return registration;
    }
}
