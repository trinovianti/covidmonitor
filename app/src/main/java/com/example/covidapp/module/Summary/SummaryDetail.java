package com.example.covidapp.module.Summary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.covidapp.R;
import com.example.covidapp.model.DetailSummary;
import com.example.covidapp.module.Countries.Countries;
import com.example.covidapp.module.Home.Home;
import com.example.covidapp.module.Summary.adapter.SummaryDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.covidapp.module.Summary.Data.DataDetailSummary.getDetailSummary;


public class SummaryDetail extends AppCompatActivity {
    ListView lv_detail_summary;
    public static List<DetailSummary> summaryDetailList = new ArrayList<>();
    public static SummaryDetailAdapter summaryDetailAdapter;
    TextView lbl_header;
    View v_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_detail);

        ///define id
        lv_detail_summary = findViewById(R.id.lv_detail_summary);
        lbl_header = findViewById(R.id.lbl_header);
        v_back = findViewById(R.id.v_back);

        ///set adapter
        summaryDetailAdapter= new SummaryDetailAdapter(this, summaryDetailList);
        lv_detail_summary.setAdapter(summaryDetailAdapter);
        lbl_header.setText("Summary Detail");
        getDetailSummary(this);
        v_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SummaryDetail.this, Home.class);
        startActivity(intent);
        finish();
    }
}