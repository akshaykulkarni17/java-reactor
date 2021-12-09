package com.example.assignment.slack;

import lombok.Getter;

import java.util.function.Consumer;

public class SlackMember {
    @Getter
    private String name;
    private Consumer<String> consumer;

    public SlackMember(String name) {
        this.name = name;
    }
    public static void receive(String msg){
        System.out.println(msg);
    }
    public void send(String msg){
        this.consumer.accept(msg);
    }
    void setConsumer(Consumer<String> consumer){
        this.consumer=consumer;
    }

}
