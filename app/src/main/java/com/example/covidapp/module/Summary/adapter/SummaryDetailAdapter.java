package com.example.covidapp.module.Summary.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.covidapp.R;
import com.example.covidapp.model.DetailSummary;
import com.example.covidapp.model.Summary;
import com.example.covidapp.model.detailSummaryHolder;
import com.example.covidapp.module.Home.Home;
import com.example.covidapp.module.Summary.SummaryDetail;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SummaryDetailAdapter  extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DetailSummary> items;
    private NumberFormat formatter = new DecimalFormat("#,###");
    private NumberFormat percentage = new DecimalFormat("#,###.##");
    private DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

    public SummaryDetailAdapter(Activity activity, List<DetailSummary> items) {
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
        detailSummaryHolder listViewHolder;
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        listViewHolder = new detailSummaryHolder();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.box_detail_summary, null);
            convertView.setTag(listViewHolder);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (detailSummaryHolder) convertView.getTag();
        }

        listViewHolder.txt_china = (TextView) convertView.findViewById(R.id.txt_china);
        listViewHolder.txt_dchina = (TextView) convertView.findViewById(R.id.txt_dchina);

        listViewHolder.txt_outsidechina = (TextView) convertView.findViewById(R.id.txt_outsidechina);
        listViewHolder.txt_doutsidechina = (TextView) convertView.findViewById(R.id.txt_doutsidechina);
        listViewHolder.txt_confirm = (TextView) convertView.findViewById(R.id.txt_confirm);
        listViewHolder.txt_death = (TextView) convertView.findViewById(R.id.txt_death);
        listViewHolder.txt_incidentrate = (TextView) convertView.findViewById(R.id.txt_incidentrate);
        listViewHolder.txt_reportdate = (TextView) convertView.findViewById(R.id.txt_reportdate);

        DetailSummary data = items.get(position);


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

        if(data.getChinaConfirm().equals("null")|| data.getChinaConfirm().equals("")){
            listViewHolder.txt_china.setText("0");
        }else{
            listViewHolder.txt_china.setText(formatter.format(Double.parseDouble(data.getChinaConfirm())));
        }

        if(data.getChinaDeath().equals("null")|| data.getChinaDeath().equals("")){
            listViewHolder.txt_dchina.setText(": 0");
        }else{
            listViewHolder.txt_dchina.setText(": "+formatter.format(Double.parseDouble(data.getChinaDeath())));
        }

        if(data.getOutsideChinaConfirm().equals("null")|| data.getOutsideChinaConfirm().equals("")){
            listViewHolder.txt_outsidechina.setText(": 0");
        }else{
            listViewHolder.txt_outsidechina.setText(": "+formatter.format(Double.parseDouble(data.getOutsideChinaConfirm())));
        }

        if(data.getOutsideChinaDeath().equals("null")|| data.getOutsideChinaDeath().equals("")){
            listViewHolder.txt_doutsidechina.setText(": 0");
        }else{
            listViewHolder.txt_doutsidechina.setText(": "+formatter.format(Double.parseDouble(data.getOutsideChinaDeath())));
        }

        if(data.getTotalConfirm().equals("null")|| data.getTotalConfirm().equals("")){
            listViewHolder.txt_confirm.setText(": 0");
        }else{
            listViewHolder.txt_confirm.setText(": "+formatter.format(Double.parseDouble(data.getTotalConfirm())));
        }

        if(data.getTotalDeath().equals("null")|| data.getTotalDeath().equals("")){
            listViewHolder.txt_death.setText(": 0");
        }else{
            listViewHolder.txt_death.setText(": "+formatter.format(Double.parseDouble(data.getTotalDeath())));
        }

        if(data.getIncidentRate().equals("null")|| data.getIncidentRate().equals("")){
            listViewHolder.txt_incidentrate.setText("0%");
        }else{
            listViewHolder.txt_incidentrate.setText(percentage.format(Double.parseDouble(data.getIncidentRate()))+"%");
        }



        listViewHolder.txt_reportdate.setTag(position);

        return convertView;
    }


}