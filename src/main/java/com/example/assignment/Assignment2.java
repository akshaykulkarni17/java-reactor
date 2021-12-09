package com.example.assignment;

import com.example.util.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

//Stock price observer
public class Assignment2 {


        static Flux<Integer> stockPrices(){
            AtomicInteger atomicInteger = new AtomicInteger(100);
            return Flux.interval(Duration.ofSeconds(1))
                    .map(i -> atomicInteger.getAndAccumulate(
                            Util.getFakerInstance().random().nextInt(-5,5),
                            Integer::sum
                    ));
        }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        stockPrices().subscribeWith(new Subscriber<Integer>() {
            private Subscription subscription;
            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription=subscription;
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(LocalDateTime.now() +" Price: "+integer);
                if(integer>110|| integer<90) {
                    this.subscription.cancel();
                    countDownLatch.countDown();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                countDownLatch.countDown();
            }

            @Override
            public void onComplete() {
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
    }

    }
