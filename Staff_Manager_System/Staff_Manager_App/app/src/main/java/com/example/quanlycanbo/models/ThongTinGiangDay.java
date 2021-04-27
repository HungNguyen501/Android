package com.example.quanlycanbo.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ThongTinGiangDay implements Serializable {
    private List<MonGiangDay> monGiangDayList;

    public ThongTinGiangDay() {
        // Constructor
        this.monGiangDayList = new ArrayList<MonGiangDay>();
    }

    public ThongTinGiangDay(List<MonGiangDay> monGiangDayList) {
        this.monGiangDayList = monGiangDayList;
    }

    public List<MonGiangDay> getMonGiangDayList() {
        return monGiangDayList;
    }

    public void setMonGiangDayList(List<MonGiangDay> monGiangDay) {
        this.monGiangDayList = monGiangDay;
    }
}
