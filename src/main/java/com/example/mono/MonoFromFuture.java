package com.example.mono;

import com.example.util.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class MonoFromFuture {

    public static void main(String[] args) {
        Mono.fromFuture(MonoFromFuture::getName)
                .subscribe(Util.onNext());
        Util.sleep(3);
    }

    public static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(()-> Util.getFakerInstance().book().title());
    }
}
