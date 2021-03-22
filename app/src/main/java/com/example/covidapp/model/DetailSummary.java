package com.example.covidapp.model;

public class DetailSummary {
    private String reportDate, ChinaConfirm, ChinaDeath,OutsideChinaConfirm, incidentRate, OutsideChinaDeath,
            TotalConfirm, TotalDeath;
    public DetailSummary() {
    }
    public DetailSummary(String ChinaDeath, String ChinaConfirm,String OutsideChinaConfirm,
                         String OutsideChinaDeath, String reportDate, String incidentRate,
                         String TotalConfirm, String TotalDeath) {
        this.ChinaConfirm = ChinaConfirm;
        this.ChinaDeath = ChinaDeath;
        this.reportDate = reportDate;
        this.incidentRate = incidentRate;
        this.OutsideChinaConfirm = OutsideChinaConfirm;
        this.OutsideChinaDeath = OutsideChinaDeath;
        this.TotalConfirm = TotalConfirm;
        this.TotalDeath = TotalDeath;
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
    public String getChinaConfirm() {
        return ChinaConfirm;
    }
    public void setChinaConfirm(String ChinaConfirm) {
        this.ChinaConfirm = ChinaConfirm;
    }

    public String getChinaDeath() {
        return ChinaDeath;
    }
    public void setChinaDeath(String ChinaDeath) {
        this.ChinaDeath = ChinaDeath;
    }

    public String getOutsideChinaConfirm() {
        return OutsideChinaConfirm;
    }
    public void setOutsideChinaConfirm(String OutsideChinaConfirm) {
        this.OutsideChinaConfirm = OutsideChinaConfirm;
    }

    public String getOutsideChinaDeath() {
        return OutsideChinaDeath;
    }
    public void setOutsideChinaDeath(String OutsideChinaDeath) {
        this.OutsideChinaDeath = OutsideChinaDeath;
    }

    public String getTotalConfirm() {
        return TotalConfirm;
    }
    public void setTotalConfirm(String TotalConfirm) {
        this.TotalConfirm = TotalConfirm;
    }

    public String getTotalDeath() {
        return TotalDeath;
    }
    public void setTotalDeath(String TotalDeath) {
        this.TotalDeath = TotalDeath;
    }
}
