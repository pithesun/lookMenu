package com.example.lookmenu;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.ChildEventListener;
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
    private MutableLiveData<ArrayList<Review>> reviews; // 전체 리뷰를 위해
    private MutableLiveData<ArrayList<Review>> selected_reviews; // select된 review 담기 위해
    private ArrayList<Review> review_history; // food_name과 일치하는 review들
    private ArrayList<Order> user_orderHistory;
    private RecommendSystem rms;
    private String userId; // 추천할 사용자 이름
    private String food_name; // 검색할 음식이름 리뷰에서
    /* user order history 추가 예정 */

    public FirebaseConnection(){
        db = FirebaseDatabase.getInstance().getReference();
        /* 임시코드 */
        userId = "user1234";
        food_name = "짬뽕";

       // Review a = new Review("user1212", "진짜 매워요", "짬뽕");

        //db.child("reviews").push().setValue(a);

       //loadReviews();

    }





    public void search_user_history(String userid)
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
                loadRecommends();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    public void retrieveReviews(String food_name) // 이름으로 검색
    {
        db.child("reviews").orderByChild("food_name").equalTo(food_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    for (DataSnapshot singleSnapshot : snapshot.getChildren()) {
                        //Object order = singleSnapshot.getValue();
                        Review a = singleSnapshot.getValue(Review.class);
                        //System.out.println(order);
                        review_history.add(a);
                    }
                }
                loadRecommendReviews();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }



    public LiveData<ArrayList<Menu>> getMenuForUser() {
        /*recommends도 반환할 예정*/
        if(menus == null) {
            menus = new MutableLiveData<>();
            loadMenus();
        }
        return menus;
    }

    public LiveData<ArrayList<Review>> getReviewForUser(){
        if(reviews==null)
        {
            reviews = new MutableLiveData<>();
            loadReviews();
        }
        return reviews;
    }


    public MutableLiveData<ArrayList<Menu>> getRecommendation() {
        if(user_orderHistory == null){
            user_orderHistory = new ArrayList<>();
            Recommends = new MutableLiveData<>();
            search_user_history(userId);
        }
        return Recommends;
    }

    public MutableLiveData<ArrayList<Review>> getRecommended_reviews()
    {
        if(review_history==null)
        {
            review_history = new ArrayList<>();
            selected_reviews = new MutableLiveData<>();
            retrieveReviews(food_name);
        }
        return selected_reviews;
    }


    private void loadRecommends(){
        System.out.println("loadRecommends");
        if(user_orderHistory != null){
            rms = new RecommendSystem(user_orderHistory);
            Recommends.setValue(rms.makeRecommendations());
        }
    }

    private void loadRecommendReviews(){
        System.out.println("Searched Reviews");
        if(review_history!=null)
        {
            selected_reviews.setValue(review_history); // 검색결과 있을시
        }
    }

    /* 해당 함수는 필요없을듯?
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

     */

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





    private void loadReviews() {
        ArrayList<Review> reviewList = new ArrayList<>();

        db.child("reviews").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Object data = dataSnapshot.getValue();
                Log.d(TAG, String.valueOf(data));
                if (dataSnapshot.exists()) {
                    for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                        Review item = singleSnapshot.getValue(Review.class);
                        //System.out.println(item.getFood_name()); 함수는 정상 작동하나 init부분에 넣어서 그런지 오류가 뜨는거같음?
                        reviewList.add(item);
                    }
                }
                reviews.setValue(reviewList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
    }




    /*load reviews 추가 예정*/
}