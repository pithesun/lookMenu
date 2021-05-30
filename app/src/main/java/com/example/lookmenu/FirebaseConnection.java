package com.example.lookmenu;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseConnection extends ViewModel {
    private String TAG = "FirebaseConnection";
    private DatabaseReference db;
    private MutableLiveData<ArrayList<Menu>> menus;
    private MutableLiveData<ArrayList<Menu>> Recommends;
    private MutableLiveData<ArrayList<ReviewTest>> selected_reviews; // select된 review 담기 위해
    private ArrayList<Order> user_orderHistory;
    private RecommendSystem rms;
    private String userId;

    public FirebaseConnection(){
        db = FirebaseDatabase.getInstance().getReference();
        /* 임시코드 */
        userId = "user1234";
    }

    public LiveData<ArrayList<Menu>> getMenuForUser() {
        /*recommends도 반환할 예정*/
        if(menus == null) {
            menus = new MutableLiveData<>();
            retrieveMenus();
        }
        return menus;
    }

    public MutableLiveData<ArrayList<Menu>> getRecommendation() {
        if(user_orderHistory == null){
            user_orderHistory = new ArrayList<>();
            Recommends = new MutableLiveData<>();
            search_user_history(userId);
        }
        return Recommends;
    }

    public MutableLiveData<ArrayList<ReviewTest>> getReviews(String food_name){
        if(selected_reviews == null) {
            selected_reviews = new MutableLiveData<>();
            retrieveReviews(food_name);
        }
        return selected_reviews;
    }

    private void retrieveRecommends(){
        System.out.println("loadRecommends");
        if(user_orderHistory != null){
            rms = new RecommendSystem(user_orderHistory);
            Recommends.setValue(rms.makeRecommendations());
        }
    }

    private void retrieveMenus() {
        ArrayList<Menu> menuList = new ArrayList<>();

        db.child("menu").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Object data = dataSnapshot.getValue();
                Log.d(TAG, String.valueOf(data));
                if (dataSnapshot.exists()) {
                    for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                        Menu item = singleSnapshot.getValue(Menu.class);
                        menuList.add(item);
                    }
                }
                menus.setValue(menuList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    private void search_user_history(String userid)
    {
        db.child("order_history").orderByChild("userid").equalTo(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    for (DataSnapshot singleSnapshot : snapshot.getChildren()) {
                        //Object order = singleSnapshot.getValue();
                        Order a = singleSnapshot.getValue(Order.class);
                        //System.out.println(order);
                        user_orderHistory.add(a);
                    }
                }
                retrieveRecommends();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    private void retrieveReviews(String food_name) // 이름으로 검색
    {
        ArrayList<ReviewTest> review_history = new ArrayList<>(); // food_name과 일치하는 review들

        db.child("reviews").orderByChild("food_name").equalTo(food_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot singleSnapshot : snapshot.getChildren()) {
                        Object review = singleSnapshot.getValue();
                        System.out.print(review);
                        ReviewTest a = singleSnapshot.getValue(ReviewTest.class);
                        //System.out.println(order);
                        review_history.add(a);
                    }
                }
                selected_reviews.setValue(review_history); // 검색결과 있을시
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
}