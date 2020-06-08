package com.example.firebase_covid19;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class JsonRetriever {
    public static void RetrieveFromURL(final CallMeBack whoToCall, String url)
    {
        RetrieveFromURL((Context)whoToCall, url, whoToCall);
    }

    public static void RetrieveFromURL(Context context, String url,final CallMeBack whoToCall)
    {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String ss) {
                whoToCall.CallThis(ss);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Log.e("ERROR", volleyError.networkResponse.toString());

            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(context);
        rQueue.add(request);
    }
}
