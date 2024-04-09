package com.lsj.core.spring.grpc.core.annotation;

import java.lang.annotation.*;

/**
 * @author lishangjian
 * @date 2024/3/28 11:48
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LsjGRpcService {

    /**
     * 服务名称
     */
    String value() default "";
}
