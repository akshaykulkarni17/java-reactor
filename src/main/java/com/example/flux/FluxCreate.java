package com.example.flux;

import com.example.util.Util;
import reactor.core.publisher.Flux;

public class FluxCreate {

    public static void main(String[] args) {


        Flux.create(fluxSink -> {
            String country;
           do {
               country = Util.getFakerInstance().country().capital();
               fluxSink.next(country);
           }while (!country.toUpperCase().equals("NEW DELHI"));
           fluxSink.complete();
        })
                .subscribe(Util.getSubscriber());

    }
}
