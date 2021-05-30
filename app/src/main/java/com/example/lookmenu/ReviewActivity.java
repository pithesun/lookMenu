package com.example.lookmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import com.example.lookmenu.databinding.ActivityReviewBinding;


public class ReviewActivity extends AppCompatActivity {

    String TAG = "ReviewActivity";

    RecyclerView mView = null;
    ReviewAdapter mAdapter = null;
    ArrayList<ReviewItem> mList = new ArrayList<>();
    FirebaseConnection fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityReviewBinding mBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_review);

        Intent intent = getIntent();
        MenuItem menuItem = (MenuItem) intent.getSerializableExtra("selectedMenu");
        Log.d(TAG, menuItem.getTitle());

        mBinding.setReviewMenu(menuItem);

        mView = findViewById(R.id.recycler1);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mView.setLayoutManager(manager);

        mAdapter = new ReviewAdapter(mList);
        mView.setAdapter(mAdapter);

        fc = new ViewModelProvider(this).get(FirebaseConnection.class);
        fc.getReviews(menuItem.getTitle()).observe(this, reviews -> {
            for( ReviewTest review : reviews) {
                addReviewItem(review.userid, review.date, review.review);
            }
            mAdapter.notifyDataSetChanged();
        });

        mAdapter.notifyDataSetChanged();
    }

    public void addReviewItem(String authorId, String reviewDate, String reviewContent) {
        ReviewItem item = new ReviewItem();

        item.setAuthorId(authorId);
        item.setReviewDate(reviewDate);
        item.setReviewContent(reviewContent);

        mList.add(item);
    }
}