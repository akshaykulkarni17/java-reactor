package com.example.assignment;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Assignment8 {

    public static void main(String[] args) {

        Set<String> set = new HashSet<>();
        set.add("Kids");
        set.add("Automotive");

        OrderService.getOrderFlux()
                .filter(purchaseOrder -> set.contains(purchaseOrder.getCategory()))
                .groupBy(PurchaseOrder::getCategory)
                .flatMap(gf -> gf.key().equals("Kids") ? OrderProcessor.getKids(gf) : OrderProcessor.getAutomotive(gf))
                .subscribe(Util.getSubscriber());

        Util.sleep(30);
    }


}
