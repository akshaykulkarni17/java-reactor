package com.example.appendix;

import com.example.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class Overflow {

    public static void main(String[] args) {
        System.setProperty("reactor.buffersize.small", String.valueOf(16));

        List<Object> list= new ArrayList<>();
        Flux.create(fluxSink -> {
            for (int i = 0; i < 5000 && !fluxSink.isCancelled(); i++) {
                fluxSink.next(i);
                System.out.println("Pushed: "+i);
            }
            fluxSink.complete();
        //overflow strategy
            }, FluxSink.OverflowStrategy.DROP)
                .onBackpressureDrop(list::add)
                //.onBackpressureLatest()
                //.onBackpressureError()
                //.onBackpressureBuffer(50 , o -> System.out.println("Dropped: "+o))
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(o -> {
                    Util.sleep(1/10);
                })
                .subscribe(Util.getSubscriber());


        Util.sleep(6);
        System.out.println(list.toString());
    }
}
