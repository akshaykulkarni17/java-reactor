package com.example.schedulers;

import com.example.util.Util;
import org.apache.commons.lang3.time.StopWatch;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.Timer;

public class Parallel {

    public static void main(String[] args) {

        StopWatch stopWatch= new StopWatch();
        stopWatch.start();
        Flux.range(1,100)
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .doOnNext(integer -> SubscribeOn.printThreadName(integer.toString()))
                .doOnComplete(() -> {

                    System.out.println("&&&&&&&&&&&&&&*******"+stopWatch.getTime());
                })
                .subscribe(Util.getSubscriber());
        stopWatch.stop();
        Util.sleep(20);
    }
}
