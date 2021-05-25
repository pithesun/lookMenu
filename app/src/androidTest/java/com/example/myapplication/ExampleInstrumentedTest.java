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


        db.insertOrderRecord("mj", a);
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        db.insertOrderRecord("mj", b);
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        db.insertOrderRecord("mj", c);

        db.insertMenu(a);
        db.insertMenu(b);
        db.insertMenu(c);

        List<Food> temp = db.getMenus(); // 정상작동


        for(Food food : temp)
            System.out.println(food.getFood_category()+" "+food.getFood_name()+" "+food.getPrice()+" "+food.getDescription());

        System.out.println();
        db.deleteMenu("냉면");
        temp = db.getMenus(); // 정상작동


        for(Food food : temp)
            System.out.println(food.getFood_category()+" "+food.getFood_name()+" "+food.getPrice()+" "+food.getDescription());

        System.out.println();
        menuList = db.getOrderHistory("mj");
        for(Food food : menuList)
            System.out.println(food.getFood_category()+" "+food.getFood_name()+" "+food.getPrice());



        /* review 기능 정상 작동 but 컴터 성능에 따라 다르겠지만 시간+id로 하니 연산속도가 빨라 값이 중복되는 현상이 발생하여 삽입되지않는현상발생
        Review a = new Review("mj", "맛있다", "피자");
        Review b = new Review("mja", "맛있다", "피자");
        Review c = new Review("mj", "엄청난 족발", "맛나요 족발", "2322");
        Review d = new Review("mj", "엄청난 족발", "맛나네", "20210796054");
        db.insertReview(a);
        db.insertReview(b);
        db.insertReview(c);
        db.insertReview(d);

        List<Review> alist = db.getAllReviews();
        for(Review r : alist)
            System.out.println(r.getId()+" "+r.getFood_name()+" "+r.getReview()+" "+r.getDate());

        List<Review> rlist = db.getReviews("mj");
        for(Review r : rlist)
            System.out.println(r.getId()+" "+r.getFood_name()+" "+r.getReview()+" "+r.getDate());


       */





        //assertEquals("일치", menuList.get(0).getFood_name(), "치킨");


    }
}