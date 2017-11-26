package com.example.david.colorweather.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by David on 23/11/2017.
 */

public class Hour implements Parcelable{
    private String title;
    private String desccription;

    public Hour(String title, String desccription) {
        this.title = title;
        this.desccription = desccription;
    }

    protected Hour(Parcel in) {
        title = in.readString();
        desccription = in.readString();
    }

    public static final Creator<Hour> CREATOR = new Creator<Hour>() {
        @Override
        public Hour createFromParcel(Parcel in) {
            return new Hour(in);
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesccription() {
        return desccription;
    }

    public void setDesccription(String desccription) {
        this.desccription = desccription;
    }

    @Override
    public String toString() {
        return "Hour{" +
                "title='" + title + '\'' +
                ", desccription='" + desccription + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(desccription);
    }
}
