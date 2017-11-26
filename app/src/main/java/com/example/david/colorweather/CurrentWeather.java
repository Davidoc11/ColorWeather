package com.example.david.colorweather;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import butterknife.BindDrawable;
import butterknife.ButterKnife;

/**
 * Created by David on 23/11/2017.
 */

public class CurrentWeather {
    public static final String CLEAR_NIGHT = "clear-night";
    public static final String CLEAR_DAY = "clear-day";
    public static final String CLOUDY = "cloudy";
    public static final String PARTLY_CLOUDY_NIGHT = "partly-cloudy-night";
    public static final String FOG = "fog";
    public static final String NA = "na";
    public static final String PARTLY_CLOUDY_DAY = "partly-cloudy-day";
    public static final String RAIN = "rain";
    public static final String SLEET = "sleet";
    public static final String SNOW = "snow";
    public static final String SUNNY = "sunny";
    public static final String WIND = "wind";


    @BindDrawable(R.drawable.clear_night)
    Drawable clearNight;
    @BindDrawable(R.drawable.clear_day)
    Drawable clearDay;
    @BindDrawable(R.drawable.cloudy)
    Drawable cloudy;
    @BindDrawable(R.drawable.cloudy_night)
    Drawable cloudyNight;
    @BindDrawable(R.drawable.fog)
    Drawable fog;
    @BindDrawable(R.drawable.na)
    Drawable na;
    @BindDrawable(R.drawable.partly_cloudy)
    Drawable partlyCloudy;
    @BindDrawable(R.drawable.rain)
    Drawable rain;
    @BindDrawable(R.drawable.sleet)
    Drawable sleet;
    @BindDrawable(R.drawable.snow)
    Drawable snow;
    @BindDrawable(R.drawable.sunny)
    Drawable sunny;
    @BindDrawable(R.drawable.wind)
    Drawable wind;
    private String description;
    private String current;
    private String highest;
    private String lowest;
    private String imageIcon;

    public CurrentWeather(Activity activity, String description, String current, String highest, String lowest, String imageIcon) {
        ButterKnife.bind(this, activity);
        this.description = description;
        this.current = current;
        this.highest = highest;
        this.lowest = lowest;
        this.imageIcon = imageIcon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getHighest() {
        return highest;
    }

    public void setHighest(String highest) {
        this.highest = highest;
    }

    public String getLowest() {
        return lowest;
    }

    public void setLowest(String lowest) {
        this.lowest = lowest;
    }

    public String getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }

    public Drawable getIconDrawableResource() {

        switch (imageIcon) {
            case CLEAR_NIGHT:
                return clearNight;
            case CLEAR_DAY:
                return clearDay;
            case CLOUDY:
                return cloudy;
            case PARTLY_CLOUDY_NIGHT:
                return cloudyNight;
            case FOG:
                return fog;
            case NA:
                return na;
            case PARTLY_CLOUDY_DAY:
                return partlyCloudy;
            case RAIN:
                return rain;
            case SLEET:
                return sleet;
            case SNOW:
                return snow;
            case SUNNY:
                return sunny;
            case WIND:
                return wind;
            default:
                return na;

        }

    }
}
