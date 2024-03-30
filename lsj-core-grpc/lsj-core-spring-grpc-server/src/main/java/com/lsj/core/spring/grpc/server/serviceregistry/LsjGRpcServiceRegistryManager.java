package com.lsj.core.spring.grpc.server.serviceregistry;

import com.lsj.core.spring.grpc.server.config.properties.LsjGRpcServerProperties;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author lishangjian
 * @date 2024/3/30 11:54
 */
public class LsjGRpcServiceRegistryManager {

    private final LsjGRpcServerProperties properties;

    private List<LsjGRpcRegistration> registrationList;

    private List<LsjGRpcBaseServiceRegistrant<? extends LsjGRpcRegistration>> registrantList;

    public LsjGRpcServiceRegistryManager(LsjGRpcServerProperties properties,
                                         List<LsjGRpcBaseServiceRegistrant<? extends LsjGRpcRegistration>> registrantList) {
        this.properties = properties;
        this.registrantList = registrantList;
    }

    public void setRegistrationList(List<LsjGRpcRegistration> registrationList) {
        this.registrationList = registrationList;
    }

    public void register() {
        if (CollectionUtils.isEmpty(registrantList)) {
            return;
        }
        for (LsjGRpcBaseServiceRegistrant<? extends LsjGRpcRegistration> registrant : registrantList) {

        }
    }
}
