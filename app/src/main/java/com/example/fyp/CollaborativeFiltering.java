package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.PrintStreamPrinter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CollaborativeFiltering extends AppCompatActivity {


    String imageURL;
    String CarManufacturer;
    String CarModel;
    String CarTrim;
    String CarPrice;
    String CarTopSpeed;
    String ZeroToSixty;
    String NumberOfDoors;
    String YearMade;

    TextView CarInformationTextView;

    String carIDString;

    //VARIABLES USED IN CALCULATION
    ArrayList<String> UserACarID = new ArrayList<String>();
    ArrayList<String> UserADesignRating = new ArrayList<String>();
    ArrayList<String> UserAPriceRating = new ArrayList<String>();
    ArrayList<String> UserATopSpeedRating = new ArrayList<String>();
    ArrayList<String> UserAAccelerationRating = new ArrayList<String>();
    ArrayList<String> UserASafetyRating = new ArrayList<String>();
    ArrayList<String> UserAHorsePowerRating = new ArrayList<String>();

    ArrayList<String> UserACarIDArrayList = new ArrayList<String>();
    ArrayList<String> UserADesignRatingArrayList = new ArrayList<String>();
    ArrayList<String> UserAPriceRatingArrayList = new ArrayList<String>();
    ArrayList<String> UserATopSpeedRatingArrayList = new ArrayList<String>();
    ArrayList<String> UserAAccelerationRatingArrayList = new ArrayList<String>();
    ArrayList<String> UserASafetyRatingArrayList = new ArrayList<String>();
    ArrayList<String> UserAHorsePowerRatingArrayList = new ArrayList<String>();

    ArrayList<String> UserBID = new ArrayList<String>();
    ArrayList<String> UserBCarID = new ArrayList<String>();
    ArrayList<String> UserBDesignRating = new ArrayList<String>();
    ArrayList<String> UserBPriceRating = new ArrayList<String>();
    ArrayList<String> UserBTopSpeedRating = new ArrayList<String>();
    ArrayList<String> UserBAccelerationRating = new ArrayList<String>();
    ArrayList<String> UserBSafetyRating = new ArrayList<String>();
    ArrayList<String> UserBHorsePowerRating = new ArrayList<String>();

    ArrayList<String> UserBIDArrayList = new ArrayList<String>();
    ArrayList<String> UserBCarIDArrayList = new ArrayList<String>();
    ArrayList<String> UserBDesignRatingArrayList = new ArrayList<String>();
    ArrayList<String> UserBPriceRatingArrayList = new ArrayList<String>();
    ArrayList<String> UserBTopSpeedRatingArrayList = new ArrayList<String>();
    ArrayList<String> UserBAccelerationRatingArrayList = new ArrayList<String>();
    ArrayList<String> UserBSafetyRatingArrayList = new ArrayList<String>();
    ArrayList<String> UserBHorsePowerRatingArrayList = new ArrayList<String>();

// END OF VARIABLES USED IN CALCULATION


    ArrayList UserBIDTest = new ArrayList<>();
    ArrayList UserBCarIDTest = new ArrayList<>();
    ArrayList UserBDesignRatingTest = new ArrayList<>();
    ArrayList UserBPriceRatingTest = new ArrayList<>();
    ArrayList UserBTopSpeedRatingTest = new ArrayList<>();
    ArrayList UserBAccelerationRatingTest = new ArrayList<>();
    ArrayList UserBSafetyRatingTest = new ArrayList<>();
    ArrayList UserBHorsePowerRatingTest = new ArrayList<>();


    ArrayList UserACarIDTest = new ArrayList<String>();
    ArrayList UserADesignRatingTest = new ArrayList<String>();
    ArrayList UserAPriceRatingTest = new ArrayList<String>();
    ArrayList UserATopSpeedRatingTest = new ArrayList<String>();
    ArrayList UserAAccelerationRatingTest = new ArrayList<String>();
    ArrayList UserASafetyRatingTest = new ArrayList<String>();
    ArrayList UserAHorsePowerRatingTest = new ArrayList<String>();


    ArrayList CarsAlreadySwipedByUserA = new ArrayList<String>();
    ArrayList CarsAlreadySwipedByUserATest = new ArrayList<String>();
    ArrayList CarsAlreadySwipedByUserATest2 = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaborative_filtering);


        String UserA = ParseUser.getCurrentUser().toString();

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("CollaborativeFilteringLog");
        query2.whereEqualTo("UserID", UserA);

        query2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {
                        TinyDB tinydb3 = new TinyDB(CollaborativeFiltering.this);
                        for(ParseObject object: objects){
                            CarsAlreadySwipedByUserA.add(object.getString("CarID"));

                            CarsAlreadySwipedByUserATest = new ArrayList<>(CarsAlreadySwipedByUserA);
                        }
                        tinydb3.putListString("CarsAlreadySwipedByUserATest", CarsAlreadySwipedByUserATest);
                    }
                }
            }
        });

        TinyDB tinydb3 = new TinyDB(CollaborativeFiltering.this);

       CarsAlreadySwipedByUserATest2 = tinydb3.getListString("CarsAlreadySwipedByUserATest");

        getNextCar();


        Button submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Submit();
            }
        });


        Button calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate();
            }
        });


    }


    public void getNextCar() {

        ImageView imageView = findViewById(R.id.imageView);
        CarInformationTextView = findViewById(R.id.CarInformationTextView);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("NewCars");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {

                    if (objects.size() > 0) {

                        for (ParseObject object : objects) {

                            if(!CarsAlreadySwipedByUserATest.contains(object.getObjectId())) {
                                imageURL = object.getString("ImageURL");
                                ImageView iv = findViewById(R.id.imageView);
                                Picasso.get().load(imageURL).into(iv);

                                carIDString = object.getObjectId();


                                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("CarobjectID", object.getObjectId());
                                editor.commit();


                                CarInformationTextView.setText("Car Manufacturer: " + object.getString("CarManufacturer") + " Car Model: " + object.getString("CarModel") + " CarTrim: " + object.getString("CarTrim") + " Car Price: " + object.getInt("Price") + " Car Top Speed: " + object.getInt("TopSpeed") + " Car ZeroToSixty: " + object.getInt("ZeroToSixty") + " Car Number Of Doors: " + object.getInt("NumberOfDoors") + "Car Year Made" + object.getInt("YearMade") + " Car Horse Power:" + object.getInt("Horsepower"));
                                break;
                            }
                        }

                    }


                }

            }
        });


        //  Log.d("ok", CarManufacturer);
        //Picasso.get().load(imageURL).into(imageView);
        // Log.d("ok", CarManufacturer);
        //  CarManufacturertextView.setText("ok");

        Spinner designSpinner = findViewById(R.id.designSpinner);
        String[] items = new String[]{"1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        designSpinner.setAdapter(adapter);

        Spinner PriceSpinner = findViewById(R.id.priceSpinner);
        String[] PriceItems = new String[]{"1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapterForPrice = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        PriceSpinner.setAdapter(adapter);


        Spinner TopSpeedSpinner = findViewById(R.id.topSpeedSpinner);
        String[] TopSpeedItems = new String[]{"1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapterForTopSpeed = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, TopSpeedItems);
        TopSpeedSpinner.setAdapter(adapter);

        Spinner AccelerationSpinner = findViewById(R.id.accelerationSpinner);
        String[] AccelerationItems = new String[]{"1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapterForAcceleration = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, TopSpeedItems);
        AccelerationSpinner.setAdapter(adapter);

        Spinner SafetySpinner = findViewById(R.id.safetySpinner);
        String[] SafetyItems = new String[]{"1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapaterForSafety = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, TopSpeedItems);
        SafetySpinner.setAdapter(adapter);

        Spinner HorsePowerSpinner = findViewById(R.id.horsePowerSpinner);
        String[] HorsePowerItems = new String[]{"1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapaterForHorsePower = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, TopSpeedItems);
        HorsePowerSpinner.setAdapter(adapter);


    }


    public void Submit() {

        Spinner designSpinner = (Spinner) findViewById(R.id.designSpinner);
        String designSpinnerText = designSpinner.getSelectedItem().toString();

        Spinner priceSpinner = (Spinner) findViewById(R.id.priceSpinner);
        String priceSpinnerText = priceSpinner.getSelectedItem().toString();

        Spinner topSpeedSpinner = (Spinner) findViewById(R.id.topSpeedSpinner);
        String topSpeedSpinnerText = topSpeedSpinner.getSelectedItem().toString();

        Spinner accelerationSpinner = (Spinner) findViewById(R.id.accelerationSpinner);
        String accelerationSpinnerText = accelerationSpinner.getSelectedItem().toString();

        Spinner safetySpinner = (Spinner) findViewById(R.id.safetySpinner);
        String safetySpinnerText = safetySpinner.getSelectedItem().toString();

        Spinner horsePowerSpinner = (Spinner) findViewById(R.id.horsePowerSpinner);
        String horsePowerSpinnerText = horsePowerSpinner.getSelectedItem().toString();


        ParseUser user = ParseUser.getCurrentUser();
        String userObjectID = user.getObjectId();

        ParseObject CollaborativeFilteringLog = new ParseObject("CollaborativeFilteringLog");


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        String CarID = pref.getString("CarobjectID", "");

        CarsAlreadySwipedByUserATest.add(CarID);

        CollaborativeFilteringLog.put("UserID", userObjectID);
        CollaborativeFilteringLog.put("CarID", CarID);
        CollaborativeFilteringLog.put("DesignRating", designSpinnerText);
        CollaborativeFilteringLog.put("PriceRating", priceSpinnerText);
        CollaborativeFilteringLog.put("TopSpeedRating", topSpeedSpinnerText);
        CollaborativeFilteringLog.put("AccelerationRating", accelerationSpinnerText);
        CollaborativeFilteringLog.put("SafetyRating", safetySpinnerText);
        CollaborativeFilteringLog.put("HorsePowerRating", horsePowerSpinnerText);
        CollaborativeFilteringLog.saveInBackground();

        getNextCar();
    }

    public void Calculate() {


        //USER A STUFF STARTS HERE
        ParseUser user = ParseUser.getCurrentUser();
        String userAObjectID = user.getObjectId();
        Log.d("255", userAObjectID);
//        UserACarID.clear();
//        UserADesignRating.clear();
//        UserAPriceRating.clear();
//        UserATopSpeedRating.clear();
//        UserAAccelerationRating.clear();
//        UserASafetyRating.clear();
//        UserAHorsePowerRating.clear();
//

        UserACarIDTest.clear();


        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("CollaborativeFilteringLog");
        query2.whereEqualTo("UserID", userAObjectID);

        query2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (objects.size() > 0) {
                    TinyDB tinydb2 = new TinyDB(CollaborativeFiltering.this);
                    for (ParseObject object : objects) {

                        UserACarID.add(object.getString("CarID"));
                        UserADesignRating.add(object.getString("DesignRating"));
                        UserAPriceRating.add(object.getString("PriceRating"));
                        UserATopSpeedRating.add(object.getString("TopSpeedRating"));
                        UserAAccelerationRating.add(object.getString("AccelerationRating"));
                        UserASafetyRating.add(object.getString("SafetyRating"));
                        UserAHorsePowerRating.add(object.getString("HorsePowerRating"));


                        UserACarIDTest = new ArrayList<>(UserACarID);
                        UserADesignRatingTest = new ArrayList<>(UserADesignRating);
                        UserAPriceRatingTest = new ArrayList<>(UserAPriceRating);
                        UserATopSpeedRatingTest = new ArrayList<>(UserATopSpeedRating);
                        UserAAccelerationRatingTest = new ArrayList<>(UserAAccelerationRating);
                        UserASafetyRatingTest = new ArrayList<>(UserASafetyRating);
                        UserAHorsePowerRatingTest = new ArrayList<>(UserAHorsePowerRating);


                    }
                    tinydb2.putListString("UserACarIDTest", UserACarIDTest);
                    tinydb2.putListString("UserADesignRatingTest", UserADesignRatingTest);
                    tinydb2.putListString("UserAPriceRatingTest", UserAPriceRatingTest);
                    tinydb2.putListString("UserATopSpeedRatingTest", UserATopSpeedRatingTest);
                    tinydb2.putListString("UserAAccelerationRatingTest", UserAAccelerationRatingTest);
                    tinydb2.putListString("UserASafetyRatingTest", UserASafetyRatingTest);
                    tinydb2.putListString("UserAHorsePowerRatingTest", UserAHorsePowerRatingTest);

                }


            }
        });
        TinyDB tinydb = new TinyDB(CollaborativeFiltering.this);

        ArrayList<String> UserACarID = tinydb.getListString("UserACarIDTest");
        ArrayList<String> UserADesignRating = tinydb.getListString("UserADesignRatingTest");
        ArrayList<String> UserAPriceRating = tinydb.getListString("UserAPriceRatingTest");
        ArrayList<String> UserATopSpeedRating = tinydb.getListString("UserATopSpeedRatingTest");
        ArrayList<String> UserAAccelerationRating = tinydb.getListString("UserAAccelerationRatingTest");
        ArrayList<String> UserASafetyRating = tinydb.getListString("UserASafetyRatingTest");
        ArrayList<String> UserAHorsePowerRating = tinydb.getListString("UserASafetyRatingTest");


        for (int i = 0; i < UserACarID.size(); i++) {
            Log.d("UserACarID", UserACarID.get(i));
            Log.d("UserADesignRating", UserADesignRating.get(i));
            Log.d("UserAPriceRating", UserAPriceRating.get(i));
            Log.d("UserATopSpeedRating", UserATopSpeedRating.get(i));
            Log.d("UserAAccelerationRating", UserAAccelerationRating.get(i));
            Log.d("UserASafetyRating", UserASafetyRating.get(i));
            Log.d("UserAHorsePowerRating", UserAHorsePowerRating.get(i));
        }


        //ABOVE WORKS AS INTENDED.

        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("CollaborativeFilteringLog");
        query1.whereNotEqualTo("UserID", userAObjectID);

        query1.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {
                        TinyDB tinydb2 = new TinyDB(CollaborativeFiltering.this);
                        for (ParseObject object : objects) {

                            UserBID.add(object.getString("UserID"));
                            UserBCarID.add(object.getString("CarID"));
                            UserBDesignRating.add(object.getString("DesignRating"));
                            UserBPriceRating.add(object.getString("PriceRating"));
                            UserBTopSpeedRating.add(object.getString("TopSpeedRating"));
                            UserBAccelerationRating.add(object.getString("AccelerationRating"));
                            UserBSafetyRating.add(object.getString("SafetyRating"));
                            UserBHorsePowerRating.add(object.getString("HorsePowerRating"));


                            UserBIDTest = new ArrayList<>(UserBID);
                            UserBCarIDTest = new ArrayList<>(UserBCarID);
                            UserBDesignRatingTest = new ArrayList<>(UserBDesignRating);
                            UserBPriceRatingTest = new ArrayList<>(UserBPriceRating);
                            UserBTopSpeedRatingTest = new ArrayList<>(UserBTopSpeedRating);
                            UserBAccelerationRatingTest = new ArrayList<>(UserBAccelerationRating);
                            UserBSafetyRatingTest = new ArrayList<>(UserBSafetyRating);
                            UserBHorsePowerRatingTest = new ArrayList<>(UserBHorsePowerRating);


                        }
                        tinydb2.putListString("IDUserB", UserBIDTest);
                        tinydb2.putListString("CarIDUserB", UserBCarIDTest);
                        tinydb2.putListString("DesignRatingUserB", UserBDesignRatingTest);
                        tinydb2.putListString("PriceRatingUserB", UserBPriceRatingTest);
                        tinydb2.putListString("TopSpeedRatingUserB", UserBTopSpeedRatingTest);
                        tinydb2.putListString("AccelerationRatingUserB", UserBAccelerationRatingTest);
                        tinydb2.putListString("SafetyRatingUserB", UserBSafetyRatingTest);
                        tinydb2.putListString("HorsePowerRatingUserB", UserBHorsePowerRatingTest);
                    }
                }
            }
        });

        TinyDB tinydb2 = new TinyDB(CollaborativeFiltering.this);

        ArrayList<String> UserBID = tinydb.getListString("IDUserB");
        ArrayList<String> UserBCarID = tinydb.getListString("CarIDUserB");
        ArrayList<String> UserBDesignRating = tinydb.getListString("DesignRatingUserB");
        ArrayList<String> UserBPriceRating = tinydb.getListString("PriceRatingUserB");
        ArrayList<String> UserBTopSpeedRating = tinydb.getListString("TopSpeedRatingUserB");
        ArrayList<String> UserBAccelerationRating = tinydb.getListString("AccelerationRatingUserB");
        ArrayList<String> UserBSafetyRating = tinydb.getListString("SafetyRatingUserB");
        ArrayList<String> UserBHorsePowerRating = tinydb.getListString("HorsePowerRatingUserB");


        Log.d("415", String.valueOf(UserBID.size()));

        for (int i = 0; i < UserBCarID.size(); i++) {
            Log.d("UserBID", UserBID.get(i));
            Log.d("UserBCarID", UserBCarID.get(i));
            Log.d("UserBDesignRating", UserBDesignRating.get(i));
            Log.d("UserBPriceRating", UserBPriceRating.get(i));
            Log.d("UserBTopSpeedRating", UserBTopSpeedRating.get(i));
            Log.d("UserBAccelerationRating", UserBAccelerationRating.get(i));
            Log.d("UserBSafetyRating", UserBSafetyRating.get(i));
            Log.d("UserBHorsePowerRating", UserBHorsePowerRating.get(i));
        }


        //ALGORITHM STUFF
        this.UserADesignRating = UserADesignRating;
        this.UserBDesignRating = UserBDesignRating;
        //YOU'LL NEED TO DO THIS FOR EVERY ATTRIBUTE LATER RAJ.


        HashMap<String, Integer> UserBFitnessesToUserA = new HashMap<String, Integer>();

        Set<String> UniqueUserBIDs = new HashSet<String>(UserBID);

        ArrayList UniqueUserBIDsArrayList = new ArrayList<String>(UniqueUserBIDs);

        int bestUserB = 1;
        String bestUserBString = "";
        int bestFitness = -1;

        for (int i = 0; i < UniqueUserBIDsArrayList.size(); i++) {
            int fitness = 0;
            for (int j = 0; j < UserBID.size(); j++) {//SPECIFIC USER B?
                Log.d("Speific user B Index", UserBID.get(j));
                if (UniqueUserBIDsArrayList.get(i).equals(UserBID.get(j))) {
                    Log.d("403", "403");
                    Log.d("404", UserBCarID.get(j));
                    String CurrentUserBCarID = UserBCarID.get(j);
                    int CurrentUserBCarIndex = j;
                    Log.d("406", "406");
                    if (UserBCarID.contains(CurrentUserBCarID)) {// So basically user A and user B have a car in common, but we don't know  where

                        Log.d("408", "408");
                        for (int k = 0; k < UserACarID.size(); k++) {
                            Log.d("410", "410");
                            if (UserACarID.get(k).equals(CurrentUserBCarID)) {// WE FOUND THE SPECIFIC INDEX WHERE THAT CAR IS STORED IN USER A ARRAYLIST
                                Log.d("412", "412");
                                String CurrentUserACarID = UserACarID.get(k);
                                int CurrentUserACarIndex = k;

                                fitness += Compare(j, k);
                                Log.d("418", String.valueOf(fitness));
                                if (fitness > bestFitness) {
                                    Log.d("419", "419");
                                    bestUserBString = UserBID.get(j);
                                    Log.d("421", bestUserBString);
                                    bestFitness = fitness;
                                    bestUserB = CurrentUserBCarIndex;//maybe this should be bestuserb = j
                                }
                            }
                        }

                    }


                }

            }

        }

        ArrayList<String> BestUserBCarIDsToRecommendToUserA = new ArrayList<String>();


        for (int i = 0; i < UserBID.size(); i++) {
            Log.d("487", "487");
            if (UserBID.contains(bestUserBString)) {
                Log.d("489", "489");
                if (!UserACarID.contains(UserBCarID.get(i))) {
                    Log.d("451", UserBCarID.get(i));
                    BestUserBCarIDsToRecommendToUserA.add(UserBCarID.get(i));
                }
            }
        }


        int BestFitness = -1;
        String BestUserBCarID = "";

        for (int i = 0; i < BestUserBCarIDsToRecommendToUserA.size(); i++) {
            Integer Fitness = 0;
            for (int j = 0; j < UserBCarID.size(); j++) {
                Fitness = Integer.valueOf(UserBDesignRating.get(j));
                Fitness += Integer.valueOf(UserBPriceRating.get(j));
                Fitness += Integer.valueOf(UserBTopSpeedRating.get(j));
                Fitness += Integer.valueOf(UserBAccelerationRating.get(j));
                Fitness += Integer.valueOf(UserBSafetyRating.get(j));
                Fitness += Integer.valueOf(UserBHorsePowerRating.get(j));

                Log.d("UserBDesignRating", String.valueOf(UserBDesignRating.get(j)));
                Log.d("UserBPriceRating", String.valueOf(UserBPriceRating.get(j)));
                Log.d("UserBTopSpeedRating", String.valueOf(UserBTopSpeedRating.get(j)));
                Log.d("UserBAccelerationRating", String.valueOf(UserBAccelerationRating.get(j)));
                Log.d("UserBSafetyRating", String.valueOf(UserBSafetyRating.get(j)));
                Log.d("UserBHorsePowerRating", String.valueOf(UserBHorsePowerRating.get(j)));

                if (Fitness > BestFitness) {
                    Fitness = BestFitness;
                    BestUserBCarID = UserBCarID.get(j);
                }


            }
        }

        Log.d("BestUserBCarID", BestUserBCarID);

        ImageView imageView = findViewById(R.id.imageView);
        CarInformationTextView = findViewById(R.id.CarInformationTextView);


        ParseQuery<ParseObject> query3 = ParseQuery.getQuery("NewCars");
        query3.whereEqualTo("objectId", BestUserBCarID);
        query3.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {

                    if (objects.size() > 0) {

                        for (ParseObject object : objects) {

                            imageURL = object.getString("ImageURL");
                            ImageView iv = findViewById(R.id.imageView);
                            Picasso.get().load(imageURL).into(iv);

                            carIDString = object.getObjectId();

                            CarInformationTextView.setText("Car Manufacturer: " + object.getString("CarManufacturer") + " Car Model: " + object.getString("CarModel") + " CarTrim: " + object.getString("CarTrim") + " Car Price: " + object.getInt("Price") + " Car Top Speed: " + object.getInt("TopSpeed") + " Car ZeroToSixty: " + object.getInt("ZeroToSixty") + " Car Number Of Doors: " + object.getInt("NumberOfDoors") + "Car Year Made" + object.getInt("YearMade") + " Car Horse Power:" + object.getInt("Horsepower"));
                            break;
                        }

                    }


                }

            }
        });

