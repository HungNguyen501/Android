package com.example.gmailapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class ItemAdapter extends BaseAdapter {

    private List<ItemModel> items;

    public ItemAdapter(List<ItemModel> items) {
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        String [] color = {"red", "green", "CYAN", "blue", "gray", "lightgray", "MAGENTA", "yellow"};

        if (view == null) {
            viewHolder = new ViewHolder();

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);

            viewHolder.tv_label = view.findViewById(R.id.tv_label);
            viewHolder.tv_name = view.findViewById(R.id.tv_name);
            viewHolder.tv_subject = view.findViewById(R.id.tv_subject);
            viewHolder.tv_content = view.findViewById(R.id.tv_content);
            viewHolder.tv_time = view.findViewById(R.id.tv_time);
            viewHolder.imgBtn_favorite = view.findViewById(R.id.img_favarite);
            viewHolder.backgound_color = (GradientDrawable) viewHolder.tv_label.getBackground().mutate();

            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();

        final ItemModel item = items.get(i);

        String name = item.getName();
        viewHolder.tv_label.setText(String.valueOf(name.charAt(0)));
        viewHolder.tv_name.setText(name);
        viewHolder.tv_subject.setText(item.getSubject());
        viewHolder.tv_content.setText(item.getContent());
        viewHolder.tv_time.setText(item.getTime());
        //set background color for label
        viewHolder.backgound_color.setColor(Color.parseColor( color[item.getColorLabel()] ));

        if (item.isFavorite()) viewHolder.imgBtn_favorite.setImageResource(R.drawable.ic_full_star);
        else viewHolder.imgBtn_favorite.setImageResource(R.drawable.ic_empty_star);

        viewHolder.imgBtn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setFavorite(!item.isFavorite());
                notifyDataSetChanged();
            }
        });

        return view;
    }

    class ViewHolder {
        TextView tv_label;
        TextView tv_name;
        TextView tv_subject;
        TextView tv_content;
        TextView tv_time;
        ImageButton imgBtn_favorite;
        GradientDrawable backgound_color; //change background color of label
    }
}
