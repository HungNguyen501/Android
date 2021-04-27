package com.example.gmailapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AutoCompleteItemAdapter extends ArrayAdapter<ItemModel> {
    private  List<ItemModel> itemsFull;

    public AutoCompleteItemAdapter(@NonNull Context context, @NonNull List<ItemModel> items) {
        super(context, 0, items);
        this.itemsFull = new ArrayList<>(items);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return itemFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.layout_item, parent, false);
        }

        String [] color = {"red", "green", "CYAN", "blue", "gray", "lightgray", "MAGENTA", "yellow"};
        TextView tv_label = view.findViewById(R.id.tv_label);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_subject = view.findViewById(R.id.tv_subject);
        TextView tv_content = view.findViewById(R.id.tv_content);
        TextView tv_time = view.findViewById(R.id.tv_time);
        GradientDrawable background_color = (GradientDrawable) tv_label.getBackground().mutate();
        ImageButton imgBtn = view.findViewById(R.id.img_favarite);

        ItemModel matched_item = getItem(position);

        if (matched_item != null) {
            String name = matched_item.getName();

            tv_label.setText(String.valueOf(name.charAt(0)));
            tv_name.setText(name);
            tv_subject.setText(matched_item.getSubject());
            tv_content.setText(matched_item.getContent());
            tv_time.setText(matched_item.getTime());
            background_color.setColor(Color.parseColor( color[matched_item.getColorLabel()] ) );

            if (matched_item.isFavorite()) {
                imgBtn.setImageResource(R.drawable.ic_full_star);
            } else {
                imgBtn.setImageResource(R.drawable.ic_empty_star);
            }
        }

        return view;
    }

    private Filter itemFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<ItemModel> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(itemsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (int i = 0; i < itemsFull.size(); i++) {
                    if (itemsFull.get(i).getName().toLowerCase().contains(filterPattern)
                    || itemsFull.get(i).getSubject().toLowerCase().contains(filterPattern)
                    || itemsFull.get(i).getContent().toLowerCase().contains(filterPattern)
                    ) {
                        suggestions.add(itemsFull.get(i));
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return null;
        }
    };

}
