package com.example.flatmap;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.Duration;
import java.util.*;

public class OrderService {

    private static final Map<Integer, List<Order>> db = new HashMap<>();

    static {
        List<Order> list1 = new ArrayList<>(Arrays.asList(
                new Order(1),
                new Order(1),
                new Order(1)
        ));
        List<Order> list2 = new ArrayList<>(Arrays.asList(
                new Order(2),
                new Order(2)
        ));
        db.put(1,list1);
        db.put(2,list2);
    }
    public static Flux<Order> getOrders(int userId){
        return Flux.create((FluxSink<Order> orderFluxSink) -> {
            db.get(userId).forEach(orderFluxSink::next);
            orderFluxSink.complete();
        }).delayElements(Duration.ofSeconds(1));
    }
//    public Flux<Order> getOrders(int userId){
//       return Flux.create(orderFluxSink -> {
//           for (Order o : db.get(userId)) {
//               orderFluxSink.next(o);
//           }
//       });
//    }
}
