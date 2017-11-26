package com.example.david.colorweather;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.david.colorweather.adapter.DayAdapter;
import com.example.david.colorweather.domain.Day;

import java.util.ArrayList;
import java.util.List;

public class DailyWeatherActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                    
        setContentView(R.layout.activity_daily_weather);

       /* List<Day> days=new ArrayList<>();
        for(int i =0;i<500;i++)
            days.add(new Day("Monday","Uno","Dos"));
       */
       Intent intent =getIntent();
       List<Day> days=intent.getParcelableArrayListExtra(MainActivity.DAYS_LIST);
        DayAdapter adapter=new DayAdapter(days,this);
        setListAdapter(adapter);
        
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(this,l.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
    }


}
