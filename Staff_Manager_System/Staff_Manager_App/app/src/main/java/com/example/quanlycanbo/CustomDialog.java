package com.example.quanlycanbo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlycanbo.ItemInfoAdapter;
import com.example.quanlycanbo.R;
import com.example.quanlycanbo.models.CanBo;
import com.example.quanlycanbo.models.ConCanBo;
import com.example.quanlycanbo.models.MonGiangDay;

import java.util.List;

import androidx.appcompat.app.AlertDialog;

public class CustomDialog {
    Activity activity;
    Context context;
    SQLiteDatabase db;

    public CustomDialog(Activity activity, Context context, SQLiteDatabase db) {
        // Constructor
        this.activity = activity;
        this.context = context;
        this.db = db;
    }

    public void displayInfo(CanBo canBo, TextView tv_thong_tin_ca_nhan, TextView tv_gia_dinh, TextView tv_nghien_cuu_khoa_hoc,
                            TextView tv_thong_tin_giang_day) {

        tv_thong_tin_ca_nhan.setText("Thông tin cá nhân:\n\n");
        tv_thong_tin_ca_nhan.append("\t\t•\tMã cán bộ: " + canBo.getMa_can_bo() + "\n");
        tv_thong_tin_ca_nhan.append("\t\t•\tHọ tên: " + canBo.getHo_ten() + "\n");
        tv_thong_tin_ca_nhan.append("\t\t•\tGiới tính: " + canBo.getGioi_tinh() + "\n");
        tv_thong_tin_ca_nhan.append("\t\t•\tNgày sinh: " + canBo.getNgay_sinh() + "\n");
        tv_thong_tin_ca_nhan.append("\t\t•\tChức danh: " + canBo.getChuc_danh() + "\n");
        tv_thong_tin_ca_nhan.append("\t\t•\tChức vụ: " + canBo.getChuc_vu() + "\n");
        tv_thong_tin_ca_nhan.append("\t\t•\tEmail: " + canBo.getEmail() + "\n");
        tv_thong_tin_ca_nhan.append("\t\t•\tSố điện thoại: " + canBo.getSo_dien_thoai() + "\n");
        tv_thong_tin_ca_nhan.append("\t\t•\tĐịa chỉ: " + canBo.getDia_chi() + "\n");

        tv_gia_dinh.setText("Gia đình: \n\n");
        if (canBo.getGiaDinh().getConCanBoList().size() == 0) {
            tv_gia_dinh.append("\t\t<trống>\n");
        } else {
            for (int i = 0; i < canBo.getGiaDinh().getConCanBoList().size(); i++) {
                tv_gia_dinh.append("\t\t•\tHọ tên con: " + canBo.getGiaDinh().getConCanBoList().get(i).getHo_ten() + "\n");
                tv_gia_dinh.append("\t\t•\tNăm sinh: " + canBo.getGiaDinh().getConCanBoList().get(i).getNam_sinh() + "\n");
                tv_gia_dinh.append("\t\t•\tThành tích: " + canBo.getGiaDinh().getConCanBoList().get(i).getThanh_tich() + "\n");
                tv_gia_dinh.append("\n");
            }
        }

        tv_nghien_cuu_khoa_hoc.setText("Nghiên cứu khoa học: \n\n");
        if (canBo.getNghienCuuKhoaHoc().getNoi_dung() == null || canBo.getNghienCuuKhoaHoc().getNoi_dung().equals("empty")) {
            tv_nghien_cuu_khoa_hoc.append("\t\t<trống>\n");
        } else {
            tv_nghien_cuu_khoa_hoc.append(canBo.getNghienCuuKhoaHoc().getNoi_dung());
        }

        tv_thong_tin_giang_day.setText("Thông tin giảng dạy: \n\n");
        if (canBo.getThongTinGiangDay().getMonGiangDayList().size() == 0) {
            tv_thong_tin_giang_day.append("\t\t<trống>\n");
        } else {
            for (int i = 0; i < canBo.getThongTinGiangDay().getMonGiangDayList().size(); i++) {
                tv_thong_tin_giang_day.append("\t\t•\tMã môn học: " + canBo.getThongTinGiangDay().getMonGiangDayList().get(i).getMa_mon_hoc() + "\n");
                tv_thong_tin_giang_day.append("\t\t•\tTên môn: " + canBo.getThongTinGiangDay().getMonGiangDayList().get(i).getTen_mon() + "\n");
                tv_thong_tin_giang_day.append("\t\t•\tSố tín chỉ: " + canBo.getThongTinGiangDay().getMonGiangDayList().get(i).getSo_tin_chi() + "\n");
                tv_thong_tin_giang_day.append("\t\t•\tMã lớp: " + canBo.getThongTinGiangDay().getMonGiangDayList().get(i).getMa_lop() + "\n");
                tv_thong_tin_giang_day.append("\t\t•\tSố sinh viên: " + canBo.getThongTinGiangDay().getMonGiangDayList().get(i).getSo_sinh_vien() + "\n");
                tv_thong_tin_giang_day.append("\t\t•\tHọc kỳ: " + canBo.getThongTinGiangDay().getMonGiangDayList().get(i).getHoc_ky() + "\n");
                tv_thong_tin_giang_day.append("\t\t•\tNăm học: " + canBo.getThongTinGiangDay().getMonGiangDayList().get(i).getNam_hoc() + "\n");
                tv_thong_tin_giang_day.append("\n");
            }
        }

    }

