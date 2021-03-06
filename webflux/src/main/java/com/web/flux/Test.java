package com.web.flux;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;


public class Test {

    public static void main(String[] strings) throws InterruptedException {
        Flux<String> stringFlux2 = Flux.fromArray(new String[]{"bb", "cc"});
        stringFlux2.subscribe((s) -> System.out.println("s1 ::"+s));
        Flux<String> stringFlux
                = Flux.create(a -> {
                    int i =0;
                    while (true && i<10){
                        a.next(String.valueOf(i++));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    a.complete();
        });

//        Flux<String> stringFlux = Flux.fromArray(new String[]{"bb", "cc"});

        Flux<String> doOn = stringFlux
                .doOnSubscribe(a -> System.out.println("start sub "+a))
                .doOnNext(a -> System.out.println("next ::"+a))
                .doOnCancel(() -> System.out.println("cancel"))
                .doOnComplete(() -> System.out.println("complete"))
                .doFirst(() -> System.out.println("first"))
                .doOnError(error -> System.out.println(error.getMessage()))
                .doOnEach(a -> System.out.println("each ::"+a.isOnComplete()))
                .doOnRequest(a -> System.out.println("request ::"+a))
                .doOnTerminate(() -> System.out.println("terminate"))
                .doAfterTerminate(() -> System.out.println("after Terminate\n"))
                .doFinally(a -> System.out.println("finally ::"+a));

        doOn.subscribe(sub -> System.out.println("s1 ::"+sub),
                (error) -> System.out.println(error),
                () -> System.out.println("on com"));
        doOn.subscribe(sub -> System.out.println("s2 ::"+sub));



        Flux<String> stringFlux3 = stringFlux2.doOnSubscribe(subscription -> System.out.println("start sub"));
        stringFlux3.subscribe(s -> System.out.println(s));

//        ConnectableFlux<String> s1 = stringFlux3.replay(Duration.ofSeconds(1));
//        s1.subscribe(
//                //??????????????????
//                s -> System.out.println("s1::"+s)
//                //??????
//                ,error -> error.getMessage()
//                //????????????
//                ,() -> System.out.println("finish"));
//        s1.subscribe(s -> System.out.println("s2::"+s));
//        s1.subscribe(s -> System.out.println("s3::"+s));
////        s1.connect();
//        s1.subscribe(s -> System.out.println("s4::"+s));
//        s1.refCount().subscribe(s -> System.out.println("ref ::"+s));
//        s1.next().subscribe(s -> System.out.println(s));
        while (true){
            Thread.sleep(100);
        }

    }

}

