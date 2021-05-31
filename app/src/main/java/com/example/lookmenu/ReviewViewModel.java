package com.example.lookmenu;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReviewViewModel extends ViewModel {
    private String TAG = "FirebaseConnection";
    private DatabaseReference db;
    private MutableLiveData<ArrayList<ReviewTest>> selected_reviews; // select된 review 담기 위해

    public ReviewViewModel(){
        db = FirebaseDatabase.getInstance().getReference();
    }

    public MutableLiveData<ArrayList<ReviewTest>> getReviews(String food_name){
        if(selected_reviews == null) {
            selected_reviews = new MutableLiveData<>();
            retrieveReviews(food_name);
        }
        return selected_reviews;
    }

    private void retrieveReviews(String food_name) // 이름으로 검색
    {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
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