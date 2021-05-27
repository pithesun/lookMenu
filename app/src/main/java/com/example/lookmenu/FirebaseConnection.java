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
    private ArrayList<Order> user_orderHistory;
    private RecommendSystem rms;
    private String userId;
    /* user order history 추가 예정 */

    public FirebaseConnection(){
        db = FirebaseDatabase.getInstance().getReference();
        /* 임시코드 */
        userId = "user1234";
    }

    public LiveData<ArrayList<Menu>> getMenuForUser() {
        /*recommends도 반환할 예정*/
        if(menus == null) {
            menus = new MutableLiveData<>();
            loadMenus();
        }
        return menus;
    }

    public MutableLiveData<ArrayList<Menu>> getRecommendation() {
        if(user_orderHistory == null){
            user_orderHistory = new ArrayList<>();
            Recommends = new MutableLiveData<>();
            loadOrderHistory();
        }
        return Recommends;
    }

    private void loadRecommends(){
        System.out.println("loadRecommends");
        if(user_orderHistory != null){
            rms = new RecommendSystem(user_orderHistory);
            Recommends.setValue(rms.makeRecommendations());
        }
    }
    private void loadOrderHistory(){
        Log.d(TAG, "loadOrderHistory");
        db.child("user-orderhis").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                        Order order = singleSnapshot.getValue(Order.class);
                        user_orderHistory.add(order);
                    }
                }
//                System.out.println(Recommends);
                loadRecommends();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
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
}