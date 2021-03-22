package com.example.covidapp.module.DailySeries;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.covidapp.R;
import com.example.covidapp.model.DailySeriesModel;
import com.example.covidapp.model.DetailSummary;
import com.example.covidapp.module.Countries.Countries;
import com.example.covidapp.module.DailySeries.adapter.DailySeriesAdapter;
import com.example.covidapp.module.Home.Home;
import com.example.covidapp.module.Summary.SummaryDetail;
import com.example.covidapp.module.Summary.adapter.SummaryDetailAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.covidapp.module.DailySeries.Data.getDataDailySeries.getDailySeries;
import static com.example.covidapp.module.Summary.Data.DataDetailSummary.getDetailSummary;

public class DailySeries extends AppCompatActivity {
    ListView lv_dailyseries;
    LinearLayout lin_home,  lin_dailyseries, lin_txt_date, lin_date;
    ImageView Iv_home,  Iv_date;
    TextView lbl_home,  txt_date;
    public static List<DailySeriesModel> dailySeriesList = new ArrayList<>();
    public static DailySeriesAdapter dailySeriesAdapter;
    CardView cv_date;
    SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
    private  static Calendar c;
    private  static int mYear,mMonth,mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actity_daily_series);

        ///define id
        lv_dailyseries = findViewById(R.id.lv_dailyseries);
        lin_home = findViewById(R.id.lin_home);
        Iv_home = findViewById(R.id.Iv_home);
        lbl_home = findViewById(R.id.lbl_home);
        lin_dailyseries = findViewById(R.id.lin_dailyseries);

        cv_date = findViewById(R.id.cv_date);
        lin_txt_date = findViewById(R.id.lin_txt_date);
        txt_date = findViewById(R.id.txt_date);
        lin_date = findViewById(R.id.lin_date);
        Iv_date = findViewById(R.id.Iv_date);

        ///set adapter
        dailySeriesAdapter= new DailySeriesAdapter(this, dailySeriesList);
        lv_dailyseries.setAdapter(dailySeriesAdapter);

        lin_dailyseries.setBackgroundResource(R.drawable.ic_selection);
        lin_home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Iv_home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });

        lbl_home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ///choose date

        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);

        txt_date.setText(thisDate);
        getDailySeries(this, thisDate);

        cv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(DailySeries.this,
                        R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        txt_date.setText(String.format("%02d", (dayOfMonth)) + "-" + String.format("%02d", ((monthOfYear + 1)))+ "-" + year);
                        String chooseDate = year + "-" + String.format("%02d", ((monthOfYear + 1))) + "-" +String.format("%02d", (dayOfMonth));
                        dailySeriesList.clear();
                        dailySeriesAdapter.notifyDataSetChanged();
                        getDailySeries(DailySeries.this, chooseDate);

                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DailySeries.this, Home.class);
        startActivity(intent);
        finish();
    }

}
