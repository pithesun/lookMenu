package com.example.lookmenu;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendSystem {

    String TAG = "RecommendSystem";
    List<Order> userOrderHis;

    RecommendSystem(List<Order> history)
    {
        this.userOrderHis = history;
    }

    ArrayList<Menu> makeRecommendations()
    {
        ArrayList<Menu> recommend = new ArrayList<>();
        HashMap<String, List<Menu>> orderDB = new HashMap<>();

        for(int i=0; i< this.userOrderHis.size(); i++)
        {
            List<Menu> menus = this.userOrderHis.get(i).getFood();
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
