package com.example.david.colorweather.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by David on 23/11/2017.
 */

public class Day implements Parcelable {
    private String title;
    private String description;
    private String rainProbability;

    public Day(String title, String description, String rainProbability) {
        this.title = title;
        this.description = description;
        this.rainProbability = rainProbability;
    }

    protected Day(Parcel in) {
        title = in.readString();
        description = in.readString();
        rainProbability = in.readString();
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRainProbability() {
        return rainProbability;
    }

    public void setRainProbability(String rainProbability) {
        this.rainProbability = rainProbability;
    }

    @Override
    public String toString() {
        return "Day{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rainProbability='" + rainProbability + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(rainProbability);

    }
}
