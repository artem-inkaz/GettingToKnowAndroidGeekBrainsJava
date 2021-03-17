package com.example.gettingtoknowandroidgeekbrainsjava.lesson6;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Notes implements Parcelable {
    private int id;
    private String name;
    private String description;
    private Date dataCreate;
    private Date dataUpdate;
    private int imageIndex;
    private String videoUrl;
    private int avatar;

    public Notes(int id, String name, String description, Date dataCreate, int avatar) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dataCreate = dataCreate;
        this.avatar = avatar;
    }

    protected Notes(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        imageIndex = in.readInt();
        videoUrl = in.readString();
        avatar = in.readInt();
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(imageIndex);
        dest.writeString(videoUrl);
        dest.writeInt(avatar);
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

    public Date getDataCreate() {
        return dataCreate;
    }

    public int getAvatar() {
        return avatar;
    }
}
