package com.example.combinepublishers;

import com.example.util.Util;
import reactor.core.publisher.Flux;

public class Concat {

    public static void main(String[] args) {


        Flux<String> flux1 = Flux.just("a","b");
        Flux<String> flux2 = Flux.error(RuntimeException::new);
        Flux<String> flux3 = Flux.just("c","d","e");

        Flux<String> flux = flux1.concatWith(flux3);
        Flux<String> fluxError = Flux.concat(flux1,flux2,flux3);
        Flux<String> fluxErrorDelay = Flux.concatDelayError(flux1,flux2,flux3);

        flux.subscribe(Util.getSubscriber());
        fluxError.subscribe(Util.getSubscriber());
        fluxErrorDelay.subscribe(Util.getSubscriber());
    }
}
