package com.example.lookmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {

    RecyclerView mView = null;
    ReviewAdapter mAdapter = null;
    ArrayList<ReviewItem> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        mView = findViewById(R.id.recycler1);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mView.setLayoutManager(manager);

        mAdapter = new ReviewAdapter(mList);
        mView.setAdapter(mAdapter);

        /* Test Data */
        addReviewItem("user4321", "20210529",  "일단 돼지1kg 시켰는데 저울 장난안쳤고요...밑반찬도 좋습니다. 다만 워터에이징 싫어하면 다른데 가시는걸 추천합니다.");
        addReviewItem("user4322", "20210429", "서비스는 좋았지만 고기의 풍미가 살아나지 못한 것이 아쉬웠어요ㅠ.하지만 냉면 맛이 좋아서 냉면을 잘 먹었어요ㅎㅎ");

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