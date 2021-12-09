package com.example.operators;

import com.example.util.Util;
import reactor.core.publisher.Flux;

public class LimitRate {

    public static void main(String[] args) {
        Flux.range(1,1000)
                .log()
                .limitRate(100) //75
                .subscribe(Util.getSubscriber());
    }
}
