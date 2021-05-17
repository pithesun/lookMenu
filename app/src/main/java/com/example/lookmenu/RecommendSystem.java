package com.example.myapplication;
import java.util.*;



public class RecommendSystem {

    List<Food> userHistory = new ArrayList<Food>();

    RecommendSystem(List<Food> history)
    {
        userHistory = history;
    } // 기본 선언시 history 받게

    List<Food> makeRecommendations(List<Food> userhistory)
    {
        // 한식 일식 중식 양식 카테고리별 카운팅
        int koreanFood = 0;
        int japaneseFood = 0;
        int chineseFood = 0;
        int westernFood = 0;

        List<Food> recommend = new ArrayList<Food>();
        HashMap<String, Integer> orderDB = new HashMap<String, Integer>();

        for(int i=0;i<userhistory.size();i++)
        {
            String cate = userhistory.get(i).getFood_category();

            switch(cate)
            {
                case "한식":
                    koreanFood++;
                    break;
                case "일식":
                    japaneseFood++;
                    break;
                case "중식":
                    chineseFood++;
                    break;
                case "양식":
                    westernFood++;
                    break;
            }
        }
        orderDB.put("한식", koreanFood);
        orderDB.put("중식", chineseFood);
        orderDB.put("일식", japaneseFood);
        orderDB.put("양식", westernFood);

        int maxCount = Math.max(koreanFood, chineseFood);
        maxCount = Math.max(maxCount, japaneseFood);
        maxCount = Math.max(maxCount, westernFood); // 최대 count

        for(String key : orderDB.keySet())
        {
            if(orderDB.get(key)==maxCount) // 최대 값 푸드카테고리 추천메뉴에 add
            {
                for(int i=0;i<userhistory.size();i++)
                {
                    String cate = userhistory.get(i).getFood_category();
                    if(cate==key)
                        recommend.add(userhistory.get(i));
                }
            }
        }

        return recommend;

    }

}
