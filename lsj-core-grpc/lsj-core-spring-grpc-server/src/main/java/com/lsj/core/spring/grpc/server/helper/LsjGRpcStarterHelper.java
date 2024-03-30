package com.lsj.core.spring.grpc.server.helper;

import com.lsj.core.spring.grpc.server.config.properties.LsjGRpcServerProperties;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lishangjian
 * @date 2024/3/29 17:29
 */
public class LsjGRpcStarterHelper {

    private final static Log logger = LogFactory.getLog(LsjGRpcStarterHelper.class);

    private final LsjGRpcServerProperties properties;

    List<BindableService> serviceList;

    public LsjGRpcStarterHelper(LsjGRpcServerProperties properties) {
        this.properties = properties;
    }

    public void setServiceList(List<BindableService> serviceList) {
        this.serviceList = serviceList;
    }

    public void start() throws Exception {
        Integer port = properties.getPort();
        ServerBuilder<?> serverBuilder = ServerBuilder
                .forPort(port);
        for (BindableService bindableService : serviceList) {
            serverBuilder.addService(bindableService);
        }
        Server server = serverBuilder.build().start();
        logger.info("GRpc server started, port : " + port);
        server.awaitTermination();
    }

    public void start(Collection<BindableService> curServiceList) throws Exception {
        if (serviceList == null) {
            serviceList = new ArrayList<>();
        }
        serviceList.addAll(curServiceList);
        start();
    }
}
