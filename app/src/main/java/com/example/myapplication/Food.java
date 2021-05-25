package com.example.myapplication;

public class Food {
    int price;
    String food_category; // 카테고리 한식 일식 중식 양식...
    String food_name; // 음식 이름
    String description; // 음식 설명
    // 카테고리로 추천

    Food(String category, String name, int _price, String _description)
    {
        food_category = category;
        food_name = name;
        price = _price;
        description = _description;
    }

    String getFood_category()
    {
        return food_category;
    }

    String getFood_name()
    {
        return food_name;
    }

    int getPrice()
    {
        return price;
    }

    String getDescription() {return description;}

    // 가격 수정은 일단 보류



}

// food category를 추가하는게 어떨까요 한식 중식 양식 일식 등등
