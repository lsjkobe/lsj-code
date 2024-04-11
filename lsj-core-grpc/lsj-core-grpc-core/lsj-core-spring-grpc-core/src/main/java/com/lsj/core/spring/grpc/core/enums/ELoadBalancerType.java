package com.lsj.core.spring.grpc.core.enums;

import lombok.Getter;

/**
 * @author lishangjian
 * @date 2024/4/10 下午5:11
 */
@Getter
public enum ELoadBalancerType implements LoadBalancerType {
    ROUND("Round"),
    ;

    ELoadBalancerType(String type) {
        this.type = type;
    }

    private final String type;
}
