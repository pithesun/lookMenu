package com.example.lookmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuAdapter.OnMenuListener {

    String TAG = "MenuActivity";

    RecyclerView mView = null;
    MenuAdapter mAdapter = null;
    ArrayList<MenuItem> mList = new ArrayList<>();
    MenuViewModel fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        String userId = "user1234";

        mView = findViewById(R.id.recycler1);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mView.setLayoutManager(manager);

        mAdapter = new MenuAdapter(mList, this);
        mView.setAdapter(mAdapter);

        fc = new ViewModelProvider(this).get(MenuViewModel.class);
        fc.getMenuForUser().observe(this, menus -> {
            Log.d(TAG, "onCreate: + getmenuforuser");
            for( Menu menu : menus) {
                addMenuItem(R.drawable.pork_cutlet,
                        menu.name, menu.price, menu.info);
            }
            mAdapter.notifyDataSetChanged();
        });
        fc.getRecommendation(userId).observe(this, recommends -> {
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
        MenuItem selectedMenu = mList.get(position);

        Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
        intent.putExtra("selectedMenu", selectedMenu);
        startActivity(intent);
    }
}