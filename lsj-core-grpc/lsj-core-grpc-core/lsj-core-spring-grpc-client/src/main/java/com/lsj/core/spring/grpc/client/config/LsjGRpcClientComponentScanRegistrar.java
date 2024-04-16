package com.lsj.core.spring.grpc.client.config;

import com.lsj.core.spring.grpc.client.annotation.EnableLsjGRpcClient;
import com.lsj.core.spring.grpc.core.annotation.LsjGRpcClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author lishangjian
 * @date 2024/4/8 17:25
 */
public class LsjGRpcClientComponentScanRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(EnableLsjGRpcClient.class.getName());
        Set<String> basePackages = getBasePackages(metadata, annotationAttributes);
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(LsjGRpcClient.class));
        // 扫描添加了@LsjGRpcClient注解的类
        for (String packagePath : basePackages) {
            registerBeanFromPackage(scanner, packagePath, registry);
        }
    }

    private void registerBeanFromPackage(
            ClassPathScanningCandidateComponentProvider scanner,
            String packagePath, BeanDefinitionRegistry registry) {
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
                LsjGRpcClient lsjGRpcServiceAnnotation = clazz.getAnnotation(LsjGRpcClient.class);
                if (StringUtils.isNotBlank(lsjGRpcServiceAnnotation.value())) {
                    beanName = lsjGRpcServiceAnnotation.value();
                } else {
                    String className = ClassUtils.getShortName(beanClassName);
                    beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
                }
                Class<?>[] argumentTypes = buildProxyArgumentTypes();
                Object[] arguments = buildProxyArguments(lsjGRpcServiceAnnotation);
                // 创建BeanDefinition
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
                // 添加构造参数
                for (int i = 0; i < argumentTypes.length; i++) {
                    beanDefinitionBuilder.addConstructorArgValue(arguments[i]);
                }
                // 将扫描到的bean注册到Spring容器
                registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Set<String> getBasePackages(AnnotationMetadata metadata, Map<String, Object> attributes) {
        Set<String> basePackages = new HashSet<>();
        for (String pkg : (String[]) attributes.get("basePackages")) {
            if (StringUtils.isNotBlank(pkg)) {
                basePackages.add(pkg);
            }
        }
        if (basePackages.isEmpty()) {
            basePackages.add(
                    ClassUtils.getPackageName(metadata.getClassName()));
        }
        return basePackages;
    }

    private Class<?>[] buildProxyArgumentTypes() {
        Class<?>[] clzs = new Class[2];
        clzs[0] = String.class;
        clzs[1] = String.class;
        return clzs;
    }

    private Object[] buildProxyArguments(LsjGRpcClient lsjGRpcClient) {
        Object[] objects = new Object[2];
        objects[0] = lsjGRpcClient.serviceName();
        objects[1] = lsjGRpcClient.componentId();
        return objects;
    }
}
