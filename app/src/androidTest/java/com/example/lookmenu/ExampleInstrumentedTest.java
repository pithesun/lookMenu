package com.example.lookmenu;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.*;



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

        Menu a = new Menu("3000", "냉면", "얼음동동", "한식");
        Menu b = new Menu("3000", "비빔냉면", "매콤새콤", "한식");
        Menu c = new Menu("7000", "치돈", "치즈듬뿍", "양식");

        ord.child("menu").push().setValue(a);
        ord.child("menu").push().setValue(b);
        ord.child("menu").push().setValue(c);

        ArrayList<Menu> temp = new ArrayList<>();

        temp.add(a);
        temp.add(b);

        Orders od = new Orders(temp,"user11");

        temp.clear();
        temp.add(c);
        System.out.println("시작");
        Orders od1 = new Orders(temp, "user13");

        //ord.setValue(od);
        //ord.setValue(od1);





    }
}