package com.example.lookmenu;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private ArrayList<MenuItem> mData = null ;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView menu_image;
        private TextView menu_title, menu_price, menu_desc;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            menu_image = view.findViewById(R.id.menuImage);
            menu_title = (TextView) view.findViewById(R.id.menuTitle);
            menu_price = (TextView) view.findViewById(R.id.menuPrice);
            menu_desc = (TextView) view.findViewById(R.id.menuDesc);
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
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param list ArrayList<MenuItem> containing the data to populate views to be used
     * by RecyclerView.
     */
    public MenuAdapter(ArrayList<MenuItem> list) {
        mData = list;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.menu_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final MenuItem item = mData.get(position) ;
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getImageView().setImageResource(item.getImage());
        viewHolder.getMenu_title().setText(item.getTitle());
        viewHolder.getMenu_price().setText(item.getPrice());
        viewHolder.getMenu_desc().setText(item.getDesc());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mData.size();
    }
}
