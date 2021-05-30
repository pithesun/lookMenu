package com.example.lookmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuAdapter.OnMenuListener {

    String TAG = "MenuActivity";

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

        mAdapter = new MenuAdapter(mList, this);
        mView.setAdapter(mAdapter);

        fc = new ViewModelProvider(this).get(FirebaseConnection.class);
        fc.getMenuForUser().observe(this, menus -> {
            for( Menu menu : menus) {
                addMenuItem(R.drawable.pork_cutlet,
                        menu.name, menu.price, menu.info);
            }
            mAdapter.notifyDataSetChanged();
        });
        fc.getRecommendation().observe(this, recommends -> {
            for( Menu menu : recommends) {
               Log.d("MenuActivity", menu.name);
            }
        });
    }

    public void addMenuItem(int icon, String title, String price, String desc) {
        MenuItem item = new MenuItem();

        item.setImage(icon);
        item.setTitle(title);
        item.setPrice(price);
        item.setDesc(desc);

        mList.add(item);
    }

    @Override
    public void onMenuClick(int position) {
        Log.d(TAG, "onMenuClick: ");
        MenuItem selectedMenu = mList.get(position);
        Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
        intent.putExtra("selectedMenu", selectedMenu);
        startActivity(intent);
    }
}