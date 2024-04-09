package com.lsj.core.spring.grpc.server.config;

import com.lsj.core.spring.grpc.core.annotation.LsjGRpcService;
import com.lsj.core.spring.grpc.server.annotation.EnableLsjGRpc;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author lishangjian
 * @date 2024/3/29 11:17
 */
public class LsjGRpcComponentScanRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(EnableLsjGRpc.class.getName());
        String[] grpcServicePath = (String[]) annotationAttributes.get("grpcServicePath");

        // 扫描添加了@LsjGRpcService注解的类
        for (String packagePath : grpcServicePath) {
            ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
            scanner.addIncludeFilter(new AnnotationTypeFilter(LsjGRpcService.class));
            // 扫描包路径下的所有类
            Set<BeanDefinition> beanDefinitions = scanner.findCandidateComponents(packagePath);
            try {
                for (BeanDefinition beanDefinition : beanDefinitions) {
                    // 获取类名
                    String beanClassName = Objects.requireNonNull(beanDefinition.getBeanClassName());
                    // 加载类
                    Class<?> clazz = Class.forName(beanClassName);
                    String beanName;
                    // 获取注解
                    LsjGRpcService lsjGRpcServiceAnnotation = clazz.getAnnotation(LsjGRpcService.class);
                    if (StringUtils.isNotBlank(lsjGRpcServiceAnnotation.value())) {
                        beanName = lsjGRpcServiceAnnotation.value();
                    } else {
                        String className = ClassUtils.getShortName(beanClassName);
                        beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
                    }
                    // 将扫描到的bean注册到Spring容器
                    registry.registerBeanDefinition(beanName, beanDefinition);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
