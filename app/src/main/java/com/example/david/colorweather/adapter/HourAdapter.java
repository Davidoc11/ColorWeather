package com.example.david.colorweather.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.david.colorweather.R;
import com.example.david.colorweather.domain.Hour;

import java.util.List;

/**
 * Created by David on 23/11/2017.
 */

public class HourAdapter extends BaseAdapter {
    List<Hour> hours;
    Context context;

    public HourAdapter(List<Hour> hours, Context context) {
        this.hours = hours;
        this.context = context;
    }

    @Override
    public int getCount() {
        return hours == null ? 0 : hours.size();
    }

    @Override
    public Object getItem(int position) {
        return hours.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.hourly_list_item, parent, false);
            holder = new Holder();
            holder.hourListTitle = convertView.findViewById(R.id.hourListTitle);
            holder.hourListDescription = convertView.findViewById(R.id.hourListDescription);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Hour hour = hours.get(position);
        holder.hourListDescription.setText(hour.getDesccription());
        holder.hourListTitle.setText(hour.getTitle());
        return convertView;
    }

    static class Holder {
        TextView hourListTitle;
        TextView hourListDescription;
    }
}
