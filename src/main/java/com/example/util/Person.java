package com.example.util;

import lombok.Data;

@Data
public class Person {

    String name;
    int age;

    public Person(){
        this.name = Util.getFakerInstance().funnyName().name();
        this.age = Util.getFakerInstance().random().nextInt(1,30);
    }
}
