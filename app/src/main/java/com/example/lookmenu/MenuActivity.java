package com.example.lookmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    RecyclerView mView = null;
    MenuAdapter mAdapter = null;
    ArrayList<MenuItem> mList = new ArrayList<>();
    FirebaseConnection fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mView = findViewById(R.id.recycler1);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mView.setLayoutManager(manager);

        mAdapter = new MenuAdapter(mList);
        mView.setAdapter(mAdapter);

        fc = new ViewModelProvider(this).get(FirebaseConnection.class);
        fc.getMenuForUser().observe(this, menus -> {
            for( Menu menu : menus) {
                addItem(R.drawable.pork_cutlet,
                        menu.name, menu.price, menu.info);
            }
            mAdapter.notifyDataSetChanged();
        });
        fc.getRecommendation().observe(this, recommends -> {
            for( Menu menu : recommends) {
               Log.d("MenuActivity", menu.name);
            }
        });
        //        addItem(R.drawable.pork_cutlet,
        //                "왕돈가스", "4000", "맛있는 돈가스");
        //        addItem(R.drawable.sushi,
        //                "왕왕", "4000", "맛있는 돈가스");
    }

    public void addItem(int icon, String title, String price, String desc) {
        MenuItem item = new MenuItem();

        item.setImage(icon);
        item.setTitle(title);
        item.setPrice(price);
        item.setDesc(desc);

        mList.add(item);
    }
}