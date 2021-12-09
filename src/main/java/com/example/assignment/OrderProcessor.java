package com.example.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OrderProcessor {

    public static Flux<PurchaseOrder> getAutomotive(Flux<PurchaseOrder> flux){
        return flux
                .doOnNext(purchaseOrder -> purchaseOrder.setPrice(1.1* purchaseOrder.getPrice()))
                .doOnNext(purchaseOrder -> purchaseOrder.setItem("{{ "+purchaseOrder.getItem()+" }}"));
    }

    public static Flux<PurchaseOrder> getKids(Flux<PurchaseOrder> flux){
        return flux
                .doOnNext(purchaseOrder -> purchaseOrder.setPrice(0.5* purchaseOrder.getPrice()))
                .flatMap(purchaseOrder -> Flux.concat(Mono.just(getFreeKidsOrder())));

    }

    private static Mono<PurchaseOrder> getFreeKidsOrder() {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setItem("FREE "+ purchaseOrder.getItem());
        purchaseOrder.setPrice(0);
        purchaseOrder.setCategory("Kids");
        return Mono.just(purchaseOrder);
    }


}
