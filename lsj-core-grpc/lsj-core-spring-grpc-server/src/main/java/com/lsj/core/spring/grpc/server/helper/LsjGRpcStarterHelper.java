package com.lsj.core.spring.grpc.server.helper;

import com.lsj.core.spring.grpc.core.properties.LsjGRpcProperties;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lishangjian
 * @date 2024/3/29 17:29
 */
public class LsjGRpcStarterHelper implements Closeable {

    private final static Log logger = LogFactory.getLog(LsjGRpcStarterHelper.class);

    private final LsjGRpcProperties properties;

    LsjGRpcRegistryHelper lsjGRpcRegistryHelper;

    private Map<String, BindableService> grpcServiceMap;

    private Server grpcServer;

    public LsjGRpcStarterHelper(
            LsjGRpcProperties properties,
            LsjGRpcRegistryHelper lsjGRpcRegistryHelper) {
        this.properties = properties;
        this.lsjGRpcRegistryHelper = lsjGRpcRegistryHelper;
    }

    public void setServiceList(Map<String, BindableService> grpcServiceMap) {
        this.grpcServiceMap = grpcServiceMap;
    }

    public void start() throws Exception {
        Integer port = properties.getPort();
        ServerBuilder<?> serverBuilder = ServerBuilder
                .forPort(port);
        for (BindableService bindableService : grpcServiceMap.values()) {
            serverBuilder.addService(bindableService);
        }
        grpcServer = serverBuilder.build().start();
        logger.info("GRpc server started, port : " + port);
        lsjGRpcRegistryHelper.registry(grpcServiceMap);
        grpcServer.awaitTermination();
    }

    public void start(Map<String, BindableService> curGrpcServiceMap) throws Exception {
        if (grpcServiceMap == null) {
            grpcServiceMap = new HashMap<>();
        }
        grpcServiceMap.putAll(curGrpcServiceMap);
        start();
    }

    @Override
    public void close() {
        if (grpcServer == null) {
            return;
        }
        if (!grpcServer.isTerminated()) {
            grpcServer.shutdown();
        }
    }
}
