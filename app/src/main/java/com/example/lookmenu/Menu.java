package com.example.lookmenu;

public class Menu {
    public String name;
    public String price;
    public String info;

    public Menu() {
        // Default constructor required for calls to DataSnapshot.getValue(Menu.class)
    }

    public Menu(String price, String name,  String info) {
        this.price = price;
        this.name = name;
        this.info = info;
    }

    /* for Debugging */
    @Override
    public String toString(){
        return "name : " + name + " price : " + price + " info : " + info;
    }
}
