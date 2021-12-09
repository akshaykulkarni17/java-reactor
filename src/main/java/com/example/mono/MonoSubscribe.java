package com.example.mono;

import com.example.util.Util;
import reactor.core.publisher.Mono;

public class MonoSubscribe {

    public static void main(String[] args) {
        Mono<Integer> beautiful =
                Mono.just("beautiful")
                .map(String::length)
                .map(integer -> integer/0);

//        beautiful.subscribe(
//                o -> System.out.println("Received: " +o),
//                err -> System.err.println(err),
//                () -> System.out.println("Completed")
//        );
        beautiful.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }
}
