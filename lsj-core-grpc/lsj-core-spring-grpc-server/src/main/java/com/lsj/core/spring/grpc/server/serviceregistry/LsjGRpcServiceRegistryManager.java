package com.lsj.core.spring.grpc.server.serviceregistry;

import com.lsj.core.spring.grpc.discovery.config.properties.LsjGRpcProperties;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author lishangjian
 * @date 2024/3/30 11:54
 */
public class LsjGRpcServiceRegistryManager {

    private final static Log logger = LogFactory.getLog(LsjGRpcServiceRegistryManager.class);

    private final LsjGRpcProperties properties;

    private List<LsjGRpcRegistration> registrationList;

    private List<ILsjGRpcServiceRegistrant> registrantList;

    public LsjGRpcServiceRegistryManager(LsjGRpcProperties properties,
                                         List<ILsjGRpcServiceRegistrant> registrantList) {
        this.properties = properties;
        this.registrantList = registrantList;
    }

    public void setRegistrationList(List<LsjGRpcRegistration> registrationList) {
        this.registrationList = registrationList;
    }

    public void register() {
        if (CollectionUtils.isEmpty(registrationList)) {
            logger.info("GRPC服务注册 需要注册的服务为空");
            return;
        }
        if (CollectionUtils.isEmpty(registrantList)) {
            logger.info("GRPC服务注册 服务发现组件未定义");
            return;
        }
        for (ILsjGRpcServiceRegistrant registrant : registrantList) {
            for (LsjGRpcRegistration lsjGRpcRegistration : registrationList) {
                registrant.register(lsjGRpcRegistration);
            }
        }
    }
}
