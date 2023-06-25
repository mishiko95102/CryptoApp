package com.example.cryptoapp.model.remote.chat;

public class Message {
    public String userEmail;
    public String message;
    public String dataTime;


// აქ მესიჯის კლასსია შექმნილი
    public Message(String userEmail, String message, String dataTime) {
        this.userEmail = userEmail;
        this.message = message;
        this.dataTime = dataTime;
    }


    public Message() { }
}