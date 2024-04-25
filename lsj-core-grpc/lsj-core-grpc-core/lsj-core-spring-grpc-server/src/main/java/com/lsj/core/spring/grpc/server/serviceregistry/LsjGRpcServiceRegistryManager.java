package com.lsj.core.spring.grpc.server.serviceregistry;

import com.lsj.core.spring.grpc.core.properties.LsjGRpcProperties;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

/**
 * @author lishangjian
 * @date 2024/3/30 11:54
 */
@Slf4j
@Setter
public class LsjGRpcServiceRegistryManager {

    private final static Log logger = LogFactory.getLog(LsjGRpcServiceRegistryManager.class);

    private final LsjGRpcProperties properties;

    private List<LsjGRpcRegistration> registrationList;

    private List<ILsjGRpcServiceRegistrant> registrantList;

    public LsjGRpcServiceRegistryManager(LsjGRpcProperties properties,
                                         List<ILsjGRpcServiceRegistrant> registrantList) {
        this.properties = properties;
        this.registrantList = registrantList;
    }

    public void register() {
        if (CollectionUtils.isEmpty(registrationList)) {
            logger.info("GRPC服务注册 需要注册的服务为空");
            return;
        }
        if (CollectionUtils.isEmpty(registrantList)) {
            logger.info("GRPC服务注册 服务发现组件未定义");
            return;
        }
        try {
            log.info("服务发现注册开始");
            Flux<ILsjGRpcServiceRegistrant> fluxRegistrant = Flux.fromIterable(registrantList)
                    .doOnNext(registrant -> log.info("[{}]服务发现组件注册开始", registrant.type().name()))
                    .flatMap(registrant -> Mono.just(registrant)
                            .flatMap(this::serviceRegistrantMono)
                            .subscribeOn(Schedulers.parallel())
                            .doOnNext(monoRegistrant -> log.info("[{}]服务发现组件注册完成", monoRegistrant.type().name()))
                    );
            fluxRegistrant.blockLast();
            log.info("服务发现注册完成");
        } catch (Exception e) {
            log.error("服务发现注册失败结束", e);
            throw new RuntimeException(e);
        }

    }

    public Mono<ILsjGRpcServiceRegistrant> serviceRegistrantMono(ILsjGRpcServiceRegistrant registrant) {
        return Flux.fromIterable(registrationList)
                .flatMap(re -> Mono.fromRunnable(() -> {
                                    registrant.register(re);
                                    log.info("[{}]:服务[{}]已注册", registrant.type().name(), re.getServiceId());
                                })
                                .onErrorResume(error -> {
                                    log.error("[{}]:[{}]服务注册失败", registrant.type().name(), re.getServiceId(), error);
                                    return Mono.error(error);
                                })
                                .subscribeOn(Schedulers.parallel())
                )
                .then(Mono.just(registrant));
    }
}
