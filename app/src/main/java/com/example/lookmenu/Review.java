package com.example.lookmenu;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Review {
    public String userid;
    public String date;
    public String food_name;
    public String review;




    public Review() {
        // Default constructor required for calls to DataSnapshot.getValue(Review.class)
    }


    public Review(String id, String review, String food_name)
    {
        this.userid = id;
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar time = Calendar.getInstance(); // 현재 시간
        date = format1.format(time.getTime());
        this.food_name = food_name;
        this.review =review;


    }

    // 디비에서 review list 넘길때 사용하려고 만든것
    public Review(String id, String review, String food_name, String dates)
    {
        this.userid=id;
        this.review=review;
        this.food_name=food_name;
       // this.date = dates;
    }



    String getId()
    {
        return userid;
    }

    String getReview()
    {
        return review;
    }

    String getFood_name()
    {
        return food_name;
    }

    String getDate() {return date;}

}