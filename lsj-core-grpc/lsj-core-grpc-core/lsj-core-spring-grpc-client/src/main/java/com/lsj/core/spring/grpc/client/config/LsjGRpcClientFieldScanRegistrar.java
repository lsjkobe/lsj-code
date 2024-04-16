package com.lsj.core.spring.grpc.client.config;

import com.lsj.core.spring.grpc.core.annotation.LsjGRpcClient;
import io.grpc.ManagedChannel;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lishangjian
 * @date 2024/4/8 17:25
 * 该类不做处理，只是留下来，以后可能用得着
 */
public class LsjGRpcClientFieldScanRegistrar implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(LsjGRpcClient.class)) {
                    // 处理带有@LsjGRpcClient注解的字段
                    field.setAccessible(true); // 允许访问私有字段
                    // 这里可以添加处理逻辑，例如获取注解的值，或者调用特定的方法
                    // 例如，获取注解实例
                    LsjGRpcClient lsjGRpcClient = field.getAnnotation(LsjGRpcClient.class);
                    try {
                        Class<?>[] argumentTypes = buildProxyArgumentTypes();
                        Object[] arguments = buildProxyArguments(lsjGRpcClient);
                        field.set(bean, getProxy(clazz, field, argumentTypes, arguments));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
        return bean;
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

    private Object getProxy(Class<?> clazz, Field field, Class<?>[] argumentTypes, Object[] arguments) {
        Class<?> fieldClass = field.getType();
        if (field.getType().isInterface()) {
            // 根据注解的属性执行相应的操作
            return Proxy.newProxyInstance(clazz.getClassLoader(),
                    new Class[]{fieldClass}, new GrpcClientInvocationHandler(null, fieldClass));
        } else {
            Enhancer enhancer = new Enhancer();
            //Cglib代理基于创建子类重写父类方法实现，所以这里要确定父类，也就是被代理类。
            enhancer.setSuperclass(fieldClass);
            /*
            创建了一个MethodInterceptor拦截器接口的实现类对象，重写intercept回调方法，
            参数依次为：代理对象、代理方法、方法参数、方法代理
            */
            MethodInterceptor interceptor = (o, method, objects, methodProxy) -> {
                System.out.println("这是前置增强");
                Object res = methodProxy.invokeSuper(o, objects);
                System.out.println("这是后置增强");
                return res;
            };
            enhancer.setCallback(interceptor);
            return enhancer.create(argumentTypes, arguments);
        }
    }

    private static class GrpcClientInvocationHandler implements InvocationHandler {
        private final ManagedChannel channel;
        private final Class<?> stubClass;

        public GrpcClientInvocationHandler(ManagedChannel channel, Class<?> stubClass) {
            this.channel = channel;
            this.stubClass = stubClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // 根据方法名和参数，调用gRPC服务端的方法
            // 这里需要根据实际情况生成代理对象的方法调用逻辑
            return null;
        }
    }

    @PostConstruct
    void init() {

    }
}
