package com.example.combinepublishers;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Zip {

    public static void main(String[] args) {

         Flux.zip(getBody(),getEngine(),getTires())
                 .subscribe(Util.getSubscriber());
        Util.sleep(10);
    }

    private static Flux<String> getBody(){
        return Flux.range(1,5)
                .map(i->"body")
                .delayElements(Duration.ofSeconds(1));
    }
    private static Flux<String> getEngine(){
        return Flux.range(1,3)
                .map(i->"engine")
                .delayElements(Duration.ofSeconds(1));
    }
    private static Flux<String> getTires(){
        return Flux.range(1,4)
                .map(i->"tires")
                .delayElements(Duration.ofSeconds(1));
    }
}
