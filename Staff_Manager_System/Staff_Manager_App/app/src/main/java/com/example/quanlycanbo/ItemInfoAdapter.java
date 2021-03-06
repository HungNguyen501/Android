package com.example.quanlycanbo;

import android.animation.TypeEvaluator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlycanbo.models.CanBo;
import com.example.quanlycanbo.models.ConCanBo;
import com.example.quanlycanbo.models.MonGiangDay;
import com.example.quanlycanbo.models.ThongTinGiangDay;

import java.lang.reflect.Type;
import java.util.List;

public class ItemInfoAdapter extends BaseAdapter {
    private CanBo canBo;
    private String isWhat;

    // Constructor
    public ItemInfoAdapter(CanBo canBo, String isWhat) {
        this.canBo = canBo;
        this.isWhat = isWhat;
    }

    public CanBo getCanBo() {
        return canBo;
    }

    public void setCanBo(CanBo canBo) {
        this.canBo = canBo;
    }

    public String getIsWhat() {
        return isWhat;
    }

    public void setIsWhat(String isWhat) {
        this.isWhat = isWhat;
    }

    @Override
    public int getCount() {
        if (isWhat.equals("GiaDinh")) {
            return canBo.getGiaDinh().getConCanBoList().size();
        } else if (isWhat.equals("ThongTinGiangDay")) {
            return  canBo.getThongTinGiangDay().getMonGiangDayList().size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (isWhat.equals("GiaDinh")) {
            return canBo.getGiaDinh().getConCanBoList().get(position);
        } else if (isWhat.equals("ThongTinGiangDay")) {
            return  canBo.getThongTinGiangDay().getMonGiangDayList().get(position);
        }

        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ItemInfoAdapter.ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ItemInfoAdapter.ViewHolder();
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_info, parent, false);
            viewHolder.tv_content = view.findViewById(R.id.tv_item_content);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ItemInfoAdapter.ViewHolder) view.getTag();
        }

        if (isWhat.equals("GiaDinh")) {
            final ConCanBo conCanBo = canBo.getGiaDinh().getConCanBoList().get(position);
            viewHolder.tv_content.setText("");
            viewHolder.tv_content.append("\t\t???\tH??? t??n con: " + conCanBo.getHo_ten() + "\n");
            viewHolder.tv_content.append("\t\t???\tN??m sinh: " + conCanBo.getNam_sinh() + "\n");
            viewHolder.tv_content.append("\t\t???\tTh??nh t??ch: " + conCanBo.getThanh_tich());
        } else if (isWhat.equals("ThongTinGiangDay")) {
            final MonGiangDay monGiangDay = canBo.getThongTinGiangDay().getMonGiangDayList().get(position);
            viewHolder.tv_content.setText("");
            viewHolder.tv_content.append("\t\t???\tM?? m??n h???c: " + monGiangDay.getMa_mon_hoc() + "\n");
            viewHolder.tv_content.append("\t\t???\tT??n m??n: " + monGiangDay.getTen_mon() + "\n");
            viewHolder.tv_content.append("\t\t???\tS??? t??n ch???: " + monGiangDay.getSo_tin_chi() + "\n");
            viewHolder.tv_content.append("\t\t???\tM?? l???p: " + monGiangDay.getMa_lop() + "\n");
            viewHolder.tv_content.append("\t\t???\tS??? sinh vi??n: " + monGiangDay.getSo_sinh_vien() + "\n");
            viewHolder.tv_content.append("\t\t???\tH???c k???: " + monGiangDay.getHoc_ky() + "\n");
            viewHolder.tv_content.append("\t\t???\tN??m h???c: " + monGiangDay.getNam_hoc() + "\n");
        }

        return view;
    }

    class ViewHolder {
        TextView tv_content;
    }

}
