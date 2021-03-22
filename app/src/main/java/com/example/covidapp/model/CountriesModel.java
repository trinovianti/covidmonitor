package com.example.covidapp.model;

public class CountriesModel {
    String provinceState, countryRegion, lastUpdate, confirmed, recovered, deaths, active, admin2, incidentRate;
    String lat, longi;
    public CountriesModel() {
    }
    public CountriesModel(String provinceState,String countryRegion, String lastUpdate, String lat, String longi, String confirmed,
                          String recovered, String deaths, String active, String admin2, String incidentRate) {

        this.provinceState = provinceState;
        this.countryRegion = countryRegion;
        this.lastUpdate = lastUpdate;
        this.lat = lat;
        this.longi = longi;
        this.confirmed = confirmed;
        this.recovered = recovered;
        this.recovered = recovered;
        this.active = active;
        this.admin2 = admin2;
        this.incidentRate = incidentRate;

    }

    public String getProvinceState() {
        return provinceState;
    }
    public void setProvinceState(String provinceState) {
        this.provinceState = provinceState;
    }

    public String getCountryRegion() {
        return countryRegion;
    }
    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }
    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getConfirm() {
        return confirmed;
    }
    public void setConfirm(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getRecovered() {
        return recovered;
    }
    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeath() {
        return deaths;
    }
    public void setDeath(String deaths) {
        this.deaths = deaths;
    }

    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }

    public String getAdmin() {
        return admin2;
    }
    public void setAdmin(String admin2) {
        this.admin2 = admin2;
    }

    public String getIncidentRate() {
        return incidentRate;
    }
    public void setIncidentRate(String incidentRate) {
        this.incidentRate = incidentRate;
    }


}
