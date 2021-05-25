package com.example.myapplication;

import android.content.*;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.util.*;

import java.text.SimpleDateFormat;
import java.util.*;




public class DataBase {

    static final String dbName = "FoodService_test11";
    static final String table1 = "OrderHistory";
    static final String table2 = "Menus";
    static final String table3 = "Review";
    SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss"); // 주문 번호 생성을 위한 패턴
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
        db.execSQL("CREATE TABLE IF NOT EXISTS "+table1+" (order_number text primary key, id text , category text, food_name text, price Integer)"); // 주문 번호 id+날짜
        db.execSQL("CREATE TABLE IF NOT EXISTS "+table2+" (category text, food_name text primary key, price Integer, description text)");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+table3+"(review_number text primary key, id text, food_name text, review text)"); // 리뷰 번호 id + 날짜
        return db;
    }

    // 주문기록 table
    public int insertOrderRecord(String userid, Food food)
    {
        int res = 0;
        SQLiteDatabase db = null;
        Calendar time = Calendar.getInstance();
        String order_num = format1.format(time.getTime());
        order_num+=userid;
        try{
            db = getDatabase();
            String query = String.format("insert into %s(order_number, id, category, food_name, price) values('%s', '%s', '%s', '%s', '%d')", table1, order_num, userid, food.getFood_category(), food.getFood_name(), food.getPrice());
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

    // insert review
    public int insertReview(Review review)
    {
        int res = 0;
        SQLiteDatabase db = null;
        try{
            db = getDatabase();
            String query = String.format("insert into %s(review_number, id, food_name, review) values('%s', '%s', '%s', '%s')",table3, review.getDate()+review.getId(), review.getId(), review.getFood_name(), review.getReview());
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

    // 사용자가 리뷰를 삭제하고 싶은경우
    // 사용자가 삭제 기능을 호출시 실행하는것으로 하기

    public int deleteReview(String review_number)
    {
        int res = 0;
        SQLiteDatabase db = null;

        try{
            db = getDatabase();
            String query = String.format("delete from %s where review_number = '%s'", table3, review_number);
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

    // userid로 review list return


    List<Food> getOrderHistory(String userid)
    {
        List<Food> res = new ArrayList<Food>();

        SQLiteDatabase db = null;

        Cursor cursor = null;
        String[] col_names = {"order_number", "id", "category", "food_name", "price"};
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
                        // 0 order_number 1 id 2 category 3 food_name 4 price
                        String category = cursor.getString(2);
                        String food_name = cursor.getString(3);
                        int price = cursor.getInt(4);

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


    List<Review> getReviews(String userid)
    {
        List<Review> res = new ArrayList<Review>();

        SQLiteDatabase db = null;

        Cursor cursor = null;
        String[] col_names = {"review_number", "id", "food_name", "review"};

        String selection = "id = ?";
        String[] selectionArgs = {userid};
        try{
            db = getDatabase();

            cursor = db.query(table3, col_names, selection, selectionArgs, null, null, null);
            if(cursor!=null)
            {
                if(cursor.moveToFirst()) // record
                {
                    // do while
                    do{
                        // 0 review_number 1 id 2 food_name 3 review
                        String review_number = cursor.getString(0);
                        String id = cursor.getString(1);
                        String food_name = cursor.getString(2);
                        String review = cursor.getString(3);
                        String dates = review_number.substring(0, review_number.length()-id.length()); // 리뷰번호는 date+userid이므로 substr해준다

                        res.add(new Review(id, review, food_name,  dates)); //id review food_name date
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


    List<Review> getAllReviews()
    {
        List<Review> res = new ArrayList<Review>();

        SQLiteDatabase db = null;

        Cursor cursor = null;
        String[] col_names = {"review_number", "id", "food_name", "review"};


        try{
            db = getDatabase();

            cursor = db.query(table3, col_names, null, null, null, null, null);
            if(cursor!=null)
            {
                if(cursor.moveToFirst()) // record
                {
                    // do while
                    do{

                        String review_number = cursor.getString(0);
                        String id = cursor.getString(1);
                        String food_name = cursor.getString(2);
                        String review = cursor.getString(3);
                        String dates = review_number.substring(0, review_number.length()-id.length()); // 리뷰번호는 date+userid이므로 substr해준다

                        res.add(new Review(id, review, food_name,  dates)); //id review food_name date
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
