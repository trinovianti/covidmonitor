package com.example.covidapp.module.Home.Data;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidapp.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import static com.example.covidapp.module.Home.Home.percentageConfirm;
import static com.example.covidapp.module.Home.Home.percentageRecovered;
import static com.example.covidapp.module.Home.Home.percentageDeath;
import static com.example.covidapp.module.Home.Home.detailConfirm;
import static com.example.covidapp.module.Home.Home.detailRecovered;
import static com.example.covidapp.module.Home.Home.detailDeath;

import static com.example.covidapp.module.Home.adapter.GraphAdapter.createTracks;
import static com.example.covidapp.util.Api.baseApi;

public class PersentagePatient {
    private static ProgressDialog pDialogCheckPercentage;

    public static void getDataPercentage(final Activity activity){
        pDialogCheckPercentage = new ProgressDialog(activity);
        pDialogCheckPercentage.setMessage("Please wait");
        pDialogCheckPercentage.show();
        pDialogCheckPercentage.setCancelable(false);
        String url = baseApi;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>(){
            @Override    public void onResponse(JSONObject response) {
                Log.i("Response",String.valueOf(response));
                double confirmValue=0.0;
                double recoveredValue=0.0;
                double deathsValue=0.0;
                double totaldata=0.0;
                try {

                    if (response.has("confirmed")) {
                        JSONObject confirmed= response.getJSONObject("confirmed");
                        confirmValue = confirmed.getDouble("value");
                        String confirmdetail = confirmed.getString("detail");
                        detailConfirm=confirmdetail;
                    }

                    if (response.has("recovered")) {
                        JSONObject recovered= response.getJSONObject("recovered");
                        recoveredValue = recovered.getDouble("value");
                        String recovereddetail = recovered.getString("detail");
                        detailRecovered =recovereddetail;
                    }

                    if (response.has("deaths")) {
                        JSONObject deaths= response.getJSONObject("deaths");
                        deathsValue = deaths.getDouble("value");
                        String deathdetail = deaths.getString("detail");
                        detailDeath=deathdetail;
                    }

                    totaldata=confirmValue+recoveredValue+deathsValue;
                    percentageConfirm=(confirmValue*100)/totaldata;
                    percentageRecovered=(recoveredValue*100)/totaldata;
                    percentageDeath= (deathsValue*100)/totaldata;

                    createTracks(activity, R.id.graph_recovered, new LinearInterpolator(), Color.parseColor("#58E143"), R.id.txt_percetage_recovered, (int)Math.round(percentageRecovered));
                    createTracks(activity, R.id.graph_confirm, new LinearInterpolator(), Color.parseColor("#FF9F0F"), R.id.txt_percetage_confirm, (int)Math.round(percentageConfirm));
                    createTracks(activity, R.id.graph_death, new LinearInterpolator(), Color.parseColor("#61A9FF"), R.id.txt_percetage_death, (int)Math.round(percentageDeath));
                    pDialogCheckPercentage.dismiss();
                }catch (JSONException e) {
                    pDialogCheckPercentage.dismiss();
                    failedgetData(activity);
                }
            }
        }, new Response.ErrorListener() {
            @Override    public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                pDialogCheckPercentage.dismiss();
                failedgetData(activity);
            }
        }){
            @Override    public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(activity.getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }


    private static void failedgetData(final Activity activity){
        createTracks(activity, R.id.graph_recovered, new LinearInterpolator(), Color.parseColor("#58E143"), R.id.txt_percetage_recovered, 0);
        createTracks(activity, R.id.graph_confirm, new LinearInterpolator(), Color.parseColor("#FF9F0F"), R.id.txt_percetage_confirm, 0);
        createTracks(activity, R.id.graph_death, new LinearInterpolator(), Color.parseColor("#61A9FF"), R.id.txt_percetage_death, 0);
    }
}
