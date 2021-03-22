package com.example.covidapp.module.Home;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapp.R;
import com.example.covidapp.model.SliderItem;
import com.example.covidapp.model.Summary;
import com.example.covidapp.module.Countries.Countries;
import com.example.covidapp.module.DailySeries.DailySeries;
import com.example.covidapp.module.Home.adapter.SliderAdapterExample;
import com.example.covidapp.module.Home.adapter.SummaryAdapter;
import com.example.covidapp.module.Summary.SummaryDetail;
import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import static com.example.covidapp.module.Home.Data.DailySummary.getDailySummary;
import static com.example.covidapp.module.Home.Data.PersentagePatient.getDataPercentage;
import static com.example.covidapp.util.Api.confirmedApi;
import static com.example.covidapp.util.Api.deathsApi;
import static com.example.covidapp.util.Api.image;
import static com.example.covidapp.util.Api.recoveredApi;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class Home extends AppCompatActivity {
    SliderView sliderView;
    private SliderAdapterExample adapter;
    RecyclerView recyclerView;
    TextView btn_seeall, lbl_dailyseries;
    LinearLayout lin_dailyseries, lin_home, lin_recovered, lin_confirm, lin_death;
    ImageView Iv_dailyseries;

    public static double percentageConfirm, percentageRecovered, percentageDeath=0;
    public static String detailConfirm, detailRecovered, detailDeath="";
    public static List<Summary> summaryList = new ArrayList<>();
    public static SummaryAdapter summaryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///define id
        sliderView = findViewById(R.id.imageSlider);
        recyclerView = findViewById(R.id.recyclerView);
        btn_seeall = findViewById(R.id.btn_seeall);

        lin_dailyseries = findViewById(R.id.lin_dailyseries);
        Iv_dailyseries = findViewById(R.id.Iv_dailyseries);
        lbl_dailyseries = findViewById(R.id.lbl_dailyseries);
        lin_home = findViewById(R.id.lin_home);

        lin_recovered = findViewById(R.id.lin_recovered);
        lin_confirm = findViewById(R.id.lin_confirm);
        lin_death = findViewById(R.id.lin_death);


        //setup
        setupSLideshow();
        setupSummary();
        ///get data
        getDataPercentage(this);
        getDailySummary(this);
        lin_home.setBackgroundResource(R.drawable.ic_selection);
        btn_seeall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, SummaryDetail.class);
                startActivity(intent);
                finish();
            }
        });

        lbl_dailyseries.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gotodailyseries();
            }
        });

        lin_dailyseries.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gotodailyseries();
            }
        });

        Iv_dailyseries.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                gotodailyseries();
            }
        });



        lin_recovered.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                gotocountries(recoveredApi, "Recovered Detail");
            }
        });

        lin_confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                gotocountries(confirmedApi, "Confirm Detail");
            }
        });

        lin_death.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                gotocountries(deathsApi, "Death Detail");
            }
        });
    }

    private void gotodailyseries(){
        Intent intent = new Intent(Home.this, DailySeries.class);
        startActivity(intent);
        finish();
    }

    private void gotocountries(String url, String header){
        Intent intent = new Intent(Home.this, Countries.class);
        intent.putExtra("sUrl", url);
        intent.putExtra("sHeader", header);

        startActivity(intent);
        finish();
    }

    private void setupSummary(){
        summaryAdapter = new SummaryAdapter(summaryList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(summaryAdapter);

    }

    private void setupSLideshow(){
        adapter = new SliderAdapterExample(this);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(R.color.light_green);
        sliderView.setIndicatorUnselectedColor(R.color.dark_grey);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
        sliderView.setInfiniteAdapterEnabled(true);

        addNewItem(this);
    }

    public void addNewItem(Activity view) {
        SliderItem sliderItem = new SliderItem();
        sliderItem.setDescription("");
        sliderItem.setImageUrl(image);
        adapter.addItem(sliderItem);
    }

    @Override
    public void onBackPressed() {
        BackMainMenu();
    }

    private void  BackMainMenu(){
        confLogout("Warning", "Are you sure want to logout from this application?");
    }

    private void confLogout(String title, String msg){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.message_box_yes_no);

        TextView txt_Title = (TextView) dialog.findViewById(R.id.titleMessage);
        TextView txt_Message = (TextView) dialog.findViewById(R.id.text_dialog);
        final Button dialogButtonYes = (Button) dialog.findViewById(R.id.btn_Yes);
        final Button btn_No = (Button) dialog.findViewById(R.id.btn_No);

        txt_Title.setText(title);
        txt_Message.setText(msg);

        dialogButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });

        btn_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.show();
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }


}