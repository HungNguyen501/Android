package com.example.spinner_gridview_autocompletetextview_horizontalscrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HorizontalScrollViewActivity extends AppCompatActivity {

    List<ItemModel> items;
    ImageView imageLarge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroll_view);

        items =  new ArrayList<ItemModel>();
        items.add(new ItemModel("Img 1", R.drawable.thumb1, R.drawable.wall1));
        items.add(new ItemModel("Img 2", R.drawable.thumb2, R.drawable.wall2));
        items.add(new ItemModel("Img 3", R.drawable.thumb3, R.drawable.wall3));
        items.add(new ItemModel("Img 4", R.drawable.thumb4, R.drawable.wall4));
        items.add(new ItemModel("Img 5", R.drawable.thumb5, R.drawable.wall5));
        items.add(new ItemModel("Img 6", R.drawable.thumb6, R.drawable.wall6));
        items.add(new ItemModel("Img 7", R.drawable.thumb7, R.drawable.wall7));
        items.add(new ItemModel("Img 8", R.drawable.thumb8, R.drawable.wall8));
        items.add(new ItemModel("Img 9", R.drawable.thumb9, R.drawable.wall9));
        items.add(new ItemModel("Img 10", R.drawable.thumb10, R.drawable.wall10));

        imageLarge = findViewById(R.id.image_large);

        LinearLayout layoutList = findViewById(R.id.list_image);
        for(int i = 0; i < items.size(); i++) {
            View itemView = LayoutInflater.from(HorizontalScrollViewActivity.this).inflate(R.layout.layout_item, null);
            //set id for itemView
            itemView.setId(i);
            ImageView imageThumb = itemView.findViewById(R.id.image_thumb);
            TextView textCaption = itemView.findViewById(R.id.text_caption);
            imageThumb.setImageResource(items.get(i).getThumbnail());
            textCaption.setText(items.get(i).getCaption());
            layoutList.addView(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = view.getId();
                    imageLarge.setImageResource(items.get(id).getImage());
                }
            });

        }



    }
}
