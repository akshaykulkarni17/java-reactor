package com.example.flux;

import com.example.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxMono {

    public static void main(String[] args) {


        Mono<String> mono = Mono.just("a");
        Flux.from(mono).subscribe(Util.onNext());

        Flux.range(0,5)
                .filter(i -> i%2==0&&i!=0)
                .next()
                .subscribe(Util.onNext());
    }
}
