package com.example.mono;

import com.example.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class SupplierRefactoring {

    public static void main(String[] args) throws InterruptedException {
        getName();
        getName()
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(Util.onNext());
        getName();
        Util.sleep(4);
    }

    public static Mono<String> getName(){
        System.out.println("Entered Name method");
        return Mono.fromSupplier(()->{
           System.out.println("Getting name..");
            Util.sleep(3);
            return Util.getFakerInstance().harryPotter().character();
        }).map(String::toUpperCase);
    }
}
