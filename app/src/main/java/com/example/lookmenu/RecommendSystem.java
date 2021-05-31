package com.example.lookmenu;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendSystem {

    String TAG = "RecommendSystem";
    private static final RecommendSystem instance = new RecommendSystem();

    private RecommendSystem(){}

    public static RecommendSystem getInstance(){
        return instance;
    }

    ArrayList<Menu> makeRecommendations(List<Order> userOrderHis)
    {
        ArrayList<Menu> recommend = new ArrayList<>();
        HashMap<String, List<Menu>> orderDB = new HashMap<>();

        for(int i=0; i< userOrderHis.size(); i++)
        {
            List<Menu> menus = userOrderHis.get(i).getFood();
            for( Menu menu : menus){
                Log.d(TAG, menu.name);
                List<Menu> categoryMenu = orderDB.get(menu.category);
                if(categoryMenu == null){
                    categoryMenu = new ArrayList<>();
                }
                categoryMenu.add(menu);
                System.out.println(categoryMenu);
                orderDB.put(menu.category, categoryMenu);
            }
        }

        Map.Entry<String, List<Menu>> maxEntry = null;
        for (Map.Entry<String, List<Menu>> entry : orderDB.entrySet()) {
            if (maxEntry == null || entry.getValue().size() >= maxEntry.getValue().size()) {
                maxEntry = entry;
            }
        }

        if(maxEntry != null && maxEntry.getKey() != null){
            String maxKey = maxEntry.getKey();
            recommend.addAll(orderDB.get(maxKey));
        }

        return recommend;
    }
}
