package com.example.david.colorweather;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.colorweather.adapter.MinuteAdapter;
import com.example.david.colorweather.domain.Minute;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MinutelyWeatherActivity extends Activity {
    private static final String TAG = MinutelyWeatherActivity.class.getSimpleName();
    @BindView(R.id.recyclerViewMinutes)RecyclerView recyclerViewMinutes;
    @BindView(R.id.noDataMinute)
    TextView noDataMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minutely_weather);
        ButterKnife.bind(this);
        List<Minute> minutes=getIntent().getParcelableArrayListExtra(MainActivity.MINUTES_LIST);

        if (minutes!=null&&minutes.isEmpty()) {
            recyclerViewMinutes.setVisibility(View.VISIBLE);
            noDataMinute.setVisibility(View.GONE);
            MinuteAdapter minuteAdapter = new MinuteAdapter(minutes, this);
            recyclerViewMinutes.setAdapter(minuteAdapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerViewMinutes.setLayoutManager(layoutManager);
            recyclerViewMinutes.setHasFixedSize(true);
        }
        else{
            noDataMinute.setVisibility(View.VISIBLE);
            recyclerViewMinutes.setVisibility(View.GONE);
        }
    }
   
}
