package com.example.operators;

import com.example.util.Util;
import reactor.core.publisher.Flux;

public class DoCallbacks {

    public static void main(String[] args) {

        Flux.create(fluxSink -> {
            for (int i = 0; i <5 ; i++) {
                fluxSink.next(Util.getFakerInstance().country().currency());
            }
            fluxSink.complete();
        })
                .doOnComplete(()->System.out.println("doOnComplete"))
                .doFirst(()->System.out.println("doOnFirst"))
                .doOnError(throwable -> System.out.println(throwable.getMessage()))
                .doOnNext(o->System.out.println("doOnNext: "+o))
                .doOnSubscribe(s->System.out.println("doOnSubscribe "+s))
                .doOnRequest(value -> System.out.println("doOnRequest "+value))
                .doOnCancel(()->System.out.println("doOnCancel"))
                .doOnTerminate(()->System.out.println("doOnTerminate"))
                .doFinally(signalType -> System.out.println("doFinally"+signalType))
                .subscribe(Util.getSubscriber());
    }
}
