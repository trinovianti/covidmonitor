package com.example.covidapp.module.Home.Data;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.util.Log;
import android.view.animation.LinearInterpolator;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidapp.R;
import com.example.covidapp.model.Summary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static com.example.covidapp.module.Home.Home.detailConfirm;
import static com.example.covidapp.module.Home.Home.summaryAdapter;
import static com.example.covidapp.module.Home.Home.summaryList;
import static com.example.covidapp.util.Api.summaryApi;

public class DailySummary {
    private static ProgressDialog pDialogSummary;
    private static DecimalFormat df = new DecimalFormat("#.##");
    public static void getDailySummary(final Activity activity){
        pDialogSummary = new ProgressDialog(activity);
        pDialogSummary.setMessage("Please wait");
        pDialogSummary.show();
        pDialogSummary.setCancelable(false);

        JsonArrayRequest JSONArray = new JsonArrayRequest(Request.Method.GET,
                summaryApi, null, new Response.Listener<JSONArray>(){
            @Override    public void onResponse(JSONArray response) {
                Log.i("Response",String.valueOf(response));
                try {
                    Log.d("JsonArray",response.toString());
                    for(int i=0;i<response.length();i++){
                        JSONObject jresponse = response.getJSONObject(i);
                        Summary item = new Summary();
                        item.setTotalConfirmed(": "+jresponse.getString("totalConfirmed"));
                        double incidentRate = Double.valueOf(df.format(jresponse.getDouble("incidentRate")));

                        item.setIncidentRate(incidentRate+" %");
                        item.setReportDate(jresponse.getString("reportDate"));
                        if (jresponse.has("deaths")) {
                            JSONObject deaths= jresponse.getJSONObject("deaths");
                            item.setTotalDeath(": "+deaths.getString("total"));
                        }
                        summaryList.add(item);
                    }

                    summaryAdapter.notifyDataSetChanged();
                    pDialogSummary.dismiss();
                }catch (JSONException e) {
                    pDialogSummary.dismiss();

                }
            }
        }, new Response.ErrorListener() {
            @Override    public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                pDialogSummary.dismiss();

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
