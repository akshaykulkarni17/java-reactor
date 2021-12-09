package com.example.combinepublishers;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class CombineLatest {

    public static void main(String[] args) {

        Flux.combineLatest(getString(),getInteger(),(s, integer) -> s+integer)
                .subscribe(Util.getSubscriber());
        Util.sleep(10);
    }

    public static Flux<String> getString(){
        return Flux.just("A","B","C","D")
                .delayElements(Duration.ofSeconds(1));
    }

    public static Flux<Integer> getInteger(){
        return Flux.just(1,2,3)
                .delayElements(Duration.ofSeconds(3));
    }
}
