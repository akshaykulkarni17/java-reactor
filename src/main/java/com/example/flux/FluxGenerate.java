package com.example.flux;

import com.example.util.Util;
import reactor.core.publisher.Flux;

public class FluxGenerate {

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
            String country = Util.getFakerInstance().country().name();
            synchronousSink.next(country);
            if (country.toUpperCase().equals("INDIA"))
                synchronousSink.complete();
        })
                .take(10)
                .subscribe(Util.getSubscriber());
    }
}
