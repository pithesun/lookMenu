package com.example.lookmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    RecyclerView mView = null;
    MenuAdapter mAdapter = null;
    ArrayList<MenuItem> mList = new ArrayList<MenuItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mView = findViewById(R.id.recycler1);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mView.setLayoutManager(manager);

        mAdapter = new MenuAdapter(mList);
        mView.setAdapter(mAdapter);

        addItem(R.drawable.pork_cutlet,
                "왕돈가스", "4000", "맛있는 돈가스") ;
        addItem(R.drawable.sushi,
                "왕왕", "4000", "맛있는 돈가스") ;
        addItem(R.drawable.pork_cutlet,
                "왕돈가스", "4000", "맛있는 돈가스") ;
        addItem(R.drawable.sushi,
                "왕왕", "4000", "맛있는 돈가스") ;
        addItem(R.drawable.pork_cutlet,
                "왕돈가스", "4000", "맛있는 돈가스") ;
        addItem(R.drawable.sushi,
                "왕왕", "4000", "맛있는 돈가스") ;

        mAdapter.notifyDataSetChanged() ;
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