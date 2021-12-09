package com.example.sinks;

import com.example.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class SinkMono {

    public static void main(String[] args) {


        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();
        mono.subscribe(Util.getSubscriber("sam"));
        sink.tryEmitValue("hi");
    }
}
