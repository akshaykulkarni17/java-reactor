package com.example.context;

import com.example.util.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Ctx {


    public static void main(String[] args) {
        getUser()
                .contextWrite(Context.of("user","Sam"))
                .subscribe(Util.getSubscriber());
    }

    public static Mono<String> getUser(){
        return Mono.deferContextual(contextView -> {
            if (contextView.hasKey("user")){
                return Mono.just("Welcome: "+contextView.get("user"));
            }
            else return Mono.error(new RuntimeException("User not found"));
        });
    }
}
