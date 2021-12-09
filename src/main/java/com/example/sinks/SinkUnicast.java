package com.example.sinks;

import com.example.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkUnicast {

    public static void main(String[] args) {

       Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> flux = sink.asFlux();
        flux.subscribe(Util.getSubscriber("sam"));
        flux.subscribe(Util.getSubscriber("mike"));
        sink.tryEmitNext("hey");
        sink.tryEmitNext("hi");
        sink.tryEmitNext("hello");
    }
}
