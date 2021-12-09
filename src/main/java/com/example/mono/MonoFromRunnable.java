package com.example.mono;

import com.example.util.Util;
import reactor.core.publisher.Mono;

public class MonoFromRunnable {

    public static void main(String[] args) {
        Mono.fromRunnable(lengthyProcess()).subscribe(
                Util.onNext(),
                Util.onError(),
                () -> System.out.println("Doing further task")
        );
    }
    public static Runnable lengthyProcess(){
        return () -> {
            Util.sleep(3);
            System.out.println("Prerequisites done");
        };
    }
}
