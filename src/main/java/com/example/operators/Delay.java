package com.example.operators;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Delay {

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.x","10");
        Flux.range(0,60)
                .log()
                .delayElements(Duration.ofSeconds(1/2))
                .subscribe(Util.getSubscriber());
        Util.sleep(61);
    }
}
