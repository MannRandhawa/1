package com.example.firebase_covid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllCountries extends AppCompatActivity implements CallMeBack {
    RecyclerView recyclerView;
    String url,username, situation;
    TextView label;

    List<CustomData> myDataSet = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_countries);

        recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager LinearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LinearLayout);

      url = "https://coronavirus-19-api.herokuapp.com/countries";

        JsonRetriever.RetrieveFromURL(this, url, this); //First Param for Context, Last Param for Callback Function

        RecyclerViewCountryList myAdapter = new RecyclerViewCountryList(myDataSet);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void CallThis(String jsonText)
    {
        try
        {
            JSONArray json = new JSONArray(jsonText);

            for (int i = 1;i<json.length();i++) {
                JSONObject jsonObject = json.getJSONObject(i);
                jsonObject.getString("country");
                jsonObject.getString("cases");
                CustomData c1 = new CustomData(i+"."+jsonObject.getString("country"), jsonObject.getString("cases"));
                myDataSet.add(c1);

                RecyclerViewCountryList r1 = new RecyclerViewCountryList(myDataSet);
                recyclerView.setAdapter(r1);
            }
        }

        catch (JSONException e)
        {
            Log.e("not found",url);
        }
    }
}