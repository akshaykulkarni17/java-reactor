package com.example.operators;

import com.example.util.Util;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

public class DefaultOrSwitchIfEmpty {


    public static void main(String[] args) {

        get1to10()
                .filter(integer -> integer>11)
                .defaultIfEmpty(-100)
                .subscribe(Util.getSubscriber());

        get1to10()
                .filter(integer -> integer>11)
                .switchIfEmpty(fallback())
                .subscribe(Util.getSubscriber());
    }

    private static Flux<Integer> fallback() {
        return Flux.range(11,10);
    }

    public static Flux<Integer> get1to10(){
       return Flux.range(0,10);
    }


}
