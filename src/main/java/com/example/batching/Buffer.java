package com.example.batching;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Buffer {

    public static void main(String[] args) {

        getCharacters()
                .delayElements(Duration.ofSeconds(1))
                //.buffer(4)
                //.buffer(Duration.ofSeconds(3))
                .bufferTimeout(2,Duration.ofSeconds(3))
                .subscribe(Util.getSubscriber());
        Util.sleep(13);
    }

    public static Flux<String> getCharacters(){
        return Flux.generate(stringSynchronousSink -> {
            stringSynchronousSink.next(Util.getFakerInstance().harryPotter().character());
        });
    }
}
