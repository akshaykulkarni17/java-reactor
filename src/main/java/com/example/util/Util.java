package com.example.util;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;

import java.util.function.Consumer;

public class Util {

    public static final Faker faker = Faker.instance();

    public static Consumer<Object> onNext(){
        return o -> System.out.println("Received: "+o);
    }
    public static Consumer<Throwable> onError(){
        return e -> System.err.println("Error: "+e.getMessage());
    }
    public static Runnable onComplete(){
        return () -> System.out.println("Completed");
    }
    public static Faker getFakerInstance(){
        return faker;
    }
    public static void sleep(int seconds){
        try {
            Thread.sleep(seconds* 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static Subscriber<Object> getSubscriber(){
        return new DefaultSubscriber();
    }

    public static Subscriber<Object> getSubscriber(String name){
        return new DefaultSubscriber(name);
    }
}
