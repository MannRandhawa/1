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

public class MainActivity extends AppCompatActivity {
    EditText emailEditText,passwordEditText,confirmPasswordEditText;
    Button registerButton;
    TextView textForRegister;
    FirebaseAuth myFireBaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myFireBaseAuth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.emailText);
        passwordEditText = findViewById(R.id.passwordText);
        registerButton = findViewById(R.id.registerButton);
        textForRegister = findViewById(R.id.alredayHaveAccountText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordText);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailId = emailEditText.getText().toString();
                String passwordCheck = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                if(emailId.isEmpty()){
                    emailEditText.setError("Please Enter a valid Email");
                    emailEditText.requestFocus();
                }
                else if(passwordCheck.isEmpty()){
                    passwordEditText.setError("Please Enter Your Password");
                    passwordEditText.requestFocus();
                }
                else if(confirmPassword.isEmpty()){
                    confirmPasswordEditText.setError("Please Enter Your Password");
                    confirmPasswordEditText.requestFocus();
                }
                else if(!passwordCheck.equals(confirmPassword)){
                    confirmPasswordEditText.setError("Password Does Not Match");
                }
                else if (emailId.isEmpty() && passwordCheck.isEmpty() && confirmPassword.isEmpty()){
                    Toast.makeText(MainActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT);
                }

                else if (!(emailId.isEmpty() && passwordCheck.isEmpty() && confirmPassword.isEmpty())){
                    myFireBaseAuth.createUserWithEmailAndPassword(emailId,passwordCheck).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"SignUp Unsuccessful,Plaese Try again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(MainActivity.this,Login.class));
                            }
                        }

                    });
                }
                else {
                    Toast.makeText(MainActivity.this,"Error Occured",Toast.LENGTH_SHORT).show();
                }
            }
        });
        textForRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Login.class);
                startActivity(i);
            }
        });
    }
}