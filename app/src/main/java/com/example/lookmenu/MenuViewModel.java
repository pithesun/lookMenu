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

public class MenuViewModel extends ViewModel {
    private String TAG = "FirebaseConnection";
    private DatabaseReference db;
    private MutableLiveData<ArrayList<Menu>> menus;
    private MutableLiveData<ArrayList<Menu>> Recommends;
    private ArrayList<Order> user_orderHistory;
    private RecommendSystem rms;

    public MenuViewModel(){
        db = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<ArrayList<Menu>> getMenuForUser() {
        /*recommends도 반환할 예정*/
        if(menus == null) {
            menus = new MutableLiveData<>();
            retrieveMenus();
        }
        return menus;
    }

    public MutableLiveData<ArrayList<Menu>> getRecommendation(String userId) {
        if(user_orderHistory == null){
            user_orderHistory = new ArrayList<>();
            Recommends = new MutableLiveData<>();
            search_user_history(userId);
        }
        return Recommends;
    }

    private void retrieveRecommends(){
        System.out.println("loadRecommends");
        if(user_orderHistory != null){
            rms = RecommendSystem.getInstance();
            Recommends.setValue(rms.makeRecommendations(user_orderHistory));
        }
    }

    private void retrieveMenus() {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
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
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
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
}
