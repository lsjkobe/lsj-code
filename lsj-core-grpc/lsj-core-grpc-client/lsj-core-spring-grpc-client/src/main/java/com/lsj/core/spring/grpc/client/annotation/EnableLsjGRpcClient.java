package com.lsj.core.spring.grpc.client.annotation;

import com.lsj.core.spring.grpc.client.config.LsjGRpcClientAllConfig;
import com.lsj.core.spring.grpc.client.config.LsjGRpcClientComponentScanRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author lishangjian
 * @date 2024/3/29 11:54
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({LsjGRpcClientComponentScanRegistrar.class, LsjGRpcClientAllConfig.class})
public @interface EnableLsjGRpcClient {

}
