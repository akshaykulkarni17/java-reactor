package com.example.assignment;

import com.example.util.Util;
import lombok.Data;

@Data
public class PurchaseOrder {

    private String item;
    private double price;
    private String category;
    private int quantity;

    public PurchaseOrder() {
        this.item = Util.getFakerInstance().commerce().productName();
        this.price = Double.parseDouble(Util.getFakerInstance().commerce().price());
        this.category = Util.getFakerInstance().commerce().department();
        this.quantity = Util.getFakerInstance().random().nextInt(1,10);
    }
}
