package com.example.bearhotel;

import java.io.Serializable;

public class Order  {

        public String uid;
        public String item,room;
        public double price;
        public  int quantity;
        public String date;
        public double totalPrice;

        public Order(){}
        public Order(String uid,String item, double price, int quantity, String date,String room,double totalPrice) {
            this.uid = uid;
            this.item = item;
            this.price = price;
            this.quantity = quantity;
            this.date = date;
            this.room = room;
            this.totalPrice=totalPrice;

    }

}
