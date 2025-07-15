package com.example.app;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    String[] titles = {"airplane page", "apple Page", "laptop Page"};
    int[] images = {R.drawable.airplane1, R.drawable.apple, R.drawable.laptop};

    @NonNull
    @Override
    public ViewPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.page_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter.ViewHolder holder, int position) {
        holder.pageTitle.setText(titles[position]);
        holder.pageImage.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView pageTitle;
        ImageView pageImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pageTitle = itemView.findViewById(R.id.pageTitle);
            pageImage = itemView.findViewById(R.id.pageImage);
        }
    }
}
