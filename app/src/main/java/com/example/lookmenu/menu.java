package com.example.lookmenu;

public class Menu {
    public String name;
    public String price;
    public String info;
    public String category;

    public Menu() {
        // Default constructor required for calls to DataSnapshot.getValue(Menu.class)
    }

    public Menu(String price, String name, String info, String category) {
        this.price = price;
        this.name = name;
        this.info = info;
        this.category = category;
    }

    /* for Debugging */
    @Override
    public String toString(){
        return "name : " + name + " price : " + price + " info : " + info;
    }

    public String getPrice()
    {
        return price;
    }
}
