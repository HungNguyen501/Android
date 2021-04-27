package com.example.quanlycanbo.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GiaDinh implements Serializable {
    private List<ConCanBo>conCanBoList;

    public GiaDinh() {
        // Constructor
        this.conCanBoList = new ArrayList<ConCanBo>();
    }

    public GiaDinh(List<ConCanBo> conCanBoList) {

        this.conCanBoList = conCanBoList;
    }

    public List<ConCanBo> getConCanBoList() {
        return conCanBoList;
    }

    public void setConCanBoList(List<ConCanBo> conCanBoList) {
        this.conCanBoList = conCanBoList;
    }
}
