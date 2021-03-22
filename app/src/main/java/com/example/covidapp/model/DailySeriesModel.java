package com.example.covidapp.model;

public class DailySeriesModel {
    String reportDate, Province, TotalConfirm, Region, TotalRecovered, TotalDeth;

    public DailySeriesModel() {
    }
    public DailySeriesModel(String Province,String reportDate, String TotalConfirm, String Region,
                           String  TotalRecovered, String TotalDeth) {

        this.reportDate = reportDate;
        this.Province = Province;
        this.TotalConfirm = TotalConfirm;
        this.Region = Region;
        this.TotalRecovered = TotalRecovered;
        this.TotalDeth = TotalDeth;

    }

    public String getReportDate() {
        return reportDate;
    }
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
    public String getProvince() {
        return Province;
    }
    public void setProvince(String Province) {
        this.Province = Province;
    }

    public String getTotalConfirm() {
        return TotalConfirm;
    }
    public void setTotalConfirm(String TotalConfirm) {
        this.TotalConfirm = TotalConfirm;
    }

    public String getRegion() {
        return Region;
    }
    public void setRegion(String Region) {
        this.Region = Region;
    }

    public String getTotalRecovered() {
        return TotalRecovered;
    }
    public void setTotalRecovered(String TotalRecovered) {
        this.TotalRecovered = TotalRecovered;
    }

    public String getTotalDeth() {
        return TotalDeth;
    }
    public void setTotalDethd(String TotalDeth) {
        this.TotalDeth = TotalDeth;
    }

}

