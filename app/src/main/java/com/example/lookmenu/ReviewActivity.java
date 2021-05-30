package com.example.lookmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {

    String TAG = "ReviewActivity";

    RecyclerView mView = null;
    ReviewAdapter mAdapter = null;
    ArrayList<ReviewItem> mList = new ArrayList<>();
    FirebaseConnection fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        /////////////////////////////////////
        /* included layout에 값을 넣기 위해 data binding 필요 */
        Intent intent = getIntent();
        MenuItem menuItem = (MenuItem) intent.getSerializableExtra("selectedMenu");
        Log.d(TAG, menuItem.getTitle());
        ////////////////////////////////////

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

        /* Test Data */
//        addReviewItem("user4321", "20210529",  "일단 돼지1kg 시켰는데 저울 장난안쳤고요...밑반찬도 좋습니다. 다만 워터에이징 싫어하면 다른데 가시는걸 추천합니다.");
//        addReviewItem("user4322", "20210429", "서비스는 좋았지만 고기의 풍미가 살아나지 못한 것이 아쉬웠어요ㅠ.하지만 냉면 맛이 좋아서 냉면을 잘 먹었어요ㅎㅎ");

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