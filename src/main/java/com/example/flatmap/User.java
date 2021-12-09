package com.example.flatmap;

import com.example.util.Util;
import lombok.Data;

@Data
public class User {
    private int userId;
    private String name;

    public User(int userId) {
        this.userId = userId;
        this.name = Util.getFakerInstance().name().username();
    }
}
