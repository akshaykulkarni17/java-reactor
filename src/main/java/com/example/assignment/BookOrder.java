package com.example.assignment;

import com.example.util.Util;
import lombok.Data;

@Data
public class BookOrder {

    private String title;
    private String category;
    private String author;
    private Double cost;

    public BookOrder() {
        this.title = Util.getFakerInstance().book().title();
        this.category = Util.getFakerInstance().book().genre();
        this.author = Util.getFakerInstance().book().author();
        this.cost = Double.valueOf(Util.getFakerInstance().commerce().price());
    }
}
