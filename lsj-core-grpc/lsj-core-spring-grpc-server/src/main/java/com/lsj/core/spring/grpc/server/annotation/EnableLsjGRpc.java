package com.lsj.core.spring.grpc.server.annotation;

import com.lsj.core.spring.grpc.server.config.LsjGRpcAllConfig;
import com.lsj.core.spring.grpc.server.config.LsjGRpcComponentScanRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author lishangjian
 * @date 2024/3/29 11:54
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@ComponentScan(
//        basePackages = "取grpcServicePath的值",
//        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = LsjGRpcService.class)
//)
@Import({LsjGRpcComponentScanRegistrar.class, LsjGRpcAllConfig.class})
public @interface EnableLsjGRpc {

    /**
     * Alias for {@link #grpcServicePath}.
     * <p>Allows for more concise annotation declarations if no other attributes
     * are needed &mdash; for example, {@code @ComponentScan("org.my.pkg")}
     * instead of {@code @ComponentScan(basePackages = "org.my.pkg")}.
     */
    @AliasFor("grpcServicePath")
    String[] value() default {};

    /**
     * Base packages to scan for annotated components.
     * <p>{@link #value} is an alias for (and mutually exclusive with) this
     * attribute.
     * <p>Use {@link #grpcServicePath} for a type-safe alternative to
     * String-based package names.
     */
    @AliasFor("value")
    String[] grpcServicePath() default {};
}
