package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends AppCompatActivity {

    Button registerButton;
    Button loginButton;

    EditText emailEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        loginButton = findViewById(R.id.backButton);
        registerButton = findViewById(R.id.updateInformationButon);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


    }

    public void loginUser(){


        EditText emailEditText =  findViewById(R.id.emailEditText);
        String email = emailEditText.getText().toString();

        EditText passwordEditText =  findViewById(R.id.passwordEditText);
        String password = passwordEditText.getText().toString();


        if (email.matches("") || password.matches("")) {

            Toast.makeText(this, "A username and password are required.", Toast.LENGTH_SHORT).show();

        }


        ParseUser.logInInBackground(email, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

                if(user != null){
                    Log.i("Login", "login was successful");
                    Intent GoToHomePage = new Intent(getBaseContext(), Homepage.class);
                    startActivity(GoToHomePage);

                }
                else{
                  Toast.makeText(Login.this, "Login was unsuccessful please try again", Toast.LENGTH_LONG).show();
                }


            }
        });




    }



    public void registerUser(){

        Intent GoToRegisterPage = new Intent(getBaseContext(), Register.class);
        startActivity(GoToRegisterPage);


    }



}
