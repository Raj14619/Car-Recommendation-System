package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("JenAX8LJ9Hj1RfOjEn8shFMJDPpN0ciRnyFGzmgY")
                .clientKey("q1jyZMlx40SElbcVGzxqBHYJjRhpONZhbZy9EMpy")
                .server("https://parseapi.back4app.com")
                .build()
        );

//BELOW IS ORIGINAL UNCOMMENT THE BELOW CODE AND SET ORIGINAL INTENT AS REGISTER CLASS
//        Intent myIntent = new Intent(getBaseContext(), Register.class);
//        startActivity(myIntent);


      // ParseUser.getCurrentUser().logOut();

        Intent myIntent = new Intent(getBaseContext(), Login.class);
        startActivity(myIntent);



    }
}
