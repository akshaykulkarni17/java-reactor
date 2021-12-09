package com.example.batching;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class GroupBy {

    public static void main(String[] args) {
        Flux.range(0,30)
                .delayElements(Duration.ofSeconds(1))
                .groupBy(integer -> integer%2)
                .subscribe(gf -> process(gf, gf.key()));
        Util.sleep(35);
    }

    public static void process(Flux<Integer> flux, int key){
        System.out.println("Called");
        flux.subscribe(integer -> System.out.println("Key: "+key+" Value: "+integer));
    }
}
