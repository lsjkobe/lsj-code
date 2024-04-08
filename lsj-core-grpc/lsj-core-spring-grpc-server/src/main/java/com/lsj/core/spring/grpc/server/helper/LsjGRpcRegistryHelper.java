package com.lsj.core.spring.grpc.server.helper;

import com.lsj.core.spring.grpc.server.config.properties.LsjGRpcServerProperties;
import com.lsj.core.spring.grpc.server.serviceregistry.LsjGRpcRegistration;
import com.lsj.core.spring.grpc.server.serviceregistry.LsjGRpcServiceRegistryManager;
import io.grpc.BindableService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lishangjian
 * @date 2024/3/29 17:29
 */
public class LsjGRpcRegistryHelper {

    private final static Log logger = LogFactory.getLog(LsjGRpcRegistryHelper.class);

    private final LsjGRpcServerProperties properties;

    private final LsjGRpcServiceRegistryManager lsjGRpcServiceRegistryManager;

    public LsjGRpcRegistryHelper(
            LsjGRpcServerProperties properties,
            LsjGRpcServiceRegistryManager lsjGRpcServiceRegistryManager) {
        this.properties = properties;
        this.lsjGRpcServiceRegistryManager = lsjGRpcServiceRegistryManager;
    }

    public void registry(Map<String, BindableService> curGrpcServiceMap) {
        List<LsjGRpcRegistration> registrationList = toRegistration(curGrpcServiceMap);
        lsjGRpcServiceRegistryManager.setRegistrationList(registrationList);
        lsjGRpcServiceRegistryManager.register();
    }

    protected List<LsjGRpcRegistration> toRegistration(Map<String, BindableService> curGrpcServiceMap) {
        List<LsjGRpcRegistration> registrationList = new ArrayList<>();
        for (String name : curGrpcServiceMap.keySet()) {
            BindableService bindableService = curGrpcServiceMap.get(name);
            registrationList.add(toRegistration(name, bindableService));
        }
        return registrationList;
    }

    protected LsjGRpcRegistration toRegistration(String name, BindableService service) {
        LsjGRpcRegistration registration = new LsjGRpcRegistration();
        registration.setServiceId(name);
        return registration;
    }
}
