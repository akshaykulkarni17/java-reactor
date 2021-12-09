package com.example.combinepublishers;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGeneratorStartsWith {

    private static final List<String> list = new ArrayList<>();
    public static Flux<Object> generateNames(){

        return Flux.generate(stringSynchronousSink -> {
            System.out.println("Generating fresh");
            Util.sleep(1);
            String name = Util.getFakerInstance().name().firstName();
            stringSynchronousSink.next(name);
            list.add(name);
        })
                .startWith(getFromCache());
    }

    private static Flux<String> getFromCache() {
        return Flux.fromIterable(list);
    }
}
