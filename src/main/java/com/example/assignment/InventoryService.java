package com.example.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class InventoryService {

    private static Map<String,Integer> db = new HashMap<>();

    public InventoryService(){
        db.put("Kids",100);
        db.put("Automotive",100);
    }

    public Consumer<PurchaseOrder> purchaseOrderConsumer(){
        return purchaseOrder -> db.computeIfPresent(purchaseOrder.getCategory(),(k,v) -> v + purchaseOrder.getQuantity());
    }

    public Flux<String> inventoryStream(){
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> db.toString());
    }

}
