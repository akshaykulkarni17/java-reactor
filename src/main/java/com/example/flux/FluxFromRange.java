package com.example.flux;

import com.example.util.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class FluxFromRange {

    public static void main(String[] args) {


        Flux.range(3,10)
                .log()
                .map(integer -> Util.getFakerInstance().address().city())
                .log()
                .subscribe(Util.onNext(),Util.onError(),Util.onComplete());

        AtomicReference<Subscription> atomicReference = new AtomicReference<>();
        Flux.range(1,20)
                .log()
                .subscribeWith(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        atomicReference.set(subscription);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("Received: "+integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Completed");
                    }
                });
        Util.sleep(3);
        atomicReference.get().request(3);
        Util.sleep(3);
        atomicReference.get().request(5);
        Util.sleep(3);
        atomicReference.get().request(4);
        Util.sleep(3);
        atomicReference.get().cancel();
    }
}
