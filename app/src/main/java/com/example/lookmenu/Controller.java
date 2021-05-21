package com.example.myapplication;

import android.view.Menu;

import java.util.*;

public class Controller {
    List<Food> menus = new ArrayList<Food>(); // 메뉴 리스트
    List<Food> UserOrderHistory = new ArrayList<Food>(); // user order history


    void setData(List<Food> MenuList, List<Food> UserHistory)
    {
        menus = MenuList;
        UserOrderHistory = UserHistory;
    }

    List<Food> getRecommendations(List<Food> UserHistory)
    {
        return new RecommendSystem(UserOrderHistory).makeRecommendations(UserOrderHistory); // 추천 시스템으로 부터 추천 메뉴 가져오기
    }

    List<Food> getMenuforUser(List<Food> menus, List<Food> recommendationMenuList)
    {

        // 우선 추천 메뉴를 넣은뒤
        // 추천 메뉴와 같은 카테고리인 음식을 또한 널어주기
        // 단 중복되지 않아야함
        // 메뉴는 아무리 많아도 10000이상 넘지 않을것이기 때문에 linear search
        // 여기서 고민인게 이 함수의 기능이 모든 음식의 메뉴를 return 하는것인지 아니면 추천 음식과 같은 카테고리의 음식만 return할지 결정하는것임
        // 만약 전자라면 그냥 menus를 return하면됨
        // 그게 아니라면 같은 카테고리를 찾아서 return
        if(recommendationMenuList.isEmpty())
            return menus;
        List<Food> total = new ArrayList<Food>();
        HashSet<String> category = new HashSet<String>();

        for(Food food : recommendationMenuList)
        {
            category.add(food.getFood_category());
        }

        for(String name : category)
        {
            for(Food food : menus)
            {
                if(name == food.getFood_category())
                    total.add(food);
            }
        }

        return total;
    }







}










