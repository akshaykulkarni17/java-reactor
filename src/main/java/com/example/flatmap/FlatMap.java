package com.example.flatmap;

import com.example.util.Util;

public class FlatMap {

    public static void main(String[] args) {

        UserService.getUsers()
                .flatMap(user -> OrderService.getOrders(user.getUserId()))
                .filter(order -> Double.parseDouble(order.getPrice())>50)
                .doOnDiscard(Order.class,o->System.out.println("Discarded: "+o))
                .subscribe(Util.getSubscriber());


        Util.sleep(10);
        UserService.getUsers()
                .concatMap(user -> OrderService.getOrders(user.getUserId()))
                .subscribe(Util.getSubscriber());

        Util.sleep(10);
    }
}
