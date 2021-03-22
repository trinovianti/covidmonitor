package com.example.covidapp.module.Home.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapp.R;
import com.example.covidapp.model.Summary;
import com.example.covidapp.module.Home.Home;
import com.example.covidapp.module.Summary.SummaryDetail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.MyViewHolder> {
    private List<Summary> summaryList;
    private DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_reportdate, lbl_confirm, txt_confirm, lbl_death, txt_death, lbl_incidentrate, txt_incidentrate, lbl_seeDetail;
        LinearLayout lin_seeDetail;
        MyViewHolder(View view) {
            super(view);
            txt_reportdate = view.findViewById(R.id.txt_reportdate);
            lbl_confirm = view.findViewById(R.id.lbl_confirm);
            txt_confirm = view.findViewById(R.id.txt_confirm);
            lbl_death = view.findViewById(R.id.lbl_death);
            txt_death = view.findViewById(R.id.txt_death);
            lbl_incidentrate = view.findViewById(R.id.lbl_incidentrate);
            txt_incidentrate = view.findViewById(R.id.txt_incidentrate);
            lin_seeDetail = view.findViewById(R.id.lin_seeDetail);
            lbl_seeDetail = view.findViewById(R.id.lbl_seeDetail);


        }
    }
    public SummaryAdapter(List<Summary> summaryList) {
        this.summaryList = summaryList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.box_summaries, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Summary summary = summaryList.get(position);

        holder.txt_confirm.setText(summary.getTotalConfirm());
        holder.txt_death.setText(summary.getTotalDeath());
        holder.txt_incidentrate.setText(summary.getIncidentRate());

        if(summary.getReportDate().equals("null")|| summary.getReportDate().equals("")){
            holder.txt_reportdate.setText(summary.getReportDate());
        }else{
            Date date = null;
            try {
                date = inputFormat.parse( summary.getReportDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            holder.txt_reportdate.setText(outputFormat.format(date));

        }
    }
    @Override
    public int getItemCount() {
        return summaryList.size();
    }
}