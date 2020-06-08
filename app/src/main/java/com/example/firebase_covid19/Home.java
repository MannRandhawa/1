package com.example.firebase_covid19;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class  Home extends AppCompatActivity implements CallMeBack, AdapterView.OnItemSelectedListener {

    Button logOutButton;
    FirebaseAuth myFirebaseAuth;
    private FirebaseAuth.AuthStateListener myAuthStateListener;
    TextView totalCasesText;
    String selectedItemWithSpinner;
    TextView countryNameText;
    Spinner spinner;
    TextView dailyIncrementText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        logOutButton = findViewById(R.id.Logout);
        totalCasesText = findViewById(R.id.totalCases);
        countryNameText = findViewById(R.id.countryName);
        dailyIncrementText = findViewById(R.id.dailyIncrement);

        Intent my =getIntent();
        selectedItemWithSpinner = my.getStringExtra("country");

        spinner = (Spinner) findViewById(R.id.spinnerCountryName);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array,
                R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        spinner.setAdapter(adapter);
         spinner.setOnItemSelectedListener(this);


        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(Home.this, Login.class);
                startActivity(intToMain);
            }
        });

        String url = "https://coronavirus-19-api.herokuapp.com/countries/"+selectedItemWithSpinner;
        JsonRetriever.RetrieveFromURL(this, url, this);

    }


    @Override
    public void CallThis(String jsonText) {
        try {
                JSONObject json = new JSONObject(jsonText);
                    String cases = json.getString("cases");
                    String countryName = json.getString("country");
                    String incrementByDay = json.getString("todayCases");
                    totalCasesText.setText(cases.toString());
                    countryNameText.setText(countryName.toString());
                    dailyIncrementText.setText("Daily Increment: +"+incrementByDay.toString());


        } catch (JSONException e) {
            e.printStackTrace();

        }
    }


    public void StatisticsHome(View buttonGotPressed) {
        startActivity(getIntent());
    }

    public void CountriesList(View mv){
        Intent AllCountries = new Intent(this, AllCountries.class);
        startActivity(AllCountries);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
        Intent samePage = new Intent(this, Home.class);
        String name = parent.getItemAtPosition(position).toString();
        if (position > 0) {
            selectedItemWithSpinner = name;
            samePage.putExtra("country", selectedItemWithSpinner);
            startActivity(samePage);
        }

        }

    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void goToListPage(View v){
        Intent listPage = new Intent(this,AllCountries.class);
        startActivity(listPage);
    }
}

