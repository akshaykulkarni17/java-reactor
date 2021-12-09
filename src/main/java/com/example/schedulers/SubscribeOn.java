package com.example.schedulers;

import com.example.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubscribeOn {

    public static void main(String[] args) {


        Flux<Object> flux = Flux.create(stringFluxSink -> {
            printThreadName("create");
            stringFluxSink.next(1);
        })
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(o -> printThreadName("next: "+o));

        Runnable runnable = () -> flux
                .doFirst(() -> printThreadName("first2"))
                .publishOn(Schedulers.boundedElastic())
                .doFirst(() -> printThreadName("first1"))
                .subscribe(o -> printThreadName("subscribe: "+o));

        for (int i = 0; i < 3; i++) {
            new Thread(runnable).start();
        }

        Util.sleep(10);
    }

    public static void printThreadName(String msg) {
        System.out.println(msg+" "+Thread.currentThread().getName());
    }
}
