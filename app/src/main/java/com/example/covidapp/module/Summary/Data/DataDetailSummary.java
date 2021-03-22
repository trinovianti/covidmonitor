package com.example.covidapp.module.Summary.Data;

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
import com.example.covidapp.model.DetailSummary;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import static com.example.covidapp.module.Home.Home.summaryList;
import static com.example.covidapp.module.Summary.SummaryDetail.summaryDetailAdapter;
import static com.example.covidapp.module.Summary.SummaryDetail.summaryDetailList;
import static com.example.covidapp.util.Api.summaryApi;

public class DataDetailSummary {
    private static ProgressDialog pDialogSummary;
    public static void getDetailSummary(final Activity activity){
        pDialogSummary = new ProgressDialog(activity);
        pDialogSummary.setMessage("Please wait");
        pDialogSummary.show();
        pDialogSummary.setCancelable(false);
        summaryDetailList.clear();
        summaryDetailAdapter.notifyDataSetChanged();

        JsonArrayRequest JSONArray = new JsonArrayRequest(Request.Method.GET,
                summaryApi, null, new Response.Listener<org.json.JSONArray>(){
            @Override    public void onResponse(JSONArray response) {
                Log.i("Response",String.valueOf(response));
                try {
                    Log.d("JsonArray",response.toString());
                    for(int i=0;i<response.length();i++){
                        JSONObject jresponse = response.getJSONObject(i);
                        DetailSummary item = new DetailSummary();

                        if (jresponse.has("confirmed")) {
                            JSONObject confirm= jresponse.getJSONObject("deaths");
                            item.setTotalConfirm(confirm.getString("total"));
                            item.setChinaConfirm(confirm.getString("china"));
                            item.setOutsideChinaConfirm(confirm.getString("outsideChina"));
                        }

                        if (jresponse.has("deaths")) {
                            JSONObject deaths= jresponse.getJSONObject("deaths");
                            item.setTotalDeath(deaths.getString("total"));
                            item.setChinaDeath(deaths.getString("china"));
                            item.setOutsideChinaDeath(deaths.getString("outsideChina"));
                        }


                        item.setIncidentRate(jresponse.getString("incidentRate"));
                        item.setReportDate(jresponse.getString("reportDate"));

                        summaryDetailList.add(item);
                    }

                    summaryDetailAdapter.notifyDataSetChanged();
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
