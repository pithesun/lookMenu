package com.example.myapplication;

import java.util.*;

public class menu {
    List<Food> menus = new ArrayList<Food>(); // food 저장
    List<Food> recommendedMenus = new ArrayList<Food>(); // 추천 메뉴 저장

    menu(List<Food> _menus, List<Food> _recommendedMenus)
    {
        menus = _menus;
        recommendedMenus = _recommendedMenus;
    }


    void PrintMenu()
    {
      for(int i=0, len = menus.size();i<len;i++)
      {
          System.out.println("이름: "+menus.get(i).getFood_name()+"\t가격: "+menus.get(i).getPrice());
      }
    }

    void PrintRecommendedMenu()
    {
        if(recommendedMenus.isEmpty())
            PrintMenu();
        else
        {
            for(int i=0, len = recommendedMenus.size();i<len;i++)
            {
                System.out.println("이름: "+recommendedMenus.get(i).getFood_name()+"\t가격: "+recommendedMenus.get(i).getPrice());
            }
        }
    }

    void setRecommendedMenus(List<Food> _recommendedMenus)
    {
        recommendedMenus = _recommendedMenus;
    }

    void setMenus(List<Food> _menus)
    {
        menus = _menus;
    }


}
