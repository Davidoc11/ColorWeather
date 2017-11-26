package com.example.david.colorweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.colorweather.R;
import com.example.david.colorweather.domain.Minute;

import java.util.List;

/**
 * Created by David on 24/11/2017.
 */

public class MinuteAdapter extends RecyclerView.Adapter {
    private static final String TAG = MinuteAdapter.class.getSimpleName();
    List<Minute> minutes;
    Context context;

    public MinuteAdapter(List<Minute> minutes, Context context) {
        this.minutes = minutes;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.minutely_list_item, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Holder) holder).onBind(position);
    }

    @Override
    public int getItemCount() {
        return minutes == null ? 0 : minutes.size();
    }


    class Holder extends RecyclerView.ViewHolder {
        TextView minuteListTitle;
        TextView minuteListDescription;

        public Holder(View itemView) {
            super(itemView);
            minuteListTitle = itemView.findViewById(R.id.minuteListTitle);
            minuteListDescription = itemView.findViewById(R.id.minuteListDescription);
            itemView.setOnClickListener((view) -> {
                Toast.makeText(view.getContext(), minuteListTitle.getText() + " - " + minuteListDescription.getText(), Toast.LENGTH_SHORT).show();
            });
        }

        public void onBind(int position) {
            Minute minute = minutes.get(position);
            minuteListTitle.setText(minute.getMinute());
            minuteListDescription.setText(minute.getPrecipProbability());
        }
    }
}