package com.example.flux;

import com.example.util.Util;
import reactor.core.publisher.Flux;

public class FluxSinkMultipleThread {

    public static void main(String[] args) {

        NameProducer nameProducer = new NameProducer();
        Flux.create(nameProducer)
                .subscribe(Util.getSubscriber());

        Runnable runnable = nameProducer::produce;

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
        Util.sleep(1);
    }
}
