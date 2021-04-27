package com.example.quanlycanbo.models;

import java.util.List;

public class ChamThi {
    private List<LopThi> lopThi;

    public ChamThi() {
        // Constructor
    }

    public ChamThi(List<LopThi> lopThi) {
        this.lopThi = lopThi;
    }

    public List<LopThi> getLopThi() {
        return lopThi;
    }

    public void setLopThi(List<LopThi> lopThi) {
        this.lopThi = lopThi;
    }
}
