package com.example.myapplication;

import java.util.*;

public class User {

    String id;
    List<Food> orderHistory = new ArrayList<Food>();
    List<Review> reviewHistory = new ArrayList<Review>();


    List<Food> getOrderHistory()
    {
        return orderHistory;
    }
    List<Review> getReviewHistory() {return reviewHistory;}

    // DB에서 얻은 order history를 멤버로 셋해줌
    void setOrderHistory(List<Food> orderHistory)
    {
        this.orderHistory = orderHistory;
    }

    // db에서 리뷰번호로 삭제를 하려면 리뷰 번호 정보가 필요함
    // db에서 사용자의 리뷰 리스트를 반환해 리뷰 번호를 얻는다.
    void setReviewHistory(List<Review> reviewHistory)
    {
        this.reviewHistory = reviewHistory;
    }


    String getId()
    {
        return id;
    }
}
