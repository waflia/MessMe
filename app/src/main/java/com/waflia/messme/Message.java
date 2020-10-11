package com.waflia.messme;

import java.util.Date;

public class Message {
    public String text;
    public String from_name;
    public String to_name;
    private String chat_id;
    private long time;

    public String getChat_id() {
        if(from_name.hashCode() < to_name.hashCode()) {
            return from_name + to_name;
        }else{
            return to_name + from_name;
        }
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public Message(){}

    public Message(String text, String from_name, String to_name) {
        this.text = text;
        this.from_name = from_name;
        this.to_name = to_name;
        time = new Date().getTime();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public String getTo_name() {
        return to_name;
    }

    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
