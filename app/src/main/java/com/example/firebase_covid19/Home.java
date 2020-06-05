package com.example.firebase_covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    Button logOutButton;
    FirebaseAuth myFirebaseAuth;
    private FirebaseAuth.AuthStateListener myAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        logOutButton = findViewById(R.id.Logout);


        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(Home.this, Login.class);
                startActivity(intToMain);
            }
        });
    }
    public void next(View buttonGotPressed){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}