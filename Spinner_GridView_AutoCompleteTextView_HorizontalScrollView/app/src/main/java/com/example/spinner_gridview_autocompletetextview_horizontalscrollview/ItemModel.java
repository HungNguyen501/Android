package com.example.spinner_gridview_autocompletetextview_horizontalscrollview;

public class ItemModel {
    String caption;
    int thumbnail;
    int image;

    public ItemModel(String caption, int thumbnail, int image) {
        this.caption = caption;
        this.thumbnail = thumbnail;
        this.image = image;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCaption() {
        return caption;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public int getImage() {
        return image;
    }
}