//        Log.d("UserBDesignRating", UserBDesignRating.get(i));
//        Log.d("UserBPriceRating", UserBPriceRating.get(i));
//        Log.d("UserBTopSpeedRating", UserBTopSpeedRating.get(i));
//        Log.d("UserBAccelerationRating", UserBAccelerationRating.get(i));
//        Log.d("UserBSafetyRating", UserBSafetyRating.get(i));
//        Log.d("UserBHorsePowerRating", UserBHorsePowerRating.get(i));


        /*
        ArrayList<String> BestUserBCars = new ArrayList<String>();
        ArrayList<String> BestUserBCarsNotYetRecommendedToUserA = new ArrayList<String>();
        for(int i =0; i < UserBID.size(); i++){
            Log.d("456","456");
            if(UserBID.get(i).equals(bestUserBString)){
                Log.d("458",bestUserBString);
                for(int k = 0; k < UserACarID.size(); k++){
                    Log.d("460","460");
                    Log.d("461", UserACarID.get(k));
                    Log.d("462", UserBCarID.get(i));
                    // if(!UserBCarIDArrayList.contains(UserACarIDArrayList.get(k))){
                    if(UserACarID.contains(UserBCarID.get(k))) {
                        Log.d("464", "464");
                        BestUserBCarsNotYetRecommendedToUserA.add(UserACarID.get(k));
                    }
                    //}
                }
            }
        }

        for(int i = 0; i < BestUserBCarsNotYetRecommendedToUserA.size(); i++){
            Log.d("Line 508", BestUserBCarsNotYetRecommendedToUserA.get(i));
        }
         */

//        ParseQuery<ParseObject> query3 = ParseQuery.getQuery("CollaborativeFilteringLog");
//        query3.whereEqualTo("CarID")
//        query3.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//
//            }
//        });


    }


    public int Compare(int userBCarIndex, int userACarIndex) {


        int UserADesignRatingInt = Integer.valueOf(UserADesignRating.get(userACarIndex));


        int UserBDesignRatingInt = Integer.valueOf(UserBDesignRating.get(userBCarIndex));

        if (UserADesignRatingInt - UserBDesignRatingInt == 0) {
            return 5;
        }
        if (UserADesignRatingInt - UserBDesignRatingInt == 1) {
            return 4;
        }
        if (UserADesignRatingInt - UserBDesignRatingInt == 2) {
            return 3;
        }
        if (UserADesignRatingInt - UserBDesignRatingInt == 3) {
            return 2;
        }
        if (UserADesignRatingInt - UserBDesignRatingInt == 5) {
            return 1;
        }
        return 0;

    }


}


