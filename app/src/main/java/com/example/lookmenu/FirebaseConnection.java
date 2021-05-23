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

    /* user order history 추가 예정 */

    public FirebaseConnection(){
        db = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<ArrayList<Menu>> getMenuForUser() {
        /*recommends도 반환할 예정*/
        if(menus == null) {
            menus = new MutableLiveData<>();
            loadMenus();
        }
        return menus;
    }

    public LiveData<ArrayList<Menu>> getRecommendation() {
        if(Recommends == null) {
            Recommends = new MutableLiveData<>();
            loadRecommends();
        }
        return Recommends;
    }

    private void loadRecommends(){
        /*
         * Recommendation System에서 계산한 추천 메뉴 가져오기
         */
    }


    private void loadMenus() {
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

    /*load reviews 추가 예정*/
    /*load user history 추가 예정 */
}