package com.example.operators;

import com.example.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

public class OnError {

    public static void main(String[] args) {


        Flux.range(0,10)
                .log()
                .map(i->10/(5-i))
                //.onErrorReturn(-1)
                //.onErrorResume(throwable -> fallback())
                .onErrorContinue((throwable, o) -> {
                    System.out.println(throwable.getMessage());
                    System.out.println("Error val: "+o.toString());
                })
                .subscribe(Util.getSubscriber());

    }

    private static Mono<Integer> fallback() {
        return Mono.fromSupplier(() -> (int)Util.getFakerInstance().random().nextLong());
    }
}
