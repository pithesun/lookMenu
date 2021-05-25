package com.example.myapplication;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Test;

import static org.junit.Assert.*;



import java.lang.Object;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");

        Calendar time =  Calendar.getInstance();

        String temp = format1.format(time.getTime());
        String id = "user1";
        temp+=id;
        System.out.println(temp);
        System.out.println(temp.substring(0,temp.length()-id.length()));

    }
}