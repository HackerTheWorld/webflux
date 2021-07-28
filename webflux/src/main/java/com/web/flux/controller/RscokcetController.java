package com.web.flux.controller;

import io.rsocket.transport.ClientTransport;
import org.springframework.messaging.rsocket.RSocketConnectorConfigurer;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PreDestroy;
import java.time.Duration;

@RestController
@RequestMapping("/rsocket")
public class RscokcetController {

    private final RSocketRequester rsocketRequester;
    private static Disposable disposable;

    public RscokcetController(RSocketRequester.Builder rsocketRequesterBuilder, RSocketStrategies strategies) {
        ClientTransport clientTransport = null;
        RSocketConnectorConfigurer rSocketConnectorConfigurer = null;
        this.rsocketRequester = rsocketRequesterBuilder
                .rsocketStrategies(strategies)
                .connectTcp("127.0.0.1",7000)
                .block();
        this.rsocketRequester.rsocket()
                .onClose()
                .doOnError(error -> System.out.println("发生错误，链接关闭"))
                .doFinally(consumer -> System.out.println("链接关闭"))
                .subscribe();
    }

    @PreDestroy
    void shutdown() {
        rsocketRequester.rsocket().dispose();
    }

    @GetMapping("requestResponse")
    public Mono<String> requestResponse() {
        return this.rsocketRequester
                .route("requestResponse")
                .data("客户端 服务器")
                .retrieveMono(String.class);
    }

    @GetMapping("fireAndForget")
    public String fireAndForget() {
        this.rsocketRequester
                .route("fireAndForget")
                .data("客户端 服务器")
                .send();
        return "fire and forget";
    }

    @GetMapping("stream")
    public String stream() {
        disposable = this.rsocketRequester
                .route("stream")
                .data("stream 客户端")
                .retrieveFlux(String.class)
                .subscribe(message -> System.out.println("客户端stream收到响应 "+message));
        return "stream";
    }

    @GetMapping("channel")
    public String channel() {
        Mono<Duration> setting1 = Mono.just(Duration.ofSeconds(1));
        Mono<Duration> setting2 = Mono.just(Duration.ofSeconds(3)).delayElement(Duration.ofSeconds(5));
        Mono<Duration> setting3 = Mono.just(Duration.ofSeconds(5)).delayElement(Duration.ofSeconds(15));
        Flux<Duration> settings = Flux.concat(setting1, setting2, setting3)
                .doOnNext(d -> System.out.println("客户端channel发送消息 {}"+d.getSeconds()));
        disposable = this.rsocketRequester
                .route("channel")
                .data(settings)
                .retrieveFlux(String.class)
                .subscribe(message -> System.out.println("客户端channel收到响应 {}"+message));
        return "channel";
    }

}

