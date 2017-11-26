package com.example.david.colorweather.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by David on 24/11/2017.
 */

public class Minute implements Parcelable {
    private String minute;
    private String precipProbability;

    public Minute(String minute, String description) {
        this.minute = minute;
        this.precipProbability = description;
    }

    protected Minute(Parcel in) {
        minute = in.readString();
        precipProbability = in.readString();
    }

    public static final Creator<Minute> CREATOR = new Creator<Minute>() {
        @Override
        public Minute createFromParcel(Parcel in) {
            return new Minute(in);
        }

        @Override
        public Minute[] newArray(int size) {
            return new Minute[size];
        }
    };

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(String precipProbability) {
        this.precipProbability = precipProbability;
    }

    @Override
    public String toString() {
        return "Minute{" +
                "minute='" + minute + '\'' +
                ", precipProbability='" + precipProbability + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(minute);
        dest.writeString(precipProbability);
    }
}
