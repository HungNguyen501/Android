package com.example.quanlycanbo.models;

import java.io.Serializable;

public class NghienCuuKhoaHoc implements Serializable {
    private String noi_dung;

    public NghienCuuKhoaHoc() {
        // Default Constructor
    }

    public NghienCuuKhoaHoc(String noi_dung) {
        this.noi_dung = noi_dung;
    }

    public String getNoi_dung() {
        return noi_dung;
    }

    public void setNoi_dung(String noi_dung) {
        this.noi_dung = noi_dung;
    }
}
