package com.example.quanlycanbo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlycanbo.models.CanBo;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    private List<CanBo> canBoList;

    // Constructor
    public ItemAdapter(List<CanBo> canBoList) {
        this.canBoList = canBoList;
    }

    @Override
    public int getCount() {
        return canBoList.size();
    }

    @Override
    public Object getItem(int position) {
        return canBoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
            viewHolder.tv_id = view.findViewById(R.id.tv_id);
            viewHolder.tv_ma_can_bo = view.findViewById(R.id.tv_ma_can_bo);
            viewHolder.tv_ho_ten = view.findViewById(R.id.tv_ho_ten);
            viewHolder.tv_gioi_tinh = view.findViewById(R.id.tv_gioi_tinh);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final CanBo canBo = canBoList.get(position);

        viewHolder.tv_id.setText(Integer.toString(canBo.getId()));
        viewHolder.tv_ma_can_bo.setText(canBo.getMa_can_bo());
        viewHolder.tv_ho_ten.setText(canBo.getHo_ten());
        viewHolder.tv_gioi_tinh.setText(canBo.getGioi_tinh());

        return view;
    }

    class ViewHolder {
        TextView tv_id;
        TextView tv_ma_can_bo;
        TextView tv_ho_ten;
        TextView tv_gioi_tinh;
    }

}
