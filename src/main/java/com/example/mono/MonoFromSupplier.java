package com.example.mono;

import com.example.util.Util;
import reactor.core.publisher.Mono;

public class MonoFromSupplier {

    public static void main(String[] args) {
        Mono<String> mono = Mono.fromSupplier(MonoFromSupplier::getName);
        mono.subscribe(
                Util.onNext()
        );
        Mono.fromCallable(MonoFromSupplier::getName).subscribe(
                Util.onNext()
        );
    }

    public static String getName(){
        return Util.getFakerInstance().funnyName().name();
    }
}
