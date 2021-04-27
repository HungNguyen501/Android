package com.example.gmailapp;

import android.graphics.drawable.Drawable;

import java.util.Random;

public class ItemModel {

    private String name, subject, content, time;
    private int colorLabel;
    private boolean favorite;

    public ItemModel(String name, String subject, String content, String time) {
        this.name = name;
        this.subject = subject;
        this.content = content;
        this.time = time;
        this.favorite = false;

        Random random = new Random();
        this.colorLabel = random.nextInt(7);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setColorLabel(int colorLabel) {
        this.colorLabel = colorLabel;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public int getColorLabel() {
        return colorLabel;
    }

}
