package com.example.mono;

import com.example.util.Util;
import reactor.core.publisher.Mono;

public class MonoEmptyOrError {

    public static void main(String[] args) {
        users(5).subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }

    public static Mono<String> users (int id){
        if (id==1) {
            return Mono.just(Util.getFakerInstance().animal().name());
        }
        else if (id==2){
            return Mono.empty();
        }
        else return Mono.error(new ArrayIndexOutOfBoundsException("Out of range"));
    }
}
