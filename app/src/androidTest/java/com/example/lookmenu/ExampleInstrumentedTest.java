package com.example.lookmenu;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        //Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //assertEquals("com.example.lookmenu", appContext.getPackageName());
        FirebaseDatabase mdb = FirebaseDatabase.getInstance();


        DatabaseReference ord = mdb.getReference();

        Menu a = new Menu("3000", "³Ã¸é", "¾óÀ½µ¿µ¿", "ÇÑ½Ä");
        Menu b = new Menu("3000", "ºñºö³Ã¸é", "¸ÅÄÞ»õÄÞ", "ÇÑ½Ä");
        Menu c = new Menu("7000", "Ä¡µ·", "Ä¡Áîµë»Ò", "¾ç½Ä");

        ord.child("menu").push().setValue(a);
        ord.child("menu").push().setValue(b);
        ord.child("menu").push().setValue(c);

        ArrayList<Menu> temp = new ArrayList<>();

        temp.add(a);
        temp.add(b);

        Orders od = new Orders(temp,"user11");

        temp.clear();
        temp.add(c);
        System.out.println("½ÃÀÛ");
        Orders od1 = new Orders(temp, "user13");

        ord.child("order_history").push().setValue(od);
        ord.child("order_history").push().setValue(od1);


    }
}