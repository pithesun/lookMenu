package com.example.myapplication;

import java.util.*;

public class User {

    String id;
    List<Food> orderHistory = new ArrayList<Food>();

    List<Food> getOrderHistory()
    {
        return orderHistory;
    }

    String getId()
    {
        return id;
    }
}
