package com.example.covidapp.module.Countries.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covidapp.R;
import com.example.covidapp.model.CountriesHolder;
import com.example.covidapp.model.CountriesModel;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import static com.example.covidapp.module.Countries.Countries.countrieList;
public class CountriesAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<CountriesModel> items;
    private NumberFormat formatter = new DecimalFormat("#,###");
    private NumberFormat percentage = new DecimalFormat("#,###.##");
    private DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

    public CountriesAdapter(Activity activity, List<CountriesModel> items) {
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
        CountriesHolder listViewHolder;
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        listViewHolder = new CountriesHolder();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.box_countries, null);
            convertView.setTag(listViewHolder);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (CountriesHolder) convertView.getTag();
        }

        listViewHolder.txt_countries = (TextView) convertView.findViewById(R.id.txt_countries);
        listViewHolder.txt_confirm = (TextView) convertView.findViewById(R.id.txt_confirm);
        listViewHolder.txt_active = (TextView) convertView.findViewById(R.id.txt_active);
        listViewHolder.txt_recovered = (TextView) convertView.findViewById(R.id.txt_recovered);
        listViewHolder.txt_incident = (TextView) convertView.findViewById(R.id.txt_incident);
        listViewHolder.txt_death = (TextView) convertView.findViewById(R.id.txt_incident);
        listViewHolder.txt_admin = (TextView) convertView.findViewById(R.id.txt_admin);
        listViewHolder.txt_lastupdate = (TextView) convertView.findViewById(R.id.txt_lastupdate);
        listViewHolder.lbl_location = (TextView) convertView.findViewById(R.id.lbl_location);
        listViewHolder.Iv_location = (ImageView) convertView.findViewById(R.id.Iv_location);
        listViewHolder.lin_location = (LinearLayout) convertView.findViewById(R.id.lin_location);

        CountriesModel data = items.get(position);


        if(data.getProvinceState().equals("null")|| data.getProvinceState().equals("")){
            listViewHolder.txt_countries.setText("-");
        }else{
            listViewHolder.txt_countries.setText(data.getProvinceState());
        }

        if(data.getConfirm().equals("null")|| data.getConfirm().equals("")){
            listViewHolder.txt_confirm.setText(": 0");
        }else{
            listViewHolder.txt_confirm.setText(": "+formatter.format(Double.parseDouble(data.getConfirm())));
        }

        if(data.getActive().equals("null")|| data.getActive().equals("")){
            listViewHolder.txt_active.setText(": 0");
        }else{
            listViewHolder.txt_active.setText(": "+formatter.format(Double.parseDouble(data.getActive())));
        }

        if(data.getRecovered().equals("null")|| data.getRecovered().equals("")){
            listViewHolder.txt_recovered.setText(": 0");
        }else{
            listViewHolder.txt_recovered.setText(": "+formatter.format(Double.parseDouble(data.getRecovered())));
        }

        if(data.getIncidentRate().equals("null")|| data.getIncidentRate().equals("")){
            listViewHolder.txt_incident.setText(": 0");
        }else{
            listViewHolder.txt_incident.setText(": "+formatter.format(Double.parseDouble(data.getIncidentRate())));
        }

        if(data.getIncidentRate().equals("null")|| data.getIncidentRate().equals("")){
            listViewHolder.txt_incident.setText(": 0");
        }else{
            listViewHolder.txt_incident.setText(": "+formatter.format(Double.parseDouble(data.getIncidentRate())));
        }

        if(data.getDeath().equals("null")|| data.getDeath().equals("")){
            listViewHolder.txt_death.setText(": 0");
        }else{
            listViewHolder.txt_death.setText(": "+formatter.format(Double.parseDouble(data.getDeath())));
        }

        if(data.getDeath().equals("null")|| data.getDeath().equals("")){
            listViewHolder.txt_death.setText(": 0");
        }else{
            listViewHolder.txt_death.setText(": "+formatter.format(Double.parseDouble(data.getDeath())));
        }

        if(data.getAdmin().equals("null")|| data.getAdmin().equals("")){
            listViewHolder.txt_admin.setText(": -");
        }else{
            listViewHolder.txt_admin.setText(": "+data.getAdmin());
        }

        if(data.getLastUpdate().equals("null")|| data.getLastUpdate().equals("")){
            listViewHolder.txt_lastupdate.setText(": -");
        }else{
            listViewHolder.txt_lastupdate.setText(formatter.format(Double.parseDouble(data.getLastUpdate())));
        }

        listViewHolder.Iv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int)view.getTag();
                String lat=countrieList.get(position).getLat();
                String longi=countrieList.get(position).getLongi();

                Uri uri = Uri.parse("geo:"+lat+","+longi+"?z=11");
                Toast.makeText(activity, "url: "+uri, Toast.LENGTH_LONG).show();
                gotoMap(uri);
            }
        });

        listViewHolder.lin_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int)view.getTag();
                String lat=countrieList.get(position).getLat();
                String longi=countrieList.get(position).getLongi();

                Uri uri = Uri.parse("geo:"+lat+","+longi+"?z=11");
                Toast.makeText(activity, "url: "+uri, Toast.LENGTH_LONG).show();
                gotoMap(uri);
            }
        });

        listViewHolder.lbl_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int)view.getTag();
                String lat=countrieList.get(position).getLat();
                String longi=countrieList.get(position).getLongi();

                Uri uri = Uri.parse("geo:"+lat+","+longi+"?z=11");
                Toast.makeText(activity, "url: "+uri, Toast.LENGTH_LONG).show();
                gotoMap(uri);
            }
        });



        listViewHolder.txt_countries.setTag(position);
        listViewHolder.Iv_location.setTag(position);
        listViewHolder.lin_location.setTag(position);
        listViewHolder.lbl_location.setTag(position);

        return convertView;
    }

    private void gotoMap(Uri geoLocation){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(intent);
        }
    }


}
