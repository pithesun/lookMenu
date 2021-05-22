package com.example.myapplication;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;



/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
   private DataBase db;
   private List<Food> menuList;
   @Before
   public void createDb(){
       Context context = ApplicationProvider.getApplicationContext();
       db = new DataBase(context);
       db.getDatabase();
   }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        //Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //assertEquals("com.example.myapplication", appContext.getPackageName());

        Food a = new Food("양식", "치킨", 30000, "후라이드");
        Food b = new Food("한식", "냉면", 9000, "비빔냉면");
        Food c = new Food("일식", "초밥", 16000, "광어초밥");


        //db.insertOrderRecord("mj", a);
        //db.insertOrderRecord("mj", b);
        //db.insertOrderRecord("mj", c);

        db.insertMenu(a);
        db.insertMenu(b);
        db.insertMenu(c);

        List<Food> temp = db.getMenus();

        for(Food food : temp)
            System.out.println(food.getFood_category()+" "+food.getFood_name()+" "+food.getPrice()+" "+food.getDescription());

        db.deleteMenu("냉면");
        temp = db.getMenus();
        for(Food food : temp)
           System.out.println(food.getFood_category()+" "+food.getFood_name()+" "+food.getPrice()+" "+food.getDescription());


        menuList = db.retrieveMenuforUser("mj");
        for(Food food : menuList)
            System.out.println(food.getFood_category()+" "+food.getFood_name()+" "+food.getPrice());



        //assertEquals("일치", menuList.get(0).getFood_name(), "치킨");


    }
}