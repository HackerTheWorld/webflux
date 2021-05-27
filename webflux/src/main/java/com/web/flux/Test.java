/* ====================================================================================================
 * Project Name     [flux]
 * File Name        [com.web.flux.Test.java]
 * Creation Date    [2021-05-27]
 *
 * Copyright© 2021 瑞声科技[AAC Technologies Holdings] All Rights Reserved
 *
 * ====================================================================================================
 * Change Log
 * ====================================================================================================
 * 2021-05-27     潘凌云      [Init] .
 * ==================================================================================================== */
package com.web.flux;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * <p></p>
 *
 * @author <a href="mailto:panlingyun@aactechnologies.com">潘凌云</a>
 * @version 1.0.0
 * @since jdk 1.8
 */
public class Test {

    public static void main(String[] strings) throws InterruptedException {
        Flux<String> stringFlux = Flux.create(a -> a.next("aa"));
        Flux<String> stringFlux2 = Flux.fromArray(new String[]{"bb", "cc"});
        Flux<String> stringFlux3 = stringFlux2.doOnSubscribe(subscription -> System.out.println("start sub"));
        stringFlux3.subscribe(s -> System.out.println(s));
        ConnectableFlux<String> s1 = stringFlux3.replay(Duration.ofSeconds(1));
        s1.subscribe(s -> System.out.println("s1::"+s));
        s1.subscribe(s -> System.out.println("s2::"+s));
        s1.subscribe(s -> System.out.println("s3::"+s));
//        s1.connect();
        s1.subscribe(s -> System.out.println("s4::"+s));
        s1.refCount().subscribe(s -> System.out.println("ref ::"+s));
        s1.next().subscribe(s -> System.out.println(s));
        while (true){
            Thread.sleep(100);
        }

    }

}

