package com.example.lookmenu;

public class Menu {
    public String name;
    public String price;
    public String info;

    public Menu() {
        // Default constructor required for calls to DataSnapshot.getValue(Menu.class)
    }

    public Menu(String name, String price, String info) {
        this.name = name;
        this.price = price;
        this.info = info;
    }
}
