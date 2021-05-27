package com.example.lookmenu;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private ArrayList<Menu> food;
    private Double totalAmount;
    private String userid;

    public Order() {
        // Default constructor required for calls to DataSnapshot.getValue(Menu.class)
    }

    public Order(ArrayList<Menu> food, Double totalAmount, String userid){
        this.food = food;
        this.totalAmount = totalAmount;
        this.userid = userid;
    }

    public List<Menu> getFood() {
        List<Menu> menuList = new ArrayList<>();
        return food;
    }
}
