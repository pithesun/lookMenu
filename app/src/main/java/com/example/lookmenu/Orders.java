package com.example.lookmenu;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    private ArrayList<Menu> food;
    private Double totalAmount;
    private String userid;

    public Orders() {
        // Default constructor required for calls to DataSnapshot.getValue(Menu.class)
    }

    public Orders(ArrayList<Menu> food, String userid){
        this.food = food;
        totalAmount = getTotalPrice();
        this.userid = userid;
    }

    public List<Menu> getFood() {
        List<Menu> menuList = new ArrayList<>(); // 해당 라인 필요성?
        return food;
    }

    public double getTotalPrice()
    {
        double tot = 0;
        for(Menu mem : food)
            tot+=Double.parseDouble(mem.getPrice());
        return tot;

    }
}
