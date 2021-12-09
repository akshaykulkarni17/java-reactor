package com.example.assignment;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class OrderService {

    private Flux<PurchaseOrder> orderFlux;

    public Flux<PurchaseOrder> orderStream(){
        if (orderFlux==null){
            orderFlux=getOrders();
        }
        return orderFlux;
    }
    //Hot publisher
    public Flux<PurchaseOrder> getOrders(){
        return Flux.interval(Duration.ofMillis(100))
                .map(i -> new PurchaseOrder())
                .publish()
                .refCount(2);
    }
    //Cold publisher
    public static Flux<PurchaseOrder> getOrderFlux(){
        return Flux.interval(Duration.ofMillis(200))
                .map(p -> new PurchaseOrder());
    }
}
