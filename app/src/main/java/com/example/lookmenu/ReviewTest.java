package com.example.lookmenu;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReviewTest {
    public String userid;
    public String date;
    public String food_name;
    public String review;
    SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");

    public ReviewTest(){
    }

    public ReviewTest(String id, String review, String food_name)
    {
        this.userid = id;
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar time = Calendar.getInstance(); // 현재 시간
        date = format1.format(time.getTime());
        this.food_name = food_name;
        this.review =review;
    }

    // 디비에서 review list 넘길때 사용하려고 만든것
    public ReviewTest(String userid, String review, String food_name, String date)
    {
        this.userid=userid;
        this.review=review;
        this.food_name=food_name;
        this.date = date;
    }
}


