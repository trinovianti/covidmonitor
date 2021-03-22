package com.example.covidapp.model;

public class Summary {
    private String totalConfirmed, totalDeath, reportDate, incidentRate;
    public Summary() {
    }
    public Summary(String totalConfirmed, String totalDeath, String reportDate, String incidentRate) {
        this.totalConfirmed = totalConfirmed;
        this.totalDeath = totalDeath;
        this.reportDate = reportDate;
        this.incidentRate = incidentRate;
    }
    public String getTotalConfirm() {
        return totalConfirmed;
    }
    public void setTotalConfirmed(String totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }
    public String getTotalDeath() {
        return totalDeath;
    }
    public void setTotalDeath(String totalDeath) {
        this.totalDeath = totalDeath;
    }
    public String getReportDate() {
        return reportDate;
    }
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
    public String getIncidentRate() {
        return incidentRate;
    }
    public void setIncidentRate(String incidentRate) {
        this.incidentRate = incidentRate;
    }
}
