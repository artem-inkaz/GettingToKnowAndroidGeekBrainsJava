package com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain;

import androidx.annotation.DrawableRes;

public class NotesCity {
    private int id;
    private String name;
    private String description;
    private String dataCreate;
    //    private Date dataUpdate;
    private String imageUrl;
//    private String videoUrl;
    private int avatar;

    public NotesCity(int id, String name, String description, String dataCreate, String imageUrl, int avatar) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dataCreate = dataCreate;
        this.imageUrl = imageUrl;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDataCreate() {
        return dataCreate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getAvatar() {
        return avatar;
    }
}
