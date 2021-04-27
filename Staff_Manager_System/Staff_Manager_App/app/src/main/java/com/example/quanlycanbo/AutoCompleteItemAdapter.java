package com.example.quanlycanbo;

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

import com.example.quanlycanbo.models.CanBo;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AutoCompleteItemAdapter extends ArrayAdapter<CanBo> {
    private List<CanBo> canBoList;

    public AutoCompleteItemAdapter(@NonNull Context context, @NonNull List<CanBo> canBoList) {
        super(context, 0, canBoList);
        this.canBoList = new ArrayList<CanBo>(canBoList);
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

        TextView tv_id = view.findViewById(R.id.tv_id);
        TextView tv_ho_ten = view.findViewById(R.id.tv_ho_ten);
        TextView tv_ma_can_bo = view.findViewById(R.id.tv_ma_can_bo);
        TextView tv_gioi_tinh = view.findViewById(R.id.tv_gioi_tinh);

        CanBo matched_item = getItem(position);

        if (matched_item != null) {
            tv_id.setText(String.valueOf(matched_item.getId()));
            tv_ho_ten.setText(matched_item.getHo_ten());
            tv_ma_can_bo.setText(matched_item.getMa_can_bo());
            tv_gioi_tinh.setText(matched_item.getGioi_tinh());
        }

        return view;
    }

    private Filter itemFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<CanBo> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(canBoList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (int i = 0; i < canBoList.size(); i++) {
                    if (canBoList.get(i).getHo_ten().toLowerCase().contains(filterPattern)
                            || canBoList.get(i).getMa_can_bo().toLowerCase().contains(filterPattern)
                            || canBoList.get(i).getGioi_tinh().toLowerCase().contains(filterPattern)
                    ) {
                        suggestions.add(canBoList.get(i));
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
