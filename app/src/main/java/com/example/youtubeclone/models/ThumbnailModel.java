package com.example.youtubeclone.models;

public class ThumbnailModel {
    private String title;
    private String image;

    private String description;
    private String date;
    private String videoID;

    public ThumbnailModel(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