    public void showCustomDialogExportOptions(final List<CanBo> canBoList) {
        final Dialog customDialog = new Dialog(activity);
        customDialog.setContentView(R.layout.custom_dialog_export_options);

        customDialog.findViewById(R.id.btn_export_danh_sach_can_bo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "Danh sach can bo:\n\n";
                for (int i = 0; i < canBoList.size(); i++) {
                    result += String.valueOf(i + 1) + "    " + canBoList.get(i).getMa_can_bo() + "    " + canBoList.get(i).getHo_ten() + "\n";
                }
                showExportContent(result);

                customDialog.dismiss();
            }
        });

        customDialog.findViewById(R.id.btn_export_danh_sach_can_bo_nam).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "Danh sach can bo nam:\n\n";
                int index = 1;
                for (int i = 0; i < canBoList.size(); i++) {
                    if (canBoList.get(i).getGioi_tinh().equals("nam")) {
                        result += String.valueOf(index++) + "    " + canBoList.get(i).getMa_can_bo() + "    " + canBoList.get(i).getHo_ten() + "\n";

                    }
                }
                showExportContent(result);

                customDialog.dismiss();
            }
        });

        customDialog.findViewById(R.id.btn_export_danh_sach_can_bo_nu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "Danh sach can bo nu:\n\n";
                int index = 1;
                for (int i = 0; i < canBoList.size(); i++) {
                    if (canBoList.get(i).getGioi_tinh().equals("nữ")) {
                        result += String.valueOf(index++) + "    " + canBoList.get(i).getMa_can_bo() + "    " + canBoList.get(i).getHo_ten() + "\n";
                    }
                }
                showExportContent(result);

                customDialog.dismiss();
            }
        });

        customDialog.findViewById(R.id.btn_export_cong_doan_phi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "Cong doan phi:\n\n";
                int index = 1;
                for (int i = 0; i < canBoList.size(); i++) {
                    if (canBoList.get(i).getCong_doan_vien()) {
                        result += String.valueOf(index++) + "    " + canBoList.get(i).getMa_can_bo() + "    " +
                                canBoList.get(i).getHo_ten() + "    " + String.format("%.0f", canBoList.get(i).congDoanPhi()) + "VND\n";
                    }
                }
                showExportContent(result);

                customDialog.dismiss();
            }
        });

        customDialog.findViewById(R.id.btn_export_dang_phi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "Dang phi:\n\n";
                int index = 1;
                for (int i = 0; i < canBoList.size(); i++) {
                    if (canBoList.get(i).getDang_vien()) {
                        result += String.valueOf(index++) + "    " + canBoList.get(i).getMa_can_bo() + "    " +
                                canBoList.get(i).getHo_ten() + "    " + String.format("%.0f", canBoList.get(i).dangPhi()) + "VND\n";
                    }
                }
                showExportContent(result);

                customDialog.dismiss();
            }
        });

        customDialog.setCanceledOnTouchOutside(false);
        customDialog.show();

        Window window = customDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT); //set width, height for dialog
    }

    public void showExportContent(String result) {
        final Dialog customDialog = new Dialog(activity);
        customDialog.setContentView(R.layout.export_content);

        TextView tv_export_content = customDialog.findViewById(R.id.tv_export_content);
        tv_export_content.setText(result);

        customDialog.setCanceledOnTouchOutside(false);
        customDialog.show();

        Window window = customDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT); //set width, height for dialog
    }

    public void showCustomDialogEdit(final boolean action_insert, final CanBo canBo, final TextView[] textViews) {
        final Dialog customDialog = new Dialog(activity);
        customDialog.setContentView(R.layout.custom_dialog_insert);

        final EditText edit_text_insert_ma_can_bo = customDialog.findViewById(R.id.edit_text_insert_ma_can_bo);
        final EditText edit_text_insert_ho_ten = customDialog.findViewById(R.id.edit_text_insert_ho_ten);
        final RadioGroup radio_group_gioi_tinh = customDialog.findViewById(R.id.radio_group_gioi_tinh);
        final EditText edit_text_insert_ngay_sinh = customDialog.findViewById(R.id.edit_text_insert_ngay_sinh);
        final EditText edit_text_insert_so_dien_thoai = customDialog.findViewById(R.id.edit_text_insert_so_dien_thoai);
        final EditText edit_text_insert_email = customDialog.findViewById(R.id.edit_text_insert_email);
        final EditText edit_text_insert_chuc_danh = customDialog.findViewById(R.id.edit_text_insert_chuc_danh);
        final EditText edit_text_insert_chuc_vu = customDialog.findViewById(R.id.edit_text_insert_chuc_vu);
        final EditText edit_text_insert_ma_so_thue = customDialog.findViewById(R.id.edit_text_insert_ma_so_thue);
        final EditText edit_text_insert_so_tai_khoan = customDialog.findViewById(R.id.edit_text_insert_so_tai_khoan);
        final EditText edit_text_insert_dia_chi = customDialog.findViewById(R.id.edit_text_insert_dia_chi);
        final RadioGroup radio_group_dang_vien = customDialog.findViewById(R.id.radio_group_dang_vien);
        final RadioGroup radio_group_doan_vien = customDialog.findViewById(R.id.radio_group_doan_vien);
        final RadioGroup radio_group_cong_doan_vien = customDialog.findViewById(R.id.radio_group_cong_doan_vien);
        final EditText edit_text_insert_thanh_tich = customDialog.findViewById(R.id.edit_text_insert_thanh_tich);

        if (!action_insert) {
            edit_text_insert_ma_can_bo.setText(canBo.getMa_can_bo());
            edit_text_insert_ho_ten.setText(canBo.getHo_ten());

            if (canBo.getGioi_tinh().equals("nam")) radio_group_gioi_tinh.check(R.id.radio_button_nam);
            else radio_group_gioi_tinh.check(R.id.radio_button_nu);

            edit_text_insert_ngay_sinh.setText(canBo.getNgay_sinh());
            edit_text_insert_so_dien_thoai.setText(canBo.getSo_dien_thoai());
            edit_text_insert_email.setText(canBo.getEmail());
            edit_text_insert_chuc_danh.setText(canBo.getChuc_danh());
            edit_text_insert_chuc_vu.setText(canBo.getChuc_vu());
            edit_text_insert_so_tai_khoan.setText(canBo.getSo_tai_khoan());
            edit_text_insert_ma_so_thue.setText(canBo.getMa_so_thue());
            edit_text_insert_dia_chi.setText(canBo.getDia_chi());

            if (canBo.getDang_vien()) radio_group_dang_vien.check(R.id.radio_button_co_dang_vien);
            else radio_group_dang_vien.check(R.id.radio_button_khong_dang_vien);

            if (canBo.getDoan_vien()) radio_group_doan_vien.check(R.id.radio_button_co_doan_vien);
            else radio_group_doan_vien.check(R.id.radio_button_khong_doan_vien);

            if (canBo.getCong_doan_vien()) radio_group_cong_doan_vien.check(R.id.radio_button_co_cong_doan_vien);
            else radio_group_cong_doan_vien.check(R.id.radio_button_khong_cong_doan_vien);

            edit_text_insert_thanh_tich.setText(canBo.getThanh_tich());
        }

        customDialog.findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma_can_bo = edit_text_insert_ma_can_bo.getText().toString();
                String ho_ten = edit_text_insert_ho_ten.getText().toString();

                String gioi_tinh;
                if (radio_group_gioi_tinh.getCheckedRadioButtonId() == R.id.radio_button_nam) {
                    gioi_tinh = "nam";
                } else gioi_tinh = "nữ";

                String ngay_sinh = edit_text_insert_ngay_sinh.getText().toString();
                String so_dien_thoai = edit_text_insert_so_dien_thoai.getText().toString();
                String email = edit_text_insert_email.getText().toString();
                String chuc_danh = edit_text_insert_chuc_danh.getText().toString();
                String chuc_vu = edit_text_insert_chuc_vu.getText().toString();
                String so_tai_khoan = edit_text_insert_so_tai_khoan.getText().toString();
                String ma_so_thue = edit_text_insert_ma_so_thue.getText().toString();
                String dia_chi = edit_text_insert_dia_chi.getText().toString();

                int check_dang_vien = 0;
                boolean dang_vien = false;
                if (radio_group_dang_vien.getCheckedRadioButtonId() == R.id.radio_button_co_dang_vien) {
                    check_dang_vien = 1;
                    dang_vien = true;
                }

                int check_doan_vien = 0;
                boolean doan_vien = false;
                if (radio_group_doan_vien.getCheckedRadioButtonId() == R.id.radio_button_co_doan_vien) {
                    check_doan_vien = 1;
                    doan_vien = true;
                }

                int check_cong_doan_vien = 0;
                boolean cong_doan_vien = false;
                if (radio_group_cong_doan_vien.getCheckedRadioButtonId() == R.id.radio_button_co_cong_doan_vien) {
                    check_cong_doan_vien = 1;
                    cong_doan_vien = true;
                }

                String thanh_tich = edit_text_insert_thanh_tich.getText().toString();

                if ( ma_can_bo.isEmpty() || ho_ten.isEmpty() || gioi_tinh.isEmpty() || ngay_sinh.isEmpty() ||
                        so_dien_thoai.isEmpty() || email.isEmpty() || chuc_danh.isEmpty() || chuc_vu.isEmpty() ||
                        so_tai_khoan.isEmpty() || ma_so_thue.isEmpty() || dia_chi.isEmpty() || thanh_tich.isEmpty()
                ) {
                    Toast.makeText(context, "Vui lòng điền hết!", Toast.LENGTH_SHORT).show();

                } else {
                    db.beginTransaction();

                    try {
                        if (action_insert) {
                            //insert data
                            db.execSQL("insert into ThongTinCaNhan(ma_can_bo, ho_ten, ngay_sinh, gioi_tinh, chuc_danh, chuc_vu, " +
                                    "hs_luong, phu_cap_chuc_vu, phu_cap_giang_day, ma_so_thue, so_tai_khoan, dia_chi, so_dien_thoai, " +
                                    "email, dang_vien, doan_vien, cong_doan_vien, thanh_tich) " +
                                    "values( '" + ma_can_bo + "', '" +
                                    ho_ten + "', '" +
                                    ngay_sinh + "', '" +
                                    gioi_tinh + "', '" +
                                    chuc_danh + "', '" +
                                    chuc_vu + "', '1.3', '2', '1.5', '" +
                                    ma_so_thue + "', '" +
                                    so_tai_khoan + "', '" +
                                    dia_chi + "', '" +
                                    so_dien_thoai + "', '" +
                                    email + "', '" +
                                    check_dang_vien + "', '" +
                                    check_doan_vien + "', '" +
                                    check_cong_doan_vien + "', '" +
                                    thanh_tich +
                                    "')");

                        } else {
                            //update data
                            db.execSQL("update ThongTinCaNhan set " +
                                    "ma_can_bo = '" + ma_can_bo + "', " +
                                    "ho_ten = '" + ho_ten + "', " +
                                    "ngay_sinh = '" + ngay_sinh + "', " +
                                    "gioi_tinh = '" + gioi_tinh + "', " +
                                    "chuc_danh = '" + chuc_danh + "', " +
                                    "chuc_vu = '" + chuc_vu + "', " +
                                    "ma_so_thue = '" + ma_so_thue + "', " +
                                    "so_tai_khoan = '" + so_tai_khoan + "', " +
                                    "dia_chi = '" + dia_chi + "', " +
                                    "so_dien_thoai = '" + so_dien_thoai + "', " +
                                    "email = '" + email + "', " +
                                    "dang_vien = '" + check_dang_vien + "', " +
                                    "doan_vien = '" + check_doan_vien + "', " +
                                    "cong_doan_vien = '" + check_cong_doan_vien + "', " +
                                    "thanh_tich = '" + thanh_tich + "' " +
                                    "where ma_can_bo = '" + canBo.getMa_can_bo() + "'"
                                    );

                            canBo.setHo_ten(ho_ten);
                            canBo.setMa_can_bo(ma_can_bo);
                            canBo.setNgay_sinh(ngay_sinh);
                            canBo.setGioi_tinh(gioi_tinh);
                            canBo.setChuc_danh(chuc_danh);
                            canBo.setChuc_vu(chuc_vu);
                            canBo.setMa_so_thue(ma_so_thue);
                            canBo.setSo_tai_khoan(so_tai_khoan);
                            canBo.setDia_chi(dia_chi);
                            canBo.setSo_dien_thoai(so_dien_thoai);
                            canBo.setEmail(email);
                            canBo.setDang_vien(dang_vien);
                            canBo.setDoan_vien(doan_vien);
                            canBo.setCong_doan_vien(cong_doan_vien);
                            canBo.setThanh_tich(thanh_tich);
                            //Log.v("TAG", canBo.getHo_ten());
                            displayInfo(canBo, textViews[0], textViews[1], textViews[2], textViews[3]);
                        }
                        db.setTransactionSuccessful();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        db.endTransaction();
                    }
                    customDialog.dismiss();
                }
            }
        });

        customDialog.findViewById(R.id.btn_close_insert_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });

        customDialog.setCanceledOnTouchOutside(false);
        customDialog.show();

        Window window = customDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT); //set width, height for dialog
    }

    public void showCustomDialogEditOptions(final CanBo canBo, final TextView[] textViews) {
        final Dialog customDialog = new Dialog(activity);
        customDialog.setContentView(R.layout.custom_dialog_edit_options);

        customDialog.findViewById(R.id.btn_edit_thong_tin_ca_nhan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.v("TAG", "sua ttcn");
                customDialog.dismiss();
                showCustomDialogEdit(false, canBo, textViews);
            }
        });

        customDialog.findViewById(R.id.btn_edit_gia_dinh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.v("TAG", "sua gd");
                customDialog.dismiss();
                showCustomDialogEditGiaDinh(canBo, textViews);
            }
        });

        customDialog.findViewById(R.id.btn_edit_thong_tin_nghien_cuu_khoa_hoc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.v("TAG", "sua nckh");
                customDialog.dismiss();
                showCustomDialogEditNghienCuuKhoaHoc(canBo, textViews);
            }
        });

        customDialog.findViewById(R.id.btn_edit_thong_tin_giang_day).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
                showCustomDialogEditThongTinGiangDay(canBo, textViews);
            }
        });

        customDialog.setCanceledOnTouchOutside(true);
        customDialog.show();

        Window window = customDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT); //set width, height for dialog
    }

    public void showCustomDialogEditNghienCuuKhoaHoc(final CanBo canBo, final TextView[] textViews) {
        final Dialog customDialog = new Dialog(activity);
        customDialog.setContentView(R.layout.custom_dialog_edit_nghien_cuu_khoa_hoc);

        final EditText edit_text_nckh = customDialog.findViewById(R.id.edit_text_noi_dung_nghien_cuu_khoa_hoc);
        edit_text_nckh.setText(canBo.getNghienCuuKhoaHoc().getNoi_dung());

        customDialog.findViewById(R.id.btn_ok_custom_dialog_edit_nckh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_text_nckh.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Vui lòng viết gì đó!", Toast.LENGTH_SHORT).show();
                } else {
                    db.beginTransaction();

                    try {
                        // Update NghienCuuKhoaHoc
                        db.execSQL("update NghienCuuKhoaHoc " +
                                "set noi_dung = '" + edit_text_nckh.getText().toString() + "' " +
                                "where ma_can_bo = '" + canBo.getMa_can_bo() +
                                "'");
                        canBo.getNghienCuuKhoaHoc().setNoi_dung(edit_text_nckh.getText().toString());

                        db.setTransactionSuccessful();
                        displayInfo(canBo, textViews[0], textViews[1], textViews[2], textViews[3]);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        db.endTransaction();
                    }

                    customDialog.dismiss();
                }
            }
        });

        customDialog.findViewById(R.id.btn_close_custom_dialog_edit_nckh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });

        customDialog.setCanceledOnTouchOutside(true);
        customDialog.show();

        Window window = customDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT); //set width, height for dialog
    }

    public void showCustomDialogEditThongTinGiangDay(final CanBo canBo, final TextView textViews[]) {
        final Dialog customDialog = new Dialog(activity);
        customDialog.setContentView(R.layout.custom_dialog_edit_list_item);

        final ListView listView = customDialog.findViewById(R.id.list_view_info);
        final ItemInfoAdapter adapter = new ItemInfoAdapter(canBo, "ThongTinGiangDay");
        listView.setAdapter(adapter);

        customDialog.findViewById(R.id.btn_add_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialogEditMonGiangDay(canBo, -1, true, listView, adapter, textViews);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showCustomDialogEditMonGiangDay(canBo, position, false, listView, adapter, textViews);
            }
        });

        customDialog.setCanceledOnTouchOutside(true);
        customDialog.show();

        Window window = customDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT); //set width, height for dialog
    }

    public void showCustomDialogEditGiaDinh(final CanBo canBo, final TextView textViews[]) {
        final Dialog customDialog = new Dialog(activity);
        customDialog.setContentView(R.layout.custom_dialog_edit_list_item);

        final ListView listView = customDialog.findViewById(R.id.list_view_info);
        final ItemInfoAdapter adapter = new ItemInfoAdapter(canBo, "GiaDinh");
        listView.setAdapter(adapter);

        customDialog.findViewById(R.id.btn_add_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialogEditConCanBo(canBo, -1, true, listView, adapter, textViews);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showCustomDialogEditConCanBo(canBo, position, false, listView, adapter, textViews);
            }
        });

        customDialog.setCanceledOnTouchOutside(true);
        customDialog.show();

        Window window = customDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT); //set width, height for dialog
    }

    public void showCustomDialogEditMonGiangDay(final CanBo canBo, final int position,
                                             final boolean action_insert, final ListView listView,
                                             final ItemInfoAdapter adapter, final TextView textViews[]) {
        final Dialog customDialog = new Dialog(activity);
        customDialog.setContentView(R.layout.custom_dialog_edit_mon_giang_day);

        final EditText edt_ma_mon_hoc = customDialog.findViewById(R.id.edit_text_ma_mon_hoc);
        final EditText edt_ten_mon = customDialog.findViewById(R.id.edit_text_ten_mon);
        final EditText edt_so_tin_chi = customDialog.findViewById(R.id.edit_text_so_tin_chi);
        final EditText edt_ma_lop = customDialog.findViewById(R.id.edit_text_ma_lop);
        final EditText edt_so_sinh_vien = customDialog.findViewById(R.id.edit_text_so_sinh_vien);
        final EditText edt_hoc_ky = customDialog.findViewById(R.id.edit_text_hoc_ky);
        final EditText edt_nam_hoc = customDialog.findViewById(R.id.edit_text_nam_hoc);

        if (!action_insert) {
            edt_ma_mon_hoc.setText(canBo.getThongTinGiangDay().getMonGiangDayList().get(position).getMa_mon_hoc());
            edt_ten_mon.setText(canBo.getThongTinGiangDay().getMonGiangDayList().get(position).getTen_mon());
            edt_so_tin_chi.setText(canBo.getThongTinGiangDay().getMonGiangDayList().get(position).getSo_tin_chi());
            edt_ma_lop.setText(canBo.getThongTinGiangDay().getMonGiangDayList().get(position).getMa_lop());
            edt_so_sinh_vien.setText(canBo.getThongTinGiangDay().getMonGiangDayList().get(position).getSo_sinh_vien());
            edt_hoc_ky.setText(canBo.getThongTinGiangDay().getMonGiangDayList().get(position).getHoc_ky());
            edt_nam_hoc.setText(canBo.getThongTinGiangDay().getMonGiangDayList().get(position).getNam_hoc());
        }

        customDialog.findViewById(R.id.btn_ok_dialog_edit_mon_giang_day).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma_mon_hoc = edt_ma_mon_hoc.getText().toString();
                String ten_mon = edt_ten_mon.getText().toString();
                String so_tin_chi = edt_so_tin_chi.getText().toString();
                String ma_lop = edt_ma_lop.getText().toString();
                String so_sinh_vien = edt_so_sinh_vien.getText().toString();
                String hoc_ky = edt_hoc_ky.getText().toString();
                String nam_hoc = edt_nam_hoc.getText().toString();

                if (ma_mon_hoc.isEmpty() || ten_mon.isEmpty() || so_tin_chi.isEmpty() ||
                        ma_lop.isEmpty() || so_sinh_vien.isEmpty() || hoc_ky.isEmpty() || nam_hoc.isEmpty()
                ) {
                    Toast.makeText(context, "Vui lòng điền hết!", Toast.LENGTH_SHORT).show();
                } else {
                    db.beginTransaction();

                    try {
                        if (action_insert) {
                            // Insert MonGiangDay
                            db.execSQL("insert into ThongTinGiangDay(ma_can_bo, ma_mon_hoc, ten_mon, so_tin_chi, " +
                                    "ma_lop, so_sinh_vien, hoc_ky, nam_hoc) " +
                                    "values( '" + canBo.getMa_can_bo() + "', '" +
                                    ma_mon_hoc + "', '" +
                                    ten_mon + "', '" +
                                    so_tin_chi + "', '" +
                                    ma_lop + "', '" +
                                    so_sinh_vien + "', '" +
                                    hoc_ky + "', '" +
                                    nam_hoc +
                                    "')");
                            canBo.getThongTinGiangDay().getMonGiangDayList().add(new MonGiangDay(ma_mon_hoc, ten_mon, so_tin_chi, ma_lop, so_sinh_vien, hoc_ky, nam_hoc));
                        } else {
                            // Update ConCanBo
                            String old_ma_lop = canBo.getThongTinGiangDay().getMonGiangDayList().get(position).getMa_lop();
                            db.execSQL("update ThongTinGiangDay set " +
                                    "ma_mon_hoc = '" + ma_mon_hoc + "', " +
                                    "ten_mon = '" + ten_mon + "', " +
                                    "so_tin_chi = '" + so_tin_chi + "', " +
                                    "ma_lop = '" + ma_lop + "', " +
                                    "so_sinh_vien = '" + so_sinh_vien + "', " +
                                    "hoc_ky = '" + hoc_ky + "', " +
                                    "nam_hoc = '" + nam_hoc + "' " +
                                    "where ma_can_bo = '" + canBo.getMa_can_bo() + "' and " +
                                    "ma_lop = '" + old_ma_lop + "';"
                            );
                            canBo.getThongTinGiangDay().getMonGiangDayList().get(position).setMa_mon_hoc(ma_mon_hoc);
                            canBo.getThongTinGiangDay().getMonGiangDayList().get(position).setTen_mon(ten_mon);
                            canBo.getThongTinGiangDay().getMonGiangDayList().get(position).setSo_tin_chi(so_tin_chi);
                            canBo.getThongTinGiangDay().getMonGiangDayList().get(position).setMa_lop(ma_lop);
                            canBo.getThongTinGiangDay().getMonGiangDayList().get(position).setSo_sinh_vien(so_sinh_vien);
                            canBo.getThongTinGiangDay().getMonGiangDayList().get(position).setHoc_ky(hoc_ky);
                            canBo.getThongTinGiangDay().getMonGiangDayList().get(position).setNam_hoc(nam_hoc);

                        }
                        db.setTransactionSuccessful();
                        adapter.setCanBo(canBo);
                        listView.setAdapter(adapter);
                        displayInfo(canBo, textViews[0], textViews[1], textViews[2], textViews[3]);

                        customDialog.dismiss();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        db.endTransaction();
                    }
                }

            }
        });

        customDialog.findViewById(R.id.btn_delete_mon_giang_day).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!action_insert) {
                    try {
                        // Delete MonGiangDay
                        String ten_mon = canBo.getThongTinGiangDay().getMonGiangDayList().get(position).getTen_mon();
                        String ma_lop = canBo.getThongTinGiangDay().getMonGiangDayList().get(position).getMa_lop();

                        db.beginTransaction();
                        db.execSQL("delete from ThongTinGiangDay "+
                                "where ma_can_bo = '" + canBo.getMa_can_bo() + "' and " +
                                "ten_mon = '" + ten_mon + "' and " +
                                "ma_lop = '" + ma_lop + "';"
                        );
                        canBo.getThongTinGiangDay().getMonGiangDayList().remove(position);

                        db.setTransactionSuccessful();
                        adapter.setCanBo(canBo);
                        listView.setAdapter(adapter);
                        displayInfo(canBo, textViews[0], textViews[1], textViews[2], textViews[3]);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        db.endTransaction();
                    }
                }
                customDialog.dismiss();
            }
        });

        customDialog.findViewById(R.id.btn_close_dialog_edit_mon_giang_day).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });

        customDialog.setCanceledOnTouchOutside(false);
        customDialog.show();

        Window window = customDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT); //set width, height for dialog
    }

    public void showCustomDialogEditConCanBo(final CanBo canBo, final int position,
                                             final boolean action_insert, final ListView listView,
                                             final ItemInfoAdapter adapter, final TextView textViews[]) {
        final Dialog customDialog = new Dialog(activity);
        customDialog.setContentView(R.layout.custom_dialog_edit_con_can_bo);

        final EditText edt_ho_ten = customDialog.findViewById(R.id.edit_text_ho_ten_con_can_bo);
        final EditText edt_nam_sinh = customDialog.findViewById(R.id.edit_text_nam_sinh_con_can_bo);
        final EditText edt_thanh_tich = customDialog.findViewById(R.id.edit_text_thanh_tich_con_can_bo);

        if (!action_insert) {
            edt_ho_ten.setText(canBo.getGiaDinh().getConCanBoList().get(position).getHo_ten());
            edt_nam_sinh.setText(canBo.getGiaDinh().getConCanBoList().get(position).getNam_sinh());
            edt_thanh_tich.setText(canBo.getGiaDinh().getConCanBoList().get(position).getThanh_tich());
        }

        customDialog.findViewById(R.id.btn_ok_dialog_edit_con_can_bo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ho_ten = edt_ho_ten.getText().toString();
                String nam_sinh = edt_nam_sinh.getText().toString();
                String thanh_tich = edt_thanh_tich.getText().toString();

                if (ho_ten.isEmpty() || nam_sinh.isEmpty() || thanh_tich.isEmpty()) {
                    Toast.makeText(context, "Vui lòng điền hết!", Toast.LENGTH_SHORT).show();
                } else {
                    db.beginTransaction();

                    try {
                        if (action_insert) {
                            // Insert ConCanBo
                            db.execSQL("insert into GiaDinh(ma_can_bo, ho_ten_con, nam_sinh, thanh_tich) " +
                                    "values( '" + canBo.getMa_can_bo() + "', '" +
                                    ho_ten + "', '" +
                                    nam_sinh + "', '" +
                                    thanh_tich +

                                    "')");
                            canBo.getGiaDinh().getConCanBoList().add(new ConCanBo(ho_ten, nam_sinh, thanh_tich));
                        } else {
                            // Update ConCanBo
                            String old_ho_ten = canBo.getGiaDinh().getConCanBoList().get(position).getHo_ten();
                            db.execSQL("update GiaDinh set " +
                                    "ho_ten_con = '" + ho_ten + "', " +
                                    "nam_sinh = '" + nam_sinh + "', " +
                                    "thanh_tich = '" + thanh_tich + "' " +
                                    "where ma_can_bo = '" + canBo.getMa_can_bo() + "' and " +
                                    "ho_ten_con = '" + old_ho_ten + "';"
                            );
                            canBo.getGiaDinh().getConCanBoList().get(position).setHo_ten(ho_ten);
                            canBo.getGiaDinh().getConCanBoList().get(position).setNam_sinh(nam_sinh);
                            canBo.getGiaDinh().getConCanBoList().get(position).setThanh_tich(thanh_tich);
                        }
                        db.setTransactionSuccessful();
                        adapter.setCanBo(canBo);
                        listView.setAdapter(adapter);
                        displayInfo(canBo, textViews[0], textViews[1], textViews[2], textViews[3]);

                        customDialog.dismiss();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        db.endTransaction();
                    }
                }

            }
        });

        customDialog.findViewById(R.id.btn_delete_con_can_bo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!action_insert) {
                    try {
                        // Delete ConCanBo
                        String old_ho_ten = canBo.getGiaDinh().getConCanBoList().get(position).getHo_ten();

                        db.beginTransaction();
                        db.execSQL("delete from GiaDinh "+
                                "where ma_can_bo = '" + canBo.getMa_can_bo() + "' and " +
                                "ho_ten_con = '" + old_ho_ten + "';"
                        );
                        canBo.getGiaDinh().getConCanBoList().remove(position);

                        db.setTransactionSuccessful();
                        adapter.setCanBo(canBo);
                        listView.setAdapter(adapter);
                        displayInfo(canBo, textViews[0], textViews[1], textViews[2], textViews[3]);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        db.endTransaction();
                    }
                }

                customDialog.dismiss();
            }
        });

        customDialog.findViewById(R.id.btn_close_dialog_edit_con_can_bo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });

        customDialog.setCanceledOnTouchOutside(false);
        customDialog.show();

        Window window = customDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT); //set width, height for dialog
    }

}
