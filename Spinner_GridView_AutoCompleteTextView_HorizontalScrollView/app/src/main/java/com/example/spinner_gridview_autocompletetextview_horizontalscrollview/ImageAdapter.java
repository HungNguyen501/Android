package com.example.spinner_gridview_autocompletetextview_horizontalscrollview;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    Integer[] items;
    Context context;
    int itemWidth;

    public ImageAdapter(Integer[] items, Context context) {
        this.items = items;
        this.context = context;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((GridViewActivity)context).getWindowManager().getDefaultDisplay().getMetrics((displayMetrics));
        itemWidth = displayMetrics.widthPixels / 3;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ImageView imageView;
        if(view == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(itemWidth, itemWidth));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            imageView = (ImageView) view;
        }

        imageView.setImageResource(items[position]);

        return imageView;
    }
}
