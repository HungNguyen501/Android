package com.example.quanlycanbo.models;

public class ConCanBo {
    private String ho_ten;
    private String nam_sinh;
    private String thanh_tich;

    public ConCanBo(String ho_ten, String nam_sinh, String thanh_tich) {
        this.ho_ten = ho_ten;
        this.nam_sinh = nam_sinh;
        this.thanh_tich = thanh_tich;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public void setNam_sinh(String nam_sinh) {
        this.nam_sinh = nam_sinh;
    }

    public void setThanh_tich(String thanh_tich) {
        this.thanh_tich = thanh_tich;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public String getNam_sinh() {
        return nam_sinh;
    }

    public String getThanh_tich() {
        return thanh_tich;
    }
}
