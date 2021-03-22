package com.example.covidapp.module.DailySeries.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.covidapp.R;
import com.example.covidapp.model.DailySeriesModel;
import com.example.covidapp.model.DetailSummary;
import com.example.covidapp.model.dailySeriesHolder;
import com.example.covidapp.model.detailSummaryHolder;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DailySeriesAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DailySeriesModel> items;
    private NumberFormat formatter = new DecimalFormat("#,###");
    private NumberFormat percentage = new DecimalFormat("#,###.##");
    private DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

    public DailySeriesAdapter(Activity activity, List<DailySeriesModel> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        dailySeriesHolder listViewHolder;
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        listViewHolder = new dailySeriesHolder();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.box_daily_series, null);
            convertView.setTag(listViewHolder);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (dailySeriesHolder) convertView.getTag();
        }

        listViewHolder.txt_reportdate = (TextView) convertView.findViewById(R.id.txt_reportdate);
        listViewHolder.txt_province = (TextView) convertView.findViewById(R.id.txt_province);
        listViewHolder.txt_confirmed = (TextView) convertView.findViewById(R.id.txt_confirmed);
        listViewHolder.txt_region = (TextView) convertView.findViewById(R.id.txt_region);
        listViewHolder.txt_recovered = (TextView) convertView.findViewById(R.id.txt_recovered);
        listViewHolder.txt_death = (TextView) convertView.findViewById(R.id.txt_death);


        DailySeriesModel data = items.get(position);


        if(data.getReportDate().equals("null")|| data.getReportDate().equals("")){
            listViewHolder.txt_reportdate.setText("-");
        }else{
            Date date = null;
            try {
                date = inputFormat.parse(data.getReportDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            listViewHolder.txt_reportdate.setText(outputFormat.format(date));
        }

        if(data.getProvince().equals("null")|| data.getProvince().equals("")){
            listViewHolder.txt_province.setText(": -");
        }else{
            listViewHolder.txt_province.setText(": "+data.getProvince());
        }

        if(data.getTotalConfirm().equals("null")|| data.getTotalConfirm().equals("")){
            listViewHolder.txt_confirmed.setText(": 0");
        }else{
            listViewHolder.txt_confirmed.setText(": "+formatter.format(Double.parseDouble(data.getTotalConfirm())));

        }

        if(data.getTotalRecovered().equals("null")|| data.getTotalRecovered().equals("")){
            listViewHolder.txt_recovered.setText(": 0");
        }else{
            listViewHolder.txt_recovered.setText(": "+formatter.format(Double.parseDouble(data.getTotalRecovered())));

        }

        if(data.getTotalDeth().equals("null")|| data.getTotalDeth().equals("")){
            listViewHolder.txt_death.setText(": 0");
        }else{
            listViewHolder.txt_death.setText(": "+formatter.format(Double.parseDouble(data.getTotalDeth())));

        }

        if(data.getRegion().equals("null")|| data.getRegion().equals("")){
            listViewHolder.txt_region.setText(": -");
        }else{
            listViewHolder.txt_region.setText(": "+data.getRegion());
        }





        listViewHolder.txt_reportdate.setTag(position);

        return convertView;
    }


}