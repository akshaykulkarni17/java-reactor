package com.example.operators;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Handle {

    public static void main(String[] args) {


        Flux.range(0,10)
                        .handle((integer, synchronousSink) -> {
                            if(integer%2==0)
                                synchronousSink.next(integer);
                            else
                                synchronousSink.next(integer+" a");
                        })
                                .subscribe(Util.getSubscriber());

        Flux.generate(synchronousSink -> synchronousSink.next(Util.getFakerInstance().country().name()))
                .map(Object::toString)
                .handle((s, synchronousSink) -> {
                    synchronousSink.next(s);
                    if(s.equalsIgnoreCase("INDIA")) {
                        synchronousSink.complete();
                    }
                })
                .subscribe(Util.getSubscriber());


    }
}
