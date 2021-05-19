package com.example.lookmenu;


import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseConnection{
    private String TAG = "FirebaseConnection";
    private DatabaseReference db;

    public FirebaseConnection(){
        db = FirebaseDatabase.getInstance().getReference();
    }

    public ArrayList<Menu> getMenuForUser() {
        ArrayList<Menu> Menus = new ArrayList<Menu>();

        // [START post_value_event_listener]
        db.child("menu").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Object data = dataSnapshot.getValue();
                Log.d(TAG, String.valueOf(data));
                if (dataSnapshot.exists()) {
                    for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                        Menu item = singleSnapshot.getValue(Menu.class);
                        Menus.add(item);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }

            // [END post_value_event_listener]
        });

        return Menus;
    }
}