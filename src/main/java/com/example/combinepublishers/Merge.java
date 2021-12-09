package com.example.combinepublishers;

import com.example.util.Util;
import reactor.core.publisher.Flux;

public class Merge {

    public static void main(String[] args) {


        Flux<String> merged = Flux.merge(
                Qatar.getFlights(),
                Emirates.getFlights(),
                Lufthansa.getFlights()
        );
        merged
                .take(3)
                .subscribe(Util.getSubscriber());
        Util.sleep(15);
    }
}
