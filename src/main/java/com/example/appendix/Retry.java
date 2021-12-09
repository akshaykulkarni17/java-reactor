package com.example.appendix;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Retry {


    public static void main(String[] args) {
        getNumbers()
                //.retry(2)
                .retryWhen(reactor.util.retry.Retry.fixedDelay(5, Duration.ofMillis(3)))
                .subscribe(Util.getSubscriber());
    }

    public static Flux<Integer> getNumbers(){
        return Flux.range(1,3)
                .doOnSubscribe(subscription -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("--Completed"))
                .map(integer -> integer/(Util.getFakerInstance().random().nextInt(1,5)>3?0:1))
                .doOnError(throwable -> System.err.println(throwable.getMessage()));
    }
}
