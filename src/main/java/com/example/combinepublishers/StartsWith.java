package com.example.combinepublishers;

import com.example.util.Util;

public class StartsWith {

    public static void main(String[] args) {


        NameGeneratorStartsWith.generateNames()
                .take(2)
                .subscribe(Util.getSubscriber("mike"));

        NameGeneratorStartsWith.generateNames()
                .take(2)
                .subscribe(Util.getSubscriber("mike2"));
        NameGeneratorStartsWith.generateNames()
                .take(3)
                .subscribe(Util.getSubscriber("mike3"));
        NameGeneratorStartsWith.generateNames()
                .filter(o -> o.toString().startsWith("A"))
                .take(2)
                .subscribe(Util.getSubscriber("mike4"));


    }
}
