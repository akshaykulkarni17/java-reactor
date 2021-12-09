package com.example.combinepublishers;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lufthansa {

    public static Flux<String> getFlights(){
        return Flux.range(1, Util.getFakerInstance().random().nextInt(1,5))
                .map(i -> "Lufthansa "+Util.getFakerInstance().random().nextInt(100,999))
                .delayElements(Duration.ofSeconds(1));
    }
}
