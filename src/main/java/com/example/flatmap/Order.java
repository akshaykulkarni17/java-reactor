package com.example.flatmap;

import com.example.util.Util;
import lombok.Data;

@Data
public class Order {

    private int userId;
    private String product;
    private String price;

    public Order(int userId) {
        this.userId = userId;
        this.product = Util.getFakerInstance().commerce().productName();
        this.price = Util.getFakerInstance().commerce().price();
    }
}
