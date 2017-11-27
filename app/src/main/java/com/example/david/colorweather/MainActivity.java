package com.example.david.colorweather;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.david.colorweather.domain.Day;
import com.example.david.colorweather.domain.Hour;
import com.example.david.colorweather.domain.Minute;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String CURRENTLY = "currently";
    private static final String DAILY = "daily";
    private static final String HOURLY = "hourly";
    private static final String DATA = "data";
    private static final String TIME = "time";
    private static final String PRECIPPROBABILITY = "precipProbability";
    private static final String SUMMARY = "summary";
    public static final String MINUTELY = "minutely";
    public static final String PRECIP_PROBABILITY = "precipProbability";
    public static final String TIMEZONE = "timezone";
    public static final String DAYS_LIST = "daysList";
    public static final String HOUR_LIST = "hourList";
    public static final String MINUTES_LIST = "minutesList";
    public static final int MY_PERMISSIONS_ACCESS_COARSE_LOCATION = 0;
    @BindView(R.id.myLayout)
    ConstraintLayout myLayout;
    @BindView(R.id.textViewDaily)
    TextView daily;
    @BindView(R.id.textViewMinutely)
    TextView minutely;
    @BindView(R.id.textViewHourly)
    TextView hourly;
    @BindView(R.id.imageViewIcon)
    ImageView imageViewIcon;
    @BindView(R.id.textViewDescription)
    TextView textViewDescription;
    @BindView(R.id.textViewCurrentTemp)
    TextView textViewCurrentTemp;
    @BindView(R.id.textViewTempoHigh)
    TextView textViewTempoHigh;
    @BindView(R.id.textViewTempLow)
    TextView textViewTempLow;
    @BindDrawable(R.drawable.cloudy_night)
    Drawable clearNight;
    List<Day> days;
    List<Hour> hours;
    List<Minute> minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Snackbar.make(myLayout, "Debes aceptar los permisos", Snackbar.LENGTH_INDEFINITE).setAction("Aceptar", (view) -> {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_ACCESS_COARSE_LOCATION);
            }).show();


            return;
        } else {
            FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                Log.d(TAG, "default");
                                obtenerInfo(location.getLatitude(), location.getLongitude());
                            }
                        }
                    });
        }

    }

    private void obtenerInfo(Double lat, Double lon) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format("https://api.darksky.net/forecast/088c263283e42b5a07446c7f0bc08787/%f,%f?units=si", lat, lon);

        Log.d(TAG, "URL : " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        CurrentWeather cw1 = getCurrentWeather(response);


                        days = getDays(response);
                        daily.setOnClickListener(getOnClickIntent(DailyWeatherActivity.class, (ArrayList) days, DAYS_LIST));


                        hours = getHours(response);
                        hourly.setOnClickListener(getOnClickIntent(HourlyWeatherActivity.class, (ArrayList) hours, HOUR_LIST));


                        minutes = getMinutes(response);
                        minutely.setOnClickListener(getOnClickIntent(MinutelyWeatherActivity.class, (ArrayList) minutes, MINUTES_LIST));

                        imageViewIcon.setImageDrawable(cw1.getIconDrawableResource());
                        textViewDescription.setText(cw1.getDescription());
                        textViewCurrentTemp.setText(cw1.getCurrent());
                        textViewTempoHigh.setText(String.format("H: %s°", cw1.getHighest()));
                        textViewTempLow.setText(String.format("L: %s°", cw1.getLowest()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Toast.makeText(this, "Comprueba tu conexion a internet", Toast.LENGTH_LONG).show());

        queue.add(stringRequest);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_ACCESS_COARSE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                        mFusedLocationClient.getLastLocation()
                                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                                    @Override
                                    public void onSuccess(Location location) {
                                        // Got last known location. In some rare situations this can be null.
                                        if (location != null) {

                                            Log.d(TAG, "onRequestPermissions");
                                            Log.d(TAG, location.getLongitude() + "");
                                            Log.d(TAG, location.getLatitude() + "");
                                            obtenerInfo(location.getLatitude(), location.getLongitude());
                                        }
                                    }
                                });
                    }

                } else {

                }
                return;
            }

        }
    }


    private View.OnClickListener getOnClickIntent(Class clase, ArrayList parcelable, String pMsg) {
        return (view) -> {
            Intent intent = new Intent(this, clase);
            intent.putParcelableArrayListExtra(pMsg, parcelable);
            startActivity(intent);
        };
    }

    private View.OnClickListener getOnClick(String msg) {
        return (view) -> {
            //Log.d(TAG, "Click on " + msg);
            Toast.makeText(this, "Click on " + msg, Toast.LENGTH_SHORT).show();
        };
    }

    private CurrentWeather getCurrentWeather(String json) throws JSONException {
        JSONObject root = new JSONObject(json);
        JSONObject current = root.getJSONObject(CURRENTLY);
        JSONObject daily = root.getJSONObject(DAILY);
        JSONArray data = daily.getJSONArray(DATA);
        JSONObject firstDay = data.getJSONObject(0);

        String icon = current.getString("icon");
        String summary = current.getString(SUMMARY);
        String temperature = Math.round(current.getDouble("temperature")) + "";
        String max = Math.round(firstDay.getDouble("temperatureHigh")) + "";
        String min = Math.round(firstDay.getDouble("temperatureLow")) + "";

        return new CurrentWeather(this, summary, temperature, max, min, icon);

    }


    private List<Day> getDays(String json) throws JSONException {
        DateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        List<Day> days = new ArrayList<>();
        JSONObject root = new JSONObject(json);
        dayFormat.setTimeZone(TimeZone.getTimeZone(root.getString(TIMEZONE)));
        JSONObject daily = root.getJSONObject(DAILY);
        JSONArray data = daily.getJSONArray(DATA);
        for (int i = 0; i < data.length(); i++) {
            JSONObject day = data.getJSONObject(i);
            String prob = "Rain Probaility: " + day.getDouble(PRECIPPROBABILITY) * 100 + "%";
            String desc = day.getString(SUMMARY);
            String dayName = dayFormat.format(day.getDouble(TIME) * 1000);

            Day dayElement = new Day(dayName, desc, prob);
            days.add(dayElement);
        }
        days.remove(days.size() - 1);
        return days;
    }

    private List<Hour> getHours(String json) throws JSONException {
        List<Hour> hours = new ArrayList<>();
        DateFormat formatHour = new SimpleDateFormat("kk:mm", Locale.getDefault());
        JSONObject root = new JSONObject(json);
        JSONObject hourly = root.getJSONObject(HOURLY);
        formatHour.setTimeZone(TimeZone.getTimeZone(root.getString(TIMEZONE)));
        JSONArray hourArray = hourly.getJSONArray(DATA);
        for (int i = 0; i < hourArray.length(); i++) {
            JSONObject hourObject = hourArray.getJSONObject(i);
            String hourTitle = formatHour.format(hourObject.getDouble(TIME) * 1000);
            String desc = hourObject.getString(SUMMARY);

            Hour hour = new Hour(hourTitle, desc);
            hours.add(hour);
        }
        return hours;
    }

    private List<Minute> getMinutes(String json) throws JSONException {
        List<Minute> minutes = null;
        DateFormat formatMinutes = new SimpleDateFormat("kk:mm", Locale.getDefault());
        JSONObject root = new JSONObject(json);
        formatMinutes.setTimeZone(TimeZone.getTimeZone(root.getString(TIMEZONE)));
       if (root.has(MINUTELY)){
           minutes=new ArrayList<>();
           JSONObject hourly = root.getJSONObject(MINUTELY);
           Log.d(TAG,""+hourly);
           JSONArray hourArray = hourly.getJSONArray(DATA);
           for (int i = 0; i < hourArray.length(); i++) {
               JSONObject hourObject = hourArray.getJSONObject(i);
               String minuteTitle = formatMinutes.format(hourObject.getDouble(TIME) * 1000);
               String desc = "Rain Probaility: " + hourObject.getDouble(PRECIP_PROBABILITY) * 100 + "%";

               Minute minute = new Minute(minuteTitle, desc);
               minutes.add(minute);
           }
       }

        return minutes;
    }

}
