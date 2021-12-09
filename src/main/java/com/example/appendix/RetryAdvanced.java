package com.example.appendix;

import com.example.util.Util;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

public class RetryAdvanced {

    public static void main(String[] args) {
        orderService(Util.getFakerInstance().business().creditCardNumber())
                .retryWhen(Retry.from(retrySignalFlux ->
                    retrySignalFlux
                            .doOnNext(retrySignal -> {
                                System.out.println("Retry: "+retrySignal.totalRetries());
                                System.out.println("Failure: "+retrySignal.failure());
                            })
                            .handle((retrySignal, synchronousSink) -> {
                                if (retrySignal.failure().getMessage().equals("500"))
                                    synchronousSink.next(1);
                                else synchronousSink.error(retrySignal.failure());
                            })
                            .delayElements(Duration.ofSeconds(1))
                ))
                .subscribe(Util.getSubscriber());
        Util.sleep(30);
    }

    public static Mono<String> orderService(String ccNumber){
        return Mono.fromSupplier(() -> {
            processPayment(ccNumber);
            return Util.getFakerInstance().idNumber().valid();
        });
    }

    private static void processPayment(String ccNumber) {
        int random = Util.getFakerInstance().random().nextInt(1,10);
        if(random>5&&random<9){
            throw new RuntimeException("500");
        }
        else if(random>8){
            throw new RuntimeException("404");
        }
    }

}
