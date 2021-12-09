package com.example.mono;

import reactor.core.publisher.Mono;

public class MonoJust {

    public static void main(String[] args) {
        Mono<Integer> mono = Mono.just(1);
        mono.subscribe(System.out::println);
    }
}
