package com.example.assignment.slack;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SlackRoom {

    private String name;
    private Sinks.Many<SlackMessage> sink;
    private Flux<SlackMessage> flux;

    public SlackRoom(String name) {
        this.name = name;
        this.sink = Sinks.many().replay().all();
        this.flux = sink.asFlux();
    }

    public void join(SlackMember slackMember){
        System.out.println(slackMember.getName() +" joins room "+this.name);
        this.receiveMessage(slackMember);
        slackMember.setConsumer(
                msg -> this.postMessage(slackMember, msg)
        );
    }
    private void postMessage(SlackMember slackMember,String msg){
        SlackMessage slackMessage = new SlackMessage();
        slackMessage.setMsg(msg);
        slackMessage.setSender(slackMember.getName());
        this.sink.tryEmitNext(slackMessage);
    }
    private void receiveMessage(SlackMember slackMember){
        this.flux
                .filter(slackMessage -> !slackMessage.getSender().equals(slackMember.getName()))
                .doOnNext(slackMessage -> slackMessage.setReceiver(slackMember.getName()))
                .map(SlackMessage::toString)
                .subscribe(SlackMember::receive);
    }

}
