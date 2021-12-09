package com.example.operators;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Timeout {


    public static void main(String[] args) {
        get1to10()
                .timeout(Duration.ofSeconds(3),get11to20())
                .subscribe(Util.getSubscriber());
        Util.sleep(60);
    }

    private static Flux<Integer> get11to20() {
        return Flux.range(11,10);
    }

    public static Flux<Integer> get1to10(){
        return Flux.range(0,10)
                .delayElements(Duration.ofSeconds(2));
    }
}
