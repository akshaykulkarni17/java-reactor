package com.example.batching;

import com.example.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Window {

    static AtomicInteger atomicInteger = new AtomicInteger(1);
    public static void main(String[] args) {

        getEvents()
                //.window(5)
                .window(Duration.ofSeconds(3))
                .flatMap(Window::process)
                .subscribe(Util.getSubscriber());

        Util.sleep(20);
    }

    private static Flux<String> getEvents(){
        return Flux.interval(Duration.ofSeconds(1))
                .map(i->"event "+i);
    }

    private static Mono<Integer> process(Flux<String> flux){
        return flux
                .doOnNext(s -> System.out.println("saving "+s))
                .doOnComplete(() -> {
                    System.out.println("Batch saved");
                    System.out.println("--------------------------");
                })
                .then(Mono.just(atomicInteger.getAndIncrement()));
    }
}
