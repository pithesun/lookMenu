package com.example.lookmenu;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private ArrayList<ReviewItem> mData;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView review_user, review_date, review_content;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            review_user = (TextView) view.findViewById(R.id.review_user);
            review_date = (TextView) view.findViewById(R.id.review_date);
            review_content = (TextView) view.findViewById(R.id.review_content);
        }
        public TextView getReview_user() { return review_user; }
        public TextView getReview_date() {
            return review_date;
        }
        public TextView getReview_content() {
            return review_content;
        }
    }


    public ReviewAdapter(ArrayList<ReviewItem> list) {
        mData = list;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.review_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final ReviewItem item = mData.get(position) ;
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        System.out.println(viewHolder);
        Log.d("ReviewAdapter", item.getAuthorId() );
        viewHolder.getReview_user().setText(item.getAuthorId());
        viewHolder.getReview_date().setText(item.getReviewDate());
        viewHolder.getReview_content().setText(item.getReviewContent());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mData.size();
    }
}
