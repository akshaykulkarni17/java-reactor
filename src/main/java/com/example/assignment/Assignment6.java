package com.example.assignment;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Assignment6 {

    public static void main(String[] args) {
        int price = 10000;
        Flux.combineLatest(getMonth(),getDemand(),(month, demand) -> {
          return (price - month*100)*demand;
        })
                .subscribe(Util.getSubscriber());
        Util.sleep(20);
    }

    public static Flux<Long> getMonth(){
        return Flux.interval(Duration.ZERO,Duration.ofSeconds(1));
    }

    public static Flux<Double> getDemand(){
        return Flux.interval(Duration.ofSeconds(3))
                .map(i-> Util.getFakerInstance().random().nextInt(80,130)/100d)
                .startWith(1d);
    }
}
