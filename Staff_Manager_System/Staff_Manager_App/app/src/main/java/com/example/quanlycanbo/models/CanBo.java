package com.example.quanlycanbo.models;

import java.io.Serializable;

public class CanBo implements Serializable {
    private String ma_can_bo;
    private String ho_ten;
    private String ngay_sinh;
    private String gioi_tinh;
    private String chuc_danh;
    private String chuc_vu;
    private double hs_luong;
    private double phu_cap_chuc_vu;
    private double phu_cap_giang_day;
    private String ma_so_thue;
    private String so_tai_khoan;
    private String dia_chi;
    private String so_dien_thoai;
    private String email;
    private boolean dang_vien;
    private boolean doan_vien;
    private boolean cong_doan_vien;
    private String thanh_tich;
    private int id;
    private  GiaDinh giaDinh;
    private NghienCuuKhoaHoc nghienCuuKhoaHoc;
    private ThongTinGiangDay thongTinGiangDay;

    public CanBo() {
        // Constructor
    }

    public CanBo(String ma_can_bo, String ho_ten, String ngay_sinh, String gioi_tinh, String chuc_danh, String chuc_vu,
                 double hs_luong, double phu_cap_chuc_vu, double phu_cap_giang_day, String ma_so_thue, String so_tai_khoan,
                 String dia_chi, String so_dien_thoai, String email, boolean dang_vien, boolean doan_vien,
                 boolean cong_doan_vien, String thanh_tich) {

        this.ma_can_bo = ma_can_bo;
        this.ho_ten = ho_ten;
        this.ngay_sinh = ngay_sinh;
        this.gioi_tinh = gioi_tinh;
        this.chuc_danh = chuc_danh;
        this.chuc_vu = chuc_vu;
        this.hs_luong = hs_luong;
        this.phu_cap_chuc_vu = phu_cap_chuc_vu;
        this.phu_cap_giang_day = phu_cap_giang_day;
        this.ma_so_thue = ma_so_thue;
        this.so_tai_khoan = so_tai_khoan;
        this.dia_chi = dia_chi;
        this.so_dien_thoai = so_dien_thoai;
        this.email = email;
        this.dang_vien = dang_vien;
        this.doan_vien = doan_vien;
        this.cong_doan_vien = cong_doan_vien;
        this.thanh_tich = thanh_tich;

        this.giaDinh = new GiaDinh();
        this.nghienCuuKhoaHoc = new NghienCuuKhoaHoc();
        this.thongTinGiangDay = new ThongTinGiangDay();
    }

    public double congDoanPhi() {
        return (hs_luong + phu_cap_chuc_vu) * 1000000 * 0.01;
    }

    public double dangPhi() {
        return (hs_luong + phu_cap_chuc_vu + phu_cap_giang_day) * 1000000 * 0.01;
    }

    public GiaDinh getGiaDinh() {
        return giaDinh;
    }

    public void setGiaDinh(GiaDinh giaDinh) {
        this.giaDinh = giaDinh;
    }

    public NghienCuuKhoaHoc getNghienCuuKhoaHoc() {
        return nghienCuuKhoaHoc;
    }

    public void setNghienCuuKhoaHoc(NghienCuuKhoaHoc nghienCuuKhoaHoc) {
        this.nghienCuuKhoaHoc = nghienCuuKhoaHoc;
    }

    public ThongTinGiangDay getThongTinGiangDay() {
        return thongTinGiangDay;
    }

    public void setThongTinGiangDay(ThongTinGiangDay thongTinGiangDay) {
        this.thongTinGiangDay = thongTinGiangDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMa_can_bo() {
        return ma_can_bo;
    }

    public void setMa_can_bo(String ma_can_bo) {
        this.ma_can_bo = ma_can_bo;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public String getNgay_sinh() {
        return ngay_sinh;
    }

    public void setNgay_sinh(String ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }

    public String getGioi_tinh() {
        return gioi_tinh;
    }

    public void setGioi_tinh(String gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }

    public String getChuc_danh() {
        return chuc_danh;
    }

    public void setChuc_danh(String chuc_danh) {
        this.chuc_danh = chuc_danh;
    }

    public String getChuc_vu() {
        return chuc_vu;
    }

    public void setChuc_vu(String chuc_vu) {
        this.chuc_vu = chuc_vu;
    }

    public double getHs_luong() {
        return hs_luong;
    }

    public void setHs_luong(double hs_luong) {
        this.hs_luong = hs_luong;
    }

    public double getPhu_cap_chuc_vu() {
        return phu_cap_chuc_vu;
    }

    public void setPhu_cap_chuc_vu(double phu_cap_chuc_vu) {
        this.phu_cap_chuc_vu = phu_cap_chuc_vu;
    }

    public double getPhu_cap_giang_day() {
        return phu_cap_giang_day;
    }

    public void setPhu_cap_giang_day(double phu_cap_giang_day) {
        this.phu_cap_giang_day = phu_cap_giang_day;
    }

    public String getMa_so_thue() {
        return ma_so_thue;
    }

    public void setMa_so_thue(String ma_so_thue) {
        this.ma_so_thue = ma_so_thue;
    }

    public String getSo_tai_khoan() {
        return so_tai_khoan;
    }

    public void setSo_tai_khoan(String so_tai_khoan) {
        this.so_tai_khoan = so_tai_khoan;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getDang_vien() {
        return dang_vien;
    }

    public void setDang_vien(boolean dang_vien) {
        this.dang_vien = dang_vien;
    }

    public boolean getDoan_vien() {
        return doan_vien;
    }

    public void setDoan_vien(boolean doan_vien) {
        this.doan_vien = doan_vien;
    }

    public boolean getCong_doan_vien() {
        return cong_doan_vien;
    }

    public void setCong_doan_vien(boolean cong_doan_vien) {
        this.cong_doan_vien = cong_doan_vien;
    }

    public String getThanh_tich() {
        return thanh_tich;
    }

    public void setThanh_tich(String thanh_tich) {
        this.thanh_tich = thanh_tich;
    }

}
