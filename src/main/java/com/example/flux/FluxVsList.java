package com.example.flux;

import com.example.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FluxVsList {

    public static String getName(){
        Util.sleep(1);
        return Util.getFakerInstance().funnyName().name();
    }
    public static List<String> getNamesList(int count){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(getName());
        }
        return list;
    }
    public static Flux<String> getNamesFlux(int count){
        return Flux.range(0,count)
                .map(integer -> getName());
    }

    public static void main(String[] args) {
//        System.out.println(getNamesList(5));
//        getNamesFlux(5).subscribe(Util.onNext(),Util.onError(),Util.onComplete());
        Flux.interval( Duration.ofSeconds(1))
                .map(aLong -> Util.getFakerInstance().address().country())
                .subscribe(Util.onNext());
        Util.sleep(10);
    }

}
