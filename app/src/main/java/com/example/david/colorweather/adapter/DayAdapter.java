package com.example.david.colorweather.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.david.colorweather.domain.Day;
import com.example.david.colorweather.R;

import java.util.List;

/**
 * Created by David on 23/11/2017.
 */

public class DayAdapter extends BaseAdapter {
    List<Day> days;
    Context context;

    public DayAdapter(List<Day> days, Context context) {
        this.days = days;
        this.context = context;
    }

    @Override
    public int getCount() {
        return days == null ? 0 : days.size();
    }

    @Override
    public Object getItem(int position) {
        return days.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.daily_list_item, parent, false);
            holder = new Holder();
            holder.textViewDailyListTitle = convertView.findViewById(R.id.textViewDailyListTitle);
            holder.textViewDailyListDescription = convertView.findViewById(R.id.textViewDailyListDescription);
            holder.textViewDailyListProbability = convertView.findViewById(R.id.textViewDailyListProbability);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        Day day = days.get(position);


        holder.textViewDailyListTitle.setText(day.getTitle());
        holder.textViewDailyListDescription.setText(day.getDescription());
        holder.textViewDailyListProbability.setText(day.getRainProbability());
        return convertView;
    }

    static class Holder {
        TextView textViewDailyListTitle;
        TextView textViewDailyListDescription;
        TextView textViewDailyListProbability;
    }
}
