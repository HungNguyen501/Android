package com.example.quanlycanbo.models;

public class LopThi {
    private String ma_lop;
    private String ma_can_bo;
    private String ma_mon_hoc;
    private int so_tin_chi;
    private String ngay_nop;
    private String ngay_thu;

    public LopThi(String ma_lop, String ma_can_bo, int so_tin_chi, String ngay_nop, String ngay_thu) {
        this.ma_lop = ma_lop;
        this.ma_can_bo = ma_can_bo;
        this.so_tin_chi = so_tin_chi;
        this.ngay_nop = ngay_nop;
        this.ngay_thu = ngay_thu;
    }

    public String getMa_lop() {
        return ma_lop;
    }

    public void setMa_lop(String ma_lop) {
        this.ma_lop = ma_lop;
    }

    public String getMa_can_bo() {
        return ma_can_bo;
    }

    public void setMa_can_bo(String ma_can_bo) {
        this.ma_can_bo = ma_can_bo;
    }

    public String getMa_mon_hoc() {
        return ma_mon_hoc;
    }

    public void setMa_mon_hoc(String ma_mon_hoc) {
        this.ma_mon_hoc = ma_mon_hoc;
    }

    public int getSo_tin_chi() {
        return so_tin_chi;
    }

    public void setSo_tin_chi(int so_tin_chi) {
        this.so_tin_chi = so_tin_chi;
    }

    public String getNgay_nop() {
        return ngay_nop;
    }

    public void setNgay_nop(String ngay_nop) {
        this.ngay_nop = ngay_nop;
    }

    public String getNgay_thu() {
        return ngay_thu;
    }

    public void setNgay_thu(String ngay_thu) {
        this.ngay_thu = ngay_thu;
    }
}
