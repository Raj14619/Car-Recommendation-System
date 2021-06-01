package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.DeleteCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Button goToCollaborativeFilteringPageButton = findViewById(R.id.goToCollaborativeFilteringPageButton);
        Button logoutButton = findViewById(R.id.logoutButton);
        Button deleteUserButton = findViewById(R.id.deleteUserButton);
        Button updateUserInformationButton = findViewById(R.id.updateUserInformationButton);
        Button contentBasedCarRecommendationButton = findViewById(R.id.ContentBasedCarRecommendationbutton);
        Button swipeCarsButton = findViewById(R.id.swipecarsButton);

        goToCollaborativeFilteringPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCarCollaborativeFilteringPage();
            }
        });

        contentBasedCarRecommendationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToContentBasedCarRecommendationPage();
            }
        });


        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });


        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
            }
        });


        updateUserInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserInformation();
            }
        });

        swipeCarsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSwipeCars();
            }
        });


    }

    public void gotoSwipeCars(){

        Intent myIntent = new Intent(getBaseContext(), Swipe.class);
        startActivity(myIntent);


    }

    public void goToContentBasedCarRecommendationPage(){

        Intent myIntent = new Intent(getBaseContext(), ContentBasedFiltering.class);
        startActivity(myIntent);

    }


    public void goToCarCollaborativeFilteringPage(){


        Intent myIntent = new Intent(getBaseContext(), CollaborativeFiltering.class);
        startActivity(myIntent);


    }


    public void logoutUser(){

        ParseUser.logOut();
        Intent myIntent = new Intent(getBaseContext(), Login.class);
        startActivity(myIntent);


    }


    public void deleteUser(){

            ParseUser user = new ParseUser();

            user = user.getCurrentUser();

            user.deleteInBackground(new DeleteCallback() {
                @Override
                public void done(ParseException e) {
                    if(e == null){
                        Log.i("Signup", "Successful");
                    }
                    else{
                        Toast.makeText(Homepage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            Intent myIntent = new Intent(getBaseContext(), Login.class);
            startActivity(myIntent);


    }

    public void updateUserInformation(){


        Intent myIntent = new Intent(this, UpdateUserInformation.class);
        startActivity(myIntent);


    }







}
