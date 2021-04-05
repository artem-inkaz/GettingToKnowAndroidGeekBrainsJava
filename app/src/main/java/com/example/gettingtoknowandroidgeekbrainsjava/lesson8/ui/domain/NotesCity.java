package com.example.gettingtoknowandroidgeekbrainsjava.lesson8.ui.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class NotesCity implements Parcelable {
    private String id;
    private String name;
    private String description;
    private String dataCreate;
    //    private Date dataUpdate;
    private String imageUrl;
//    private String videoUrl;
    private String avatar;

    public NotesCity(String id, String name, String description, String dataCreate, String imageUrl, String avatar) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dataCreate = dataCreate;
        this.imageUrl = imageUrl;
        this.avatar = avatar;
    }

    protected NotesCity(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        dataCreate = in.readString();
        imageUrl = in.readString();
        avatar = in.readString();
    }

    public static final Creator<NotesCity> CREATOR = new Creator<NotesCity>() {
        @Override
        public NotesCity createFromParcel(Parcel in) {
            return new NotesCity(in);
        }

        @Override
        public NotesCity[] newArray(int size) {
            return new NotesCity[size];
        }
    };

    public NotesCity() {

    }

    public String getId() {
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

    public String getAvatar() {
        return avatar;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDataCreate(String dataCreate) {
        this.dataCreate = dataCreate;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(dataCreate);
        dest.writeString(imageUrl);
        dest.writeString(avatar);
    }
}
