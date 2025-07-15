package com.example.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter
{
    Context context;
    int[] images;
    public ImageAdapter(Context context ,int[] images)
    {
        this.context = context;
        this.images=images;

    }
    public int getCount()
    {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(images[position]);
        imageView.setLayoutParams(new Gallery.LayoutParams(150, 150));
        return imageView;
    }
}