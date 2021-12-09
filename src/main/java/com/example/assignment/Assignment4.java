package com.example.assignment;

import com.example.util.Util;

public class Assignment4 {

    public static void main(String[] args) {

        OrderService orderService = new OrderService();
        RevenueService revenueService = new RevenueService();
        InventoryService inventoryService = new InventoryService();

        orderService.orderStream()
                .subscribe(revenueService.purchaseOrderConsumer());
        orderService.orderStream()
                .subscribe(inventoryService.purchaseOrderConsumer());

        inventoryService.inventoryStream()
                .subscribe(Util.getSubscriber());
        revenueService.revenueStream()
                .subscribe(Util.getSubscriber());

        Util.sleep(60);
    }
}
