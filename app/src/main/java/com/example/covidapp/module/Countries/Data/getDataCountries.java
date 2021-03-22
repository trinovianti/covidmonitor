package com.example.covidapp.module.Countries.Data;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidapp.model.CountriesModel;
import com.example.covidapp.model.DetailSummary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.covidapp.module.Countries.Countries.countriesAdapter;
import static com.example.covidapp.module.Countries.Countries.countrieList;


public class getDataCountries {
    private static ProgressDialog pDialogCountries;
    public static void getCountries(Activity activity, String url){
        pDialogCountries = new ProgressDialog(activity);
        pDialogCountries.setMessage("Please wait");
        pDialogCountries.show();
        pDialogCountries.setCancelable(false);

        countrieList.clear();
        countriesAdapter.notifyDataSetChanged();

        JsonArrayRequest JSONArray = new JsonArrayRequest(Request.Method.GET,
                url, null, new Response.Listener<org.json.JSONArray>(){
            @Override    public void onResponse(org.json.JSONArray response) {
                Log.i("Response",String.valueOf(response));
                try {
                    Log.d("JsonArray",response.toString());
                    for(int i=0;i<response.length();i++){
                        JSONObject jresponse = response.getJSONObject(i);
                        CountriesModel item = new CountriesModel();

                        item.setProvinceState(jresponse.getString("countryRegion"));
                        item.setLastUpdate(jresponse.getString("lastUpdate"));
                        item.setLat(jresponse.getString("lat"));
                        item.setLongi(jresponse.getString("long"));
                        item.setConfirm(jresponse.getString("confirmed"));
                        item.setRecovered(jresponse.getString("recovered"));
                        item.setDeath(jresponse.getString("deaths"));
                        item.setActive(jresponse.getString("active"));
                        item.setAdmin(jresponse.getString("admin2"));
                        item.setIncidentRate(jresponse.getString("incidentRate"));

                        countrieList.add(item);
                    }

                    countriesAdapter.notifyDataSetChanged();
                    pDialogCountries.dismiss();
                }catch (JSONException e) {
                    pDialogCountries.dismiss();

                }
            }
        }, new Response.ErrorListener() {
            @Override    public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                pDialogCountries.dismiss();

            }
        }){
            @Override    public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(activity.getApplicationContext());
        requestQueue.add(JSONArray);
    }
}
