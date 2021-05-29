package com.example.lookmenu;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReviewTest {
    String author;
    String review;
    String date;
    String food_name;
    SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");

    public ReviewTest(String id, String review, String food_name)
    {
        this.author = id;
        this.review =review;
        this.food_name = food_name;
        Calendar time = Calendar.getInstance(); // 현재 시간
        date = format1.format(time.getTime());
    }

    // 디비에서 review list 넘길때 사용하려고 만든것
    public ReviewTest(String id, String review, String food_name, String dates)
    {
        this.author=id;
        this.review=review;
        this.food_name=food_name;
        this.date = dates;
    }
}
