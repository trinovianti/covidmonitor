package com.example.covidapp.module.DailySeries.Data;

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
import com.example.covidapp.model.DailySeriesModel;
import com.example.covidapp.model.DetailSummary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.covidapp.module.DailySeries.DailySeries.dailySeriesAdapter;

import static com.example.covidapp.module.DailySeries.DailySeries.dailySeriesList;
import static com.example.covidapp.util.Api.summaryApi;

public class getDataDailySeries {
    private static ProgressDialog pDialogSummary;

    public static void getDailySeries(Activity activity, String date) {
        pDialogSummary = new ProgressDialog(activity);
        pDialogSummary.setMessage("Please wait");
        pDialogSummary.show();
        pDialogSummary.setCancelable(false);
        dailySeriesList.clear();
        dailySeriesAdapter.notifyDataSetChanged();

        JsonArrayRequest JSONArray = new JsonArrayRequest(Request.Method.GET,
                summaryApi+"/"+date, null, new Response.Listener<org.json.JSONArray>(){
            @Override    public void onResponse(org.json.JSONArray response) {
                Log.i("Response",String.valueOf(response));
                try {
                    Log.d("JsonArray",response.toString());
                    for(int i=0;i<response.length();i++){
                        JSONObject jresponse = response.getJSONObject(i);
                        DailySeriesModel item = new DailySeriesModel();

                        item.setProvince(jresponse.getString("provinceState"));
                        item.setRegion(jresponse.getString("countryRegion"));
                        item.setReportDate(jresponse.getString("lastUpdate"));
                        item.setTotalConfirm(jresponse.getString("confirmed"));
                        item.setTotalRecovered(jresponse.getString("recovered"));
                        item.setTotalDethd(jresponse.getString("deaths"));

                        dailySeriesList.add(item);
                    }

                    dailySeriesAdapter.notifyDataSetChanged();
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
