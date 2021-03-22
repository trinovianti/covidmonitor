package com.example.covidapp.module.Countries;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covidapp.R;
import com.example.covidapp.model.CountriesModel;
import com.example.covidapp.module.Countries.adapter.CountriesAdapter;
import com.example.covidapp.module.DailySeries.DailySeries;
import com.example.covidapp.module.Home.Home;
import java.util.ArrayList;
import java.util.List;

import static com.example.covidapp.module.Countries.Data.getDataCountries.getCountries;
import static com.example.covidapp.module.DailySeries.Data.getDataDailySeries.getDailySeries;
import static com.example.covidapp.util.Api.confirmedApi;
import static com.example.covidapp.util.Api.deathsApi;

public class Countries  extends AppCompatActivity {
    ListView lv_countrie;
    public static List<CountriesModel> countrieList = new ArrayList<>();
    public static CountriesAdapter countriesAdapter;
    TextView lbl_header;
    View v_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        ///define id
        lv_countrie = findViewById(R.id.lv_countrie);
        lbl_header = findViewById(R.id.lbl_header);
        v_back = findViewById(R.id.v_back);

        ///set adapter
        countriesAdapter = new CountriesAdapter(this, countrieList);
        lv_countrie.setAdapter(countriesAdapter);
        try{
            Bundle extrasData = getIntent().getExtras();
            String url=extrasData.getString("sUrl");
            String header=extrasData.getString("sHeader");
            getCountries(this,url);
            lbl_header.setText(header);
        }catch (Exception e){

        }

        v_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Countries.this, Home.class);
        startActivity(intent);
        finish();
    }
}