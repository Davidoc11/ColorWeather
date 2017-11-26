package com.example.david.colorweather;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.colorweather.adapter.HourAdapter;
import com.example.david.colorweather.domain.Hour;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HourlyWeatherActivity extends Activity {
    @BindView(R.id.hourListView)
    ListView hourListView;
    @BindView(R.id.hourListViewEmpty)
    TextView hourListViewEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_weather);
        ButterKnife.bind(this);
        List<Hour> hours = getIntent().getParcelableArrayListExtra(MainActivity.HOUR_LIST);

        HourAdapter hourAdapter = new HourAdapter(hours, this);
        hourListView.setAdapter(hourAdapter);
        hourListView.setEmptyView(hourListViewEmpty);
    }

}
