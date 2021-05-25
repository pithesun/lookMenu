package com.example.myapplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Review {
    String id;
    String review;
    String food_name;
    String date;
    SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");

    public Review(String id, String review, String food_name)
    {
        this.id = id;
        this.review =review;
        this.food_name = food_name;
        Calendar time = Calendar.getInstance(); // 현재 시간
        date = format1.format(time.getTime());
    }

    // 디비에서 review list 넘길때 사용하려고 만든것
    public Review(String id, String review, String food_name, String dates)
    {
        this.id=id;
        this.review=review;
        this.food_name=food_name;
        this.date = dates;
    }



    String getId()
    {
        return id;
    }

    String getReview()
    {
        return review;
    }

    String getFood_name()
    {
        return food_name;
    }

    String getDate()
    {
        return date;
    }
}
