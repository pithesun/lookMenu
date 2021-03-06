package com.example.lookmenu;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lookmenu.databinding.MenuItemBinding;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private ArrayList<MenuItem> mData;
    private OnMenuListener mOnMenuListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView menu_image;
        private TextView menu_title, menu_price, menu_desc;
        private OnMenuListener onMenuListener;

        public ViewHolder(View view, OnMenuListener onMenuListener) {
            super(view);

            menu_image = view.findViewById(R.id.menuImage);
            menu_title = (TextView) view.findViewById(R.id.menuTitle);
            menu_price = (TextView) view.findViewById(R.id.menuPrice);
            menu_desc = (TextView) view.findViewById(R.id.menuDesc);

            this.onMenuListener = onMenuListener;
            view.setOnClickListener(this);
        }

        public ImageView getImageView() {
            return menu_image;
        }
        public TextView getMenu_title() {
            return menu_title;
        }
        public TextView getMenu_price() {
            return menu_price;
        }
        public TextView getMenu_desc() {
            return menu_desc;
        }

        @Override
        public void onClick(View v) {
            onMenuListener.onMenuClick(getAdapterPosition());
        }
    }
    public interface OnMenuListener{
        void onMenuClick(int position);
    }

    public MenuAdapter(ArrayList<MenuItem> list, OnMenuListener onMenuListener) {
        mData = list;
        mOnMenuListener = onMenuListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.menu_item, viewGroup, false);
        return new ViewHolder(view, mOnMenuListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final MenuItem item = mData.get(position) ;

        viewHolder.getImageView().setImageResource(item.getImage());
        viewHolder.getMenu_title().setText(item.getTitle());
        viewHolder.getMenu_price().setText(item.getPrice());
        viewHolder.getMenu_desc().setText(item.getDesc());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
