package com.example.myapplication;

import android.content.*;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.util.*;

import java.util.*;




public class DataBase {

    static final String dbName = "FoodServicess";
    static final String table1 = "OrderHistory";
    static final String table2 = "Menus";
    Context context;

    public DataBase(Context context)
    {
        this.context = context;
    }



    //db 호출
    SQLiteDatabase getDatabase()
    {
        SQLiteDatabase db = context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
        //db.execSQL("DELETE FROM "+table1);
        //db.execSQL("DELETE FROM "+table2);
        // 테이블 없을시 생성
        db.execSQL("CREATE TABLE IF NOT EXISTS "+table1+" (id text , category text, food_name text, price Integer, primary key(id, category, food_name, price))");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+table2+" (category text, food_name text, price Integer, description text)");

        return db;
    }

    // 주문기록 table
    public int insertOrderRecord(String userid, Food food)
    {
        int res = 0;
        SQLiteDatabase db = null;
        try{
            db = getDatabase();
            String query = String.format("insert into %s(id, category, food_name, price) values('%s', '%s', '%s', '%d')", table1, userid, food.getFood_category(), food.getFood_name(), food.getPrice());
            db.execSQL(query);
            res=1;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(db!=null)
                db.close();
        }
        return res;
    }

    // 메뉴 table
    public int insertMenu(Food food)
    {
        int res = 0;
        SQLiteDatabase db = null;
        try{
            db = getDatabase();
            String query = String.format("insert into %s(category, food_name, price, description) values('%s', '%s', '%d', '%s')",table2, food.getFood_category(), food.getFood_name(), food.getPrice(), food.getDescription());
            db.execSQL(query);
            res = 1;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }finally{
            if(db!=null)
                db.close();
        }
        return res;
    }


    // 주문기록 table 삭제가 필요할지 고민...
    // 혹시 필요하게 되면 구현하는걸로

    // menu 삭제
    // 계절 음식등등 판매자가 자유롭게 메뉴 추가 제거가 가능해야함.
    public int deleteMenu(String food_name)
    {
        int res = 0;
        SQLiteDatabase db = null;

        try{
            db = getDatabase();
            String query = String.format("delete from %s where food_name = '%s'", table2, food_name);
            db.execSQL(query);
            res =1;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }finally{
            if(db!=null)
                db.close();
        }

      return res;

    }

    // update 구현할지 미정
    // 음식 가격 변동을 생각하면 있어야할 것 같긴함.
    // 추후 논의해서 결정

    // user order histroy와 menu list를 반환
    // 자바 다중값 return이 안됨
    // 멤버 변수를 추가해서 set하는식으로 할지 고민
    // 우선 orderhistory를 return하는걸로 정의
    List<Food> retrieveMenuforUser(String userid)
    {
        List<Food> res = new ArrayList<Food>();

        SQLiteDatabase db = null;

        Cursor cursor = null;
        String[] col_names = {"id", "category", "food_name", "price"};
        // 추천 메뉴 return은 설명이 굳이 필요없다고 판단하여 우선은 null로 set하겠음
        // 이 부분은 추후 논의
        String selection = "id = ?";
        String[] selectionArgs = {userid};
        try{
            db = getDatabase();

            cursor = db.query(table1, col_names, selection, selectionArgs, null, null, null);
            if(cursor!=null)
            {
                if(cursor.moveToFirst()) // record
                {
                    // do while
                    do{
                        //String id = cursor.getString(0);
                        String category = cursor.getString(1);
                        String food_name = cursor.getString(2);
                        int price = cursor.getInt(3);

                        res.add(new Food(category, food_name, price, ""));
                    }while(cursor.moveToNext());
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }finally
        {
            if(cursor!=null)
                cursor.close();
            if(db!=null)
                cursor.close();
        }

    return res;

    }

    List<Food> getMenus()
    {
        List<Food> res = new ArrayList<Food>();

        SQLiteDatabase db = null;

        Cursor cursor = null;
        String[] col_names = {"category", "food_name", "price", "description"};
        // 추천 메뉴 return은 설명이 굳이 필요없다고 판단하여 우선은 null로 set하겠음
        // 이 부분은 추후 논의

        try{
            db = getDatabase();

            cursor = db.query(table2, col_names, null, null, null, null, null);
            if(cursor!=null)
            {
                if(cursor.moveToFirst()) // record
                {
                    // do while
                    do{

                        String category = cursor.getString(0);
                        String food_name = cursor.getString(1);
                        int price = cursor.getInt(2);
                        String description = cursor.getString(3);

                        res.add(new Food(category, food_name, price, description));
                    }while(cursor.moveToNext());
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }finally
        {
            if(cursor!=null)
                cursor.close();
            if(db!=null)
                cursor.close();
        }

        return res;

    }








}
