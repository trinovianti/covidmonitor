package com.example.covidapp.util;

public class Api {
    
    /// Base api url
    public static  String baseApi = "https://covid19.mathdro.id/api";

    /// Api list detail confirmed
    public static  String confirmedApi = baseApi+"/confirmed";

    /// Api list detail recovered
    public static  String recoveredApi = baseApi+"/recovered";

    /// Api list detail deaths
    public static  String deathsApi = baseApi+"/deaths";

    /// Api list detail summary
    public static  String summaryApi = baseApi+"/daily";

    ///API image banner
    public static  String image = baseApi+"/og";

}
