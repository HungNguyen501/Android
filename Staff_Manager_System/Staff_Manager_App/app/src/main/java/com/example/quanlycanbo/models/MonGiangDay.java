package com.example.quanlycanbo.models;

public class MonGiangDay {
    private String ma_mon_hoc;
    private String ten_mon;
    private String so_tin_chi;
    private String ma_lop;
    private String so_sinh_vien;
    private String hoc_ky;
    private String nam_hoc;

    public MonGiangDay(String ma_mon_hoc, String ten_mon, String so_tin_chi, String ma_lop, String so_sinh_vien,
                       String hoc_ky, String nam_hoc)
    {
        this.ma_mon_hoc = ma_mon_hoc;
        this.ten_mon = ten_mon;
        this.so_tin_chi = so_tin_chi;
        this.ma_lop = ma_lop;
        this.so_sinh_vien = so_sinh_vien;
        this.hoc_ky = hoc_ky;
        this.nam_hoc = nam_hoc;
    }

    public String getMa_mon_hoc() {
        return ma_mon_hoc;
    }

    public void setMa_mon_hoc(String ma_mon_hoc) {
        this.ma_mon_hoc = ma_mon_hoc;
    }

    public String getTen_mon() {
        return ten_mon;
    }

    public void setTen_mon(String ten_mon) {
        this.ten_mon = ten_mon;
    }

    public String getSo_tin_chi() {
        return so_tin_chi;
    }

    public void setSo_tin_chi(String so_tin_chi) {
        this.so_tin_chi = so_tin_chi;
    }

    public String getMa_lop() {
        return ma_lop;
    }

    public void setMa_lop(String ma_lop) {
        this.ma_lop = ma_lop;
    }

    public String getSo_sinh_vien() {
        return so_sinh_vien;
    }

    public void setSo_sinh_vien(String so_sinh_vien) {
        this.so_sinh_vien = so_sinh_vien;
    }

    public String getHoc_ky() {
        return hoc_ky;
    }

    public void setHoc_ky(String hoc_ky) {
        this.hoc_ky = hoc_ky;
    }

    public String getNam_hoc() {
        return nam_hoc;
    }

    public void setNam_hoc(String nam_hoc) {
        this.nam_hoc = nam_hoc;
    }
}
