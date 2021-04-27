package com.example.quanlycanbo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.quanlycanbo.models.CanBo;
import com.example.quanlycanbo.models.ConCanBo;
import com.example.quanlycanbo.models.MonGiangDay;

public class PersonalInfor extends AppCompatActivity {
    SQLiteDatabase db;
    TextView tv_thong_tin_ca_nhan, tv_gia_dinh, tv_nghien_cuu_khoa_hoc, tv_thong_tin_giang_day;
    CanBo canBo;
    ImageButton btn_back, btn_edit;
    CustomDialog customDialog;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_infor);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Quản lý Cán bộ");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_bar_color));

        openDB(); // Open database
        customDialog = new CustomDialog(PersonalInfor.this, getApplicationContext(), db);
        intent = getIntent();
        canBo = (CanBo) intent.getSerializableExtra("data"); // Get data from mainActivity

        btn_back = findViewById(R.id.btn_back);
        btn_edit = findViewById(R.id.btn_edit);
        tv_thong_tin_ca_nhan = findViewById(R.id.tv_thong_tin_ca_nhan);
        tv_gia_dinh = findViewById(R.id.tv_gia_dinh);
        tv_nghien_cuu_khoa_hoc = findViewById(R.id.tv_nghien_cuu_khoa_hoc);
        tv_thong_tin_giang_day = findViewById(R.id.tv_thong_tin_giang_day);

        updateGiaDinh();
        updateNghienCuuKhoaHoc();
        updateThongTinGiangDay();

        customDialog.displayInfo(canBo, tv_thong_tin_ca_nhan, tv_gia_dinh, tv_nghien_cuu_khoa_hoc, tv_thong_tin_giang_day);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textViews[] = {tv_thong_tin_ca_nhan, tv_gia_dinh, tv_nghien_cuu_khoa_hoc, tv_thong_tin_giang_day};
                customDialog.showCustomDialogEditOptions(canBo, textViews);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalInfor.this, MainActivity.class));
            }
        });

    }

    private void openDB() {
        try {
            String path = getBaseContext().getExternalFilesDir("mydb").getPath() + "/db"; //access SD Card
            //String path = getFilesDir().getAbsolutePath() + "/mydb"; //access interal storage
            db = SQLiteDatabase.openDatabase(path, null, 0);
            //Log.v("TAG", path);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateGiaDinh() {
        String ma_can_bo = canBo.getMa_can_bo();

        String sql = "select * from GiaDinh where ma_can_bo='" + ma_can_bo + "'";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();

        if (cs.getCount() > 0) {
            do {
                String ho_ten = cs.getString(cs.getColumnIndex("ho_ten_con"));
                String nam_sinh = cs.getString(cs.getColumnIndex("nam_sinh"));
                String thanh_tich = cs.getString(cs.getColumnIndex("thanh_tich"));

                canBo.getGiaDinh().getConCanBoList().add( new ConCanBo(ho_ten, nam_sinh, thanh_tich) );
            }
            while (cs.moveToNext());
        }
    }

    public void updateNghienCuuKhoaHoc() {
        String ma_can_bo = canBo.getMa_can_bo();

        String sql = "select * from NghienCuuKhoaHoc where ma_can_bo='" + ma_can_bo + "'";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();

        if (cs.getCount() > 0) {
            String noi_dung = cs.getString(cs.getColumnIndex("noi_dung"));
            canBo.getNghienCuuKhoaHoc().setNoi_dung(noi_dung);
        } else {
            db.beginTransaction();
            try {
                // Insert NghienCuuKhoaHoc
                db.execSQL("insert into NghienCuuKhoaHoc(ma_can_bo, noi_dung) " +
                        "values('"+ canBo.getMa_can_bo() +"', " +
                        "'empty')");
                db.setTransactionSuccessful();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                db.endTransaction();
            }
        }
    }

    public void updateThongTinGiangDay() {
        String ma_can_bo = canBo.getMa_can_bo();

        String sql = "select * from ThongTinGiangDay where ma_can_bo='" + ma_can_bo + "'";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();

        if (cs.getCount() > 0) {
            do {
                String ma_mon_hoc  = cs.getString(cs.getColumnIndex("ma_mon_hoc"));
                String ten_mon = cs.getString(cs.getColumnIndex("ten_mon"));
                String so_tin_chi = cs.getString(cs.getColumnIndex("so_tin_chi"));
                String ma_lop = cs.getString(cs.getColumnIndex("ma_lop"));
                String so_sinh_vien = cs.getString(cs.getColumnIndex("so_sinh_vien"));
                String hoc_ky = cs.getString(cs.getColumnIndex("hoc_ky"));
                String nam_hoc = cs.getString(cs.getColumnIndex("nam_hoc"));

                canBo.getThongTinGiangDay().getMonGiangDayList().add( new MonGiangDay(ma_mon_hoc, ten_mon, so_tin_chi,
                        ma_lop, so_sinh_vien, hoc_ky, nam_hoc) );
            }
            while (cs.moveToNext());
        }
    }

}
