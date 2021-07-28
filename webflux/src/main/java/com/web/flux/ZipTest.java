package com.web.flux;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;


public class ZipTest {

    public static void main(String[] args) {

        Flux<Long> integerFlux = Flux.interval(Duration.ofSeconds(2));

        Flux<String> stringFlux2 = Flux.create(
                a -> {
                    int i =0 ;
                    while (true && i<10){
                        a.next(String.valueOf(i++));
                        a.complete();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        Flux<String> stringFlux3 = Flux.fromArray(new String[]{"aa", "dd","qq"});
        Flux<String> zip = stringFlux2.zipWith(stringFlux3)
                .flatMap(map -> {
                    String mapStr = map.getT1()+"::"+map.getT2();
                    return Flux.fromArray(new String[]{mapStr});
                });

        Flux.combineLatest(integerFlux,stringFlux3,(v1,v2) ->
            v1+"::"+v2).subscribe(a -> System.out.println(a));

        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

