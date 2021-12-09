package com.example.assignment.slack;

import lombok.Data;

@Data
public class SlackMessage {

    private static String FORMAT = "[%s -> %s] : %s";
    private String sender;
    private String receiver;
    private String msg;

    @Override
    public String toString() {
        return String.format(FORMAT,this.sender,this.receiver,this.msg);
    }
}
