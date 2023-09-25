package com.example.bearhotel;

public class Request {
    public String userID;
    public String room;
    public String request;
    public String phone;
    public String time;
    public String date;


    public Request(){}

    public Request(String userID, String room, String request, String phone, String time,String date) {
        this.userID = userID;
        this.room = room;
        this.request = request;
        this.phone = phone;
        this.date = date;
        this.time = time;
    }
}
