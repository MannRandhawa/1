package com.example.firebase_covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText loginEmailText,loginPasswordText;
    Button loginButton;
    TextView notRegisterText;
    FirebaseAuth myFireBaseAuth;
    private FirebaseAuth.AuthStateListener myAuthSateListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        myFireBaseAuth = FirebaseAuth.getInstance();
        loginEmailText = findViewById(R.id.loginEmail);
        loginPasswordText = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.buttonForLogin);
        notRegisterText = findViewById(R.id.notRegisterSignUpText);



        myAuthSateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser myFirebaseUser = myFireBaseAuth.getCurrentUser();
                if (myFirebaseUser != null) {
                    Toast.makeText(Login.this, "You Are Logged In", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, Home.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Login.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String emailId = loginEmailText.getText().toString();
                String passwordCheck = loginPasswordText.getText().toString();
                if (emailId.isEmpty()) {
                    loginEmailText.setError("Please Enter a valid Email");
                    loginEmailText.requestFocus();
                } else if (passwordCheck.isEmpty()) {
                    loginPasswordText.setError("Please Enter Your Password");
                    loginPasswordText.requestFocus();
                } else if (emailId.isEmpty() && passwordCheck.isEmpty()) {
                    Toast.makeText(Login.this, "Fields Are Empty!", Toast.LENGTH_SHORT);
                } else if (!(emailId.isEmpty() && passwordCheck.isEmpty())) {
                    myFireBaseAuth.signInWithEmailAndPassword(emailId, passwordCheck).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Login.this, "Login Error, Plaese Login Again", Toast.LENGTH_SHORT);
                            } else {
                                Intent intoHome = new Intent(Login.this, Home.class);
                                startActivity(intoHome);
                            }
                        }
                    });
                } else {
                    Toast.makeText(Login.this, "Error Occured", Toast.LENGTH_SHORT);
                }
            }
        });
    }
    public void onClick(View v) {
        Intent intSignUp = new Intent(Login.this,MainActivity.class);
        startActivity(intSignUp);
    }


    protected void onStart(){
        super.onStart();
        myFireBaseAuth.addAuthStateListener(myAuthSateListner);
    }
}
