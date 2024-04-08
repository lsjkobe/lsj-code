package com.lsj.core.spring.grpc.server.config;

import com.lsj.core.spring.grpc.server.helper.LsjGRpcStarterHelper;
import com.lsj.core.spring.grpc.server.util.SpringContextUtil;
import io.grpc.BindableService;
import jakarta.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.Map;

/**
 * @author lishangjian
 * @date 2024/3/29 16:09
 */
@DependsOn(SpringContextUtil.BEAN_NAME)
@Configuration
public class LsjGRpcServiceConfig {

    private final static Log logger = LogFactory.getLog(LsjGRpcServiceConfig.class);

    @Autowired
    private LsjGRpcStarterHelper lsjGRpcStarterHelper;

    // 这个方法可以用于初始化服务或者进行其他操作
    @PostConstruct
    public void initServices() {
        Map<String, BindableService> grpcServiceList = SpringContextUtil.getBeansOfType(BindableService.class);
        try {
            lsjGRpcStarterHelper.start(grpcServiceList);
        } catch (Exception e) {
            logger.error("GRpc 服务启动失败", e);
            throw new RuntimeException(e);
        }
    }
}
