package com.example.combinepublishers;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Qatar {

    public static Flux<String> getFlights(){
        Integer num = Util.getFakerInstance().random().nextInt(100,999);
        return Flux.range(1, Util.getFakerInstance().random().nextInt(1,5))
                .map(i -> "Qatar "+ num)
                .filter(s -> num>500)
                .delayElements(Duration.ofSeconds(1));
    }
}
