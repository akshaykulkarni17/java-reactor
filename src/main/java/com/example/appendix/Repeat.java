package com.example.appendix;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class Repeat {

    public static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        getNumbers()
                //.repeat(2)
                .repeat(() -> atomicInteger.get()<14)
                .subscribe(Util.getSubscriber());
    }

    public static Flux<Integer> getNumbers(){
        return Flux.range(1,3)
                .doOnSubscribe(subscription -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("--Completed"))
                .map(integer -> atomicInteger.getAndIncrement());
    }
}
