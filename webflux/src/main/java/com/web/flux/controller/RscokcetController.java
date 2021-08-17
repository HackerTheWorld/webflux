package com.web.flux.controller;

import com.web.flux.vo.MessageVo;
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

    private final Mono<RSocketRequester> rsocketRequester;
    private static Disposable disposable;

    public RscokcetController(RSocketRequester.Builder rsocketRequesterBuilder, RSocketStrategies strategies) {
        this.rsocketRequester = rsocketRequesterBuilder
                .rsocketStrategies(strategies)
                .connectTcp("127.0.0.1",7000)
                .doOnError(error -> System.out.println("发生错误，链接关闭"))
                .doFinally(consumer -> System.out.println("链接关闭"));
    }

    @PreDestroy
    void shutdown() {
        rsocketRequester.subscribe(e -> e.rsocket().dispose());
    }

    @GetMapping("requestResponse")
    public Mono<String> requestResponse() {
        return this.rsocketRequester.flatMap(mapper ->
                mapper.route("requestResponse")
                .data("客户端 服务器")
                .retrieveMono(String.class));
    }

    @GetMapping("fireAndForget")
    public String fireAndForget() {
        this.rsocketRequester.subscribe(e ->
                e.route("fireAndForget")
                        .data("客户端 服务器")
                        .send());
        return "fire and forget";
    }

    @GetMapping("stream")
    public String stream() {
        disposable = this.rsocketRequester.subscribe(e ->
                        e.route("stream")
                                .data("stream 客户端")
                                .retrieveFlux(String.class)
                                .subscribe(message -> System.out.println("客户端stream收到响应 "+message)));
        return "stream";
    }

    @GetMapping("channel")
    public String channel() {
        Mono<Duration> setting1 = Mono.create(e -> new Thread(new SettingOneRunnable(e,new MessageVo("webFlux","rsocket","A send Message"),1000L)).start());
        Mono<Duration> setting2 = Mono.create(e -> new Thread(new SettingOneRunnable(e,new MessageVo("webFlux","rsocket","B send Message"),1300L)).start());
        Mono<Duration> setting3 = Mono.create(e -> new Thread(new SettingOneRunnable(e,new MessageVo("webFlux","rsocket","C send Message"),1600L)).start());
        Flux<Duration> settings = Flux.concat(setting1, setting2, setting3);
        disposable = this.rsocketRequester.subscribe(e ->
                e.route("channel")
                        .data(settings)
                        .retrieveFlux(MessageVo.class)
                        .subscribe(message -> System.out.println("客户端channel收到响应 from::"+message.getFrom()+" to::"+message.getTo()+"=="+message.getMessage()))
        );
        return "channel";
    }

}

