package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.icu.text.Edits;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

//PROBLEMS OCCUR BECAUSE YOU NEED GLOBAL VARIABLES RAJ WHEN STORING THE OBJECT.GETSTRING("CAR") ETC.

public class Swipe extends AppCompatActivity {

    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private int ivariableintemplate = 0;




    String CarObjectID;
    String CarManufacturer;
    String CarModel;
    String CarTrim;
    double CarPrice;
    double CarTopSpeed;
    double CarZeroToSixty;
    int CarNumberOfDoors;
    int CarYearMade;
    int CarHorsePower;
    Boolean CarisElectric;

    boolean SwipedRight;
    String UserObjectID;

    ArrayList<String> CarObjectIDListRight = new ArrayList<String>();
    ArrayList<String> CarManufacturerListRight = new ArrayList<String>();
    ArrayList<String> CarModelListRight = new ArrayList<String>();
    ArrayList<String> CarTrimListRight = new ArrayList<String>();
    ArrayList<Double> CarPriceListRight = new ArrayList<Double>();
    ArrayList<Double> CarTopSpeedListRight = new ArrayList<Double>();
    ArrayList<Double> CarZeroToSixtyListRight = new ArrayList<Double>();
    ArrayList<Integer> CarNumberOfDoorsListRight = new ArrayList<Integer>();
    ArrayList<Integer> CarYearMadeListRight = new ArrayList<Integer>();
    ArrayList<Integer> CarHorsePowerListRight = new ArrayList<Integer>();
    ArrayList<Boolean> CarisElectricListRight = new ArrayList<Boolean>();

    ArrayList<String> CarObjectIDListLeft = new ArrayList<String>();
    ArrayList<String> CarManufacturerListLeft = new ArrayList<String>();
    ArrayList<String> CarModelListLeft = new ArrayList<String>();
    ArrayList<String> CarTrimListLeft = new ArrayList<String>();
    ArrayList<Double> CarPriceListLeft = new ArrayList<Double>();
    ArrayList<Double> CarTopSpeedListLeft = new ArrayList<Double>();
    ArrayList<Double> CarZeroToSixtyListLeft = new ArrayList<Double>();
    ArrayList<Integer> CarNumberOfDoorsListLeft = new ArrayList<Integer>();
    ArrayList<Integer> CarYearMadeListLeft = new ArrayList<Integer>();
    ArrayList<Integer> CarHorsePowerListLeft = new ArrayList<Integer>();
    ArrayList<Boolean> CarisElectricListLeft = new ArrayList<Boolean>();


    Map<String, Integer> CarManufacturerAndIndexRight = new HashMap<>();
    Map<String, Integer> CarManufacturerAndIndexLeft= new HashMap<>();
    Map<String, Integer> CarManufacturerAndIndexAVG= new HashMap<>();


    Map<String, Integer> CarModelAndIndexRight = new HashMap<>();
    Map<String, Integer> CarModelAndIndexLeft= new HashMap<>();
    Map<String, Integer> CarModelAndIndexAVG= new HashMap<>();

    Map<String, Integer> CarTrimAndIndexRight = new HashMap<>();
    Map<String, Integer> CarTrimAndIndexLeft= new HashMap<>();
    Map<String, Integer> CarTrimAndIndexAVG= new HashMap<>();

    Map<Double, Integer> CarTopSpeedAndIndexRight = new HashMap<>();
    Map<Double, Integer> CarTopSpeedAndIndexLeft= new HashMap<>();
    Map<Double, Integer> CarTopSpeedAndIndexAVG= new HashMap<>();

    Map<Integer, Integer> CarNumberOfDoorsAndIndexRight = new HashMap<>();
    Map<Integer, Integer> CarNumberOfDoorsAndIndexLeft= new HashMap<>();
    Map<Integer, Integer> CarNumberOfDoorsAndIndexAVG= new HashMap<>();

    Map<Integer, Integer> CarYearMadeAndIndexRight = new HashMap<>();
    Map<Integer, Integer> CarYearMadeAndIndexLeft= new HashMap<>();
    Map<Integer, Integer> CarYearMadeAndIndexAVG= new HashMap<>();




    ArrayList<String> CarManufacturerLeftAndRight = new ArrayList<>();
    ArrayList<String> CarModelLeftAndRight = new ArrayList<>();
    ArrayList<String> CarTrimLeftAndRight = new ArrayList<>();
    ArrayList<Double> CarTopSpeedLeftAndRight = new ArrayList<>();
    ArrayList<String> CarImageURLLeftAndRight = new ArrayList<>();
    ArrayList<Integer> CarNumberOfDoorsLeftAndRight = new ArrayList<>();
    ArrayList<Integer> CarYearMadeListLeftAndRight = new ArrayList<>();


    ArrayList<String> ObjectIDSpecificNumbersWeHaveAlreadyWentThrough = new ArrayList<String>();

    String ImageURl;

    //
    ArrayList<String> UserAcarsSwiped = new ArrayList<String>();
    boolean didwehitforloop = false;

    ArrayList<String> UserACarObjectIDsSwiped = new ArrayList<String>();
    ArrayList<Boolean> UserACarObjectIDsSwipedLeftOrRight = new ArrayList<Boolean>();

    HashSet<String> UserBObjectIDs = new HashSet<String>();
    ArrayList<String>  UserBCarObjctIDsSwiped = new ArrayList<String>();
    ArrayList<String> UserBCarObjectIDsSwipedLeftOrRight = new ArrayList<String>();
    ArrayList<String> UserBObjectIDsArrayList = new ArrayList<String>();
    ArrayList<String> CarsNotRecommendedYetToUserA = new ArrayList<String>();

    int fitness = 0;
    int bestfitness = 0;
    int fitnessTheSame = 0;
    int fitnessNotTheSame =0;
    String bestUserObjectID;
    int bestfitnessTheSame = 0;
    int bestFitnessNotTheSame = 0;

    int count = 0;
    double output =0;

    String CarAlreadySwiped = "";

    ArrayList<CarManufacturer> cars = new ArrayList<CarManufacturer>();

    private  static final String FILE_NAME = "example.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        UserObjectID = ParseUser.getCurrentUser().getObjectId();

        al = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserCarSwipes");
        query.whereEqualTo("UserObjectID",UserObjectID);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for(ParseObject object: objects){
                        CarAlreadySwiped=object.getString("CarObjectID");
                        AddCarAlreadySwipedToSpecificCarObjectIDArrayList(CarAlreadySwiped);
                    }
                } else {
                    // error
                }
            }
        });




        getNextCarInformation();



        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations();
            }
        });


        arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.helloText, al );

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);



        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(Swipe.this, "Left", Toast.LENGTH_SHORT).show();

                //STARTED HERE RAJ


                SwipedRight = false;

                StoreValuesToLeftArrayList(CarObjectID, CarManufacturer, CarModel, CarTrim, CarPrice, CarTopSpeed, CarZeroToSixty, CarNumberOfDoors, CarYearMade, CarHorsePower, CarisElectric, ImageURl);

                //ADD USER INFO TO USERCARSWIPES IN PARSE
                ParseObject sc = new ParseObject("UserCarSwipes");
                sc.put("UserObjectID",UserObjectID);
                sc.put("CarObjectID",CarObjectID);
                sc.put("SwipedRight",SwipedRight);
                sc.saveInBackground();
                //END OF ADDING USERS TO USERCARSWIPES IN PARSE

                //UPDATE CURRENT ARRAY WHICH CONTAINS INFORMATION ABOUT CARS WHICH ARE SWIPED
                ObjectIDSpecificNumbersWeHaveAlreadyWentThrough.add(CarObjectID);
                getNextCarInformation();




            }


            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(Swipe.this, "Right", Toast.LENGTH_SHORT).show();


                //STARTED HERE RAJ


                SwipedRight = true;

                StoreValuesToRightArrayList(CarObjectID, CarManufacturer, CarModel, CarTrim, CarPrice, CarTopSpeed, CarZeroToSixty, CarNumberOfDoors, CarYearMade, CarHorsePower, CarisElectric, ImageURl);

                //ADD USER INFO TO USERCARSWIPES IN PARSE
                ParseObject sc = new ParseObject("UserCarSwipes");
                sc.put("UserObjectID",UserObjectID);
                sc.put("CarObjectID",CarObjectID);
                sc.put("SwipedRight",SwipedRight);
                sc.saveInBackground();
                //END OF ADDING USERS TO USERCARSWIPES IN PARSE

                //UPDATE CURRENT ARRAY WHICH CONTAINS INFORMATION ABOUT CARS WHICH ARE SWIPED
                ObjectIDSpecificNumbersWeHaveAlreadyWentThrough.add(CarObjectID);
                getNextCarInformation();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
//                al.add("XML ".concat(String.valueOf(i)));
//                arrayAdapter.notifyDataSetChanged();
//                Log.d("LIST", "notified");
//                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(Swipe.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }// end of oncreate

    public void UpdateCarsAlreadySwipedByUser(ArrayList<String> ObjectIDSpecificNumbersWeHaveAlreadyWentThrough){

        this.ObjectIDSpecificNumbersWeHaveAlreadyWentThrough=ObjectIDSpecificNumbersWeHaveAlreadyWentThrough;


    }

    public void AddCarAlreadySwipedToSpecificCarObjectIDArrayList(String k){

        ObjectIDSpecificNumbersWeHaveAlreadyWentThrough.add(k);
        Log.d("334", String.valueOf(ObjectIDSpecificNumbersWeHaveAlreadyWentThrough.size()));

        for(int i = 0; i < ObjectIDSpecificNumbersWeHaveAlreadyWentThrough.size(); i++){
            Log.d("339", ObjectIDSpecificNumbersWeHaveAlreadyWentThrough.get(i));
        }
    }


    public void getNextCarInformation(){



        Boolean getUserBsCarAndRecommendItToUserA = ShallWeRecommendAnotherUsersCarToUserA();

        Log.d("318",String.valueOf(getUserBsCarAndRecommendItToUserA));


        Log.d("805,",String.valueOf(CarsNotRecommendedYetToUserA.size()));
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        Set<String> ok = pref.getStringSet("set", null);
        boolean ok2 = pref.getBoolean("set2", false);


        Log.d("1000000", String.valueOf(ok2));



            if(ok != null){
                for (String x : ok) {
                    CarsNotRecommendedYetToUserA.add(x);
                    Log.d("yeahyeah", x);
                }
            }





        if(getUserBsCarAndRecommendItToUserA == true){
            ParseQuery<ParseObject> query = ParseQuery.getQuery("NewCars");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {

                    if (e == null) {

                        if (objects.size() > 0) {

                            for (ParseObject object : objects) {
                                CarObjectID = object.getObjectId();
                                if (!(ObjectIDSpecificNumbersWeHaveAlreadyWentThrough.contains(CarObjectID)) && CarsNotRecommendedYetToUserA.contains(CarObjectID)) {
                                    Log.d("VICTORY","VICTORY");
                                    CarManufacturer = object.getString("CarManufacturer");
                                    CarModel = object.getString("CarModel");
                                    CarTrim = object.getString("CarTrim");
                                    CarPrice = object.getDouble("Price");
                                    CarTopSpeed = object.getDouble("TopSpeed");
                                    CarZeroToSixty = object.getDouble("ZeroToSixty");
                                    CarNumberOfDoors = object.getInt("NumberOfDoors");
                                    CarYearMade = object.getInt("YearMade");
                                    CarHorsePower = object.getInt("Horsepower");
                                    CarisElectric = object.getBoolean("isElectric");
                                    ImageURl = object.getString("ImageURL");
                                    ImageView iv = findViewById(R.id.imageView);
                                    Picasso.get().load(ImageURl).into(iv);
                                    CarObjectID = CarObjectID;
                                    al.add(CarManufacturer+" "+CarModel +" "+CarTrim);
                                    break;
                                }

                            }
                        }


                    }
                    arrayAdapter.notifyDataSetChanged();
                    CarObjectID = CarObjectID;

                }
            });
        }






        if(getUserBsCarAndRecommendItToUserA == false) {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("NewCars");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {

                    if (e == null) {

                        if (objects.size() > 0) {

                            for (ParseObject object : objects) {
                                CarObjectID = object.getObjectId();
                                if (!(ObjectIDSpecificNumbersWeHaveAlreadyWentThrough.contains(CarObjectID))) {

                                    CarManufacturer = object.getString("CarManufacturer");
                                    CarModel = object.getString("CarModel");
                                    CarTrim = object.getString("CarTrim");
                                    CarPrice = object.getDouble("Price");
                                    CarTopSpeed = object.getDouble("TopSpeed");
                                    CarZeroToSixty = object.getDouble("ZeroToSixty");
                                    CarNumberOfDoors = object.getInt("NumberOfDoors");
                                    CarYearMade = object.getInt("YearMade");
                                    CarHorsePower = object.getInt("Horsepower");
                                    CarisElectric = object.getBoolean("isElectric");
                                    ImageURl = object.getString("ImageURL");
                                    ImageView iv = findViewById(R.id.imageView);
                                    Picasso.get().load(ImageURl).into(iv);
                                    CarObjectID = CarObjectID;
                                    al.add(CarManufacturer+" "+CarModel +" "+CarTrim);
                                    Log.d("line 399",CarManufacturer+" "+CarModel +" "+CarTrim);

                                    break;
                                }

                            }
                        }


                    }


                    arrayAdapter.notifyDataSetChanged();
                    CarObjectID = CarObjectID;
//
                }
            });
        }


    }


    public boolean ShallWeRecommendAnotherUsersCarToUserA(){


        ParseUser user = ParseUser.getCurrentUser();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserCarSwipes");
        query.whereEqualTo("UserObjectID",user.getObjectId());

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {

                    if (objects.size() > 0) {

                        for (ParseObject object : objects) {
                            Log.d("439","439");
                            didwehitforloop = true;
                            UserACarObjectIDsSwiped.add(object.getString("CarObjectID"));
                            UserACarObjectIDsSwipedLeftOrRight.add(object.getBoolean("SwipedRight"));
                        }
                    }
                }
                didwehitforloop = didwehitforloop;
                UserACarObjectIDsSwiped = UserACarObjectIDsSwiped;
                UserACarObjectIDsSwipedLeftOrRight = UserACarObjectIDsSwipedLeftOrRight;
                method2();
            }
        });

//        if(didwehitforloop = false){
//            Log.d("452","452");
//            return false;
//        }



        //START OF 3RD PART





        //part 4


//        if (output >= 0.5){
//            Log.d("562","562");
//            return  true;
//        }
//        else {
//            Log.d("566","566");
//            return false;
//        }
        return false;
    }



    //GETS USER B'S USER ID'S (BASICALLY ALL USERS IN THE CLASS USERSWIPES WHO ISN'T USER A).
    public void method2(){
        ParseUser user = ParseUser.getCurrentUser();
        //START OF 2ND PART
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("UserCarSwipes");
        query2.whereNotEqualTo("UserObjectID",user.getObjectId());

        query2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                Log.d("602","602");
                if (e == null) {
                    Log.d("604","604");
                    if (objects.size() > 0) {
                        Log.d("606","606");
                        for (ParseObject object : objects) {
                            UserBObjectIDs.add(object.getString("UserObjectID"));
                            Log.d("609", UserObjectID);
                        }
                    }
                }
                UserBObjectIDs = UserBObjectIDs;
                UserBObjectIDsArrayList = new ArrayList<String>(UserBObjectIDs);
                method3();
            }
        });
        //END OF 2ND PART



    }


    public void method3(){
        ParseUser user = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query3 = ParseQuery.getQuery("UserCarSwipes");
        query3.whereNotEqualTo("UserObjectID",user.getObjectId());

        query3.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {

                    if (objects.size() > 0) {

                        for (ParseObject object : objects) {
                            Log.d("496","496");
                            for(int i = 0; i < UserBObjectIDsArrayList.size(); i++){
                                fitness = 0;
//                                fitnessTheSame=0;
//                                fitnessNotTheSame = 0;
                                Log.d("594",object.getString("UserObjectID"));
                                Log.d("595", UserBObjectIDsArrayList.get(i));
                                if(UserBObjectIDsArrayList.get(i).equals(object.getString("UserObjectID"))){
                                    Log.d("500","500");
                                    //SOMETHINGIS WRONG FROM HERE
                                    if(UserACarObjectIDsSwiped.contains(object.getString("CarObjectID"))){
                                        int index = UserACarObjectIDsSwiped.indexOf(object.getString("CarObjectID"));
                                        Log.d("504","504");
                                        if(UserACarObjectIDsSwipedLeftOrRight.get(index) == object.getBoolean("SwipedRight")){
                                            fitnessTheSame++;
                                            Log.d("505","505");
                                        }else{
                                            fitnessNotTheSame++;
                                            Log.d("508","508");
                                        }
                                        if(fitnessTheSame > bestfitnessTheSame){
//                                            Log.d("509","509");
//                                            bestfitnessTheSame = fitnessTheSame;
//                                            bestFitnessNotTheSame = fitnessNotTheSame;
                                            bestUserObjectID = object.getString("UserObjectID");
                                        }

                                    }
                                    //TO HERE
                                }
                            }
                            fitness = 0;
                        }
                        bestUserObjectID = bestUserObjectID;
                        bestfitness = bestfitness;
                        bestfitnessTheSame = bestfitnessTheSame;
                        bestFitnessNotTheSame = bestFitnessNotTheSame;
                        Log.d("600",String.valueOf(fitnessTheSame));
                        Log.d("601",String.valueOf(fitnessNotTheSame));

                        if(fitnessTheSame < 1 || fitnessNotTheSame <1){
                            output = 0.1;
                        }else{
                            output = fitnessTheSame/fitnessNotTheSame;
                        }



                        Log.d("625",String.valueOf(output));
                        if(output > 0.5) {
                            Log.d("650","650");
                            method4();
                        }
                    }
                }

            }
        });
        Log.d("531",String.valueOf(UserACarObjectIDsSwiped.size()));
        Log.d("531",String.valueOf(UserACarObjectIDsSwipedLeftOrRight.size()));



    }


    public void method4(){
        ParseQuery<ParseObject> query4 = ParseQuery.getQuery("UserCarSwipes");
        query4.whereEqualTo("UserObjectID",bestUserObjectID);
        Log.d("699", bestUserObjectID);
        query4.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {
                    Log.d("700","700");
                    if (objects.size() > 0) {
                        Log.d("720","720");
                        for (ParseObject object : objects) {
                            Log.d("740","740");
                            if(!(UserACarObjectIDsSwiped.contains(object.getString("CarObjectID")))) {
                                CarsNotRecommendedYetToUserA.add(object.getString("CarObjectID"));

                                Log.d("750","750");
                                Log.d("800,",String.valueOf(CarsNotRecommendedYetToUserA.size()));
                                Set<String> CarsNotRecommendedYetToUserASet = new HashSet<String>(CarsNotRecommendedYetToUserA);
                                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putStringSet("set",CarsNotRecommendedYetToUserASet);
                                editor.putBoolean("set2", true);
                                editor.commit();
                            }
                        }
                    }

                }

            }
        });




    }



    public void addMoreCarsToArrayListForSwiping(String CarObjectID, String CarManufacturer,String CarModel,String CarTrim,double CarPrice, double CarTopSpeed,double  CarZeroToSixty,int CarNumberOfDoors, int CarYearMade,int  CarHorsePower, boolean CarisElectric) {


        //ADDING CARS TO ARRAYLIST
        al.add(CarManufacturer+" "+CarModel +" "+CarTrim);
        arrayAdapter.notifyDataSetChanged();
        // END OF ADDING CARS TO ARRAYLIST


        //ADD USER INFO TO USERCARSWIPES IN PARSE
        ParseObject sc = new ParseObject("UserCarSwipes");
        sc.put("UserObjectID",UserObjectID);
        sc.put("CarObjectID",CarObjectID);
        sc.put("SwipedRight",SwipedRight);
        sc.saveInBackground();
        //END OF ADDING USERS TO USERCARSWIPES IN PARSE

        //UPDATE CURRENT ARRAY WHICH CONTAINS INFORMATION ABOUT CARS WHICH ARE SWIPED
        ObjectIDSpecificNumbersWeHaveAlreadyWentThrough.add(CarObjectID);



    }

    public void StoreValuesToRightArrayList(String CarObjectID, String CarManufacturer,String CarModel,String CarTrim,double CarPrice, double CarTopSpeed,double  CarZeroToSixty,int CarNumberOfDoors, int CarYearMade,int  CarHorsePower, boolean CarisElectric, String ImageURl){
        CarObjectIDListRight.add(CarObjectID);
        CarManufacturerListRight.add(CarManufacturer);
        CarModelListRight.add(CarModel);
        CarTrimListRight.add(CarTrim);
        CarPriceListRight.add(CarPrice);
        CarTopSpeedListRight.add((double)Math.round(CarTopSpeed/10.0) * 10);
        CarZeroToSixtyListRight.add(CarZeroToSixty);
        CarNumberOfDoorsListRight.add(CarNumberOfDoors);
        CarYearMadeListRight.add(CarYearMade);
        CarHorsePowerListRight.add(CarHorsePower);
        CarisElectricListRight.add(CarisElectric);

        CarManufacturerLeftAndRight.add(CarManufacturer);
        CarModelLeftAndRight.add(CarModel);
        CarTrimLeftAndRight.add(CarTrim);
        CarTopSpeedLeftAndRight.add((double)Math.round(CarTopSpeed/10.0) * 10);
        CarImageURLLeftAndRight.add(ImageURl);

        CarNumberOfDoorsLeftAndRight.add(CarNumberOfDoors);
        CarYearMadeListLeftAndRight.add(CarYearMade);
    }

    public void StoreValuesToLeftArrayList(String CarObjectID, String CarManufacturer,String CarModel,String CarTrim,double CarPrice, double CarTopSpeed,double  CarZeroToSixty,int CarNumberOfDoors, int CarYearMade,int  CarHorsePower, boolean CarisElectric, String ImageURl){
        CarObjectIDListLeft.add(CarObjectID);
        CarManufacturerListLeft.add(CarManufacturer);
        CarModelListLeft.add(CarModel);
        CarTrimListLeft.add(CarTrim);
        CarPriceListLeft.add(CarPrice);
        CarTopSpeedListLeft.add((double)Math.round(CarTopSpeed/10.0) * 10);
        CarZeroToSixtyListLeft.add(CarZeroToSixty);
        CarNumberOfDoorsListLeft.add(CarNumberOfDoors);
        CarYearMadeListLeft.add(CarYearMade);
        CarHorsePowerListLeft.add(CarHorsePower);
        CarisElectricListLeft.add(CarisElectric);

        CarManufacturerLeftAndRight.add(CarManufacturer);
        CarModelLeftAndRight.add(CarModel);
        CarTrimLeftAndRight.add(CarTrim);
        CarTopSpeedLeftAndRight.add((double)Math.round(CarTopSpeed/10.0) * 10);

        CarImageURLLeftAndRight.add(ImageURl);

        CarNumberOfDoorsLeftAndRight.add(CarNumberOfDoors);
        CarYearMadeListLeftAndRight.add(CarYearMade);

    }


    public void calculations(){

        CarManufacturerCalculations();
        CarModelCalculations();
        CarTrimCalculations();
        CarTopSpeedCalculations();
        NumberOfDoorsCalculations();
        YearMadeCalculations();
        FigureOutWhatDoesTheUserLikes();
    }




    public void CarManufacturerCalculations(){



        CarManufacturerAndIndexRight.clear();
        CarManufacturerAndIndexLeft.clear();
        CarManufacturerAndIndexAVG.clear();



        for(int i = 0; i < CarManufacturerListRight.size(); i++){
            if(CarManufacturerAndIndexRight.containsKey(CarManufacturerListRight.get(i))){// WE HAVE THIS MODEL IN THE HASHMAP
                CarManufacturerAndIndexRight.put(CarManufacturerListRight.get(i), CarManufacturerAndIndexRight.get(CarManufacturerListRight.get(i))+1);

                //      Log.d("CAR MAN 371", "WE HIT IF STATEMENT");
                Log.d("CAR MAN 372", String.valueOf(CarManufacturerAndIndexRight.get(CarManufacturerListRight.get(i))));

            }else{ // WE DONT HAVE THIS MODEL IN THE HASHMAP
                CarManufacturerAndIndexRight.put(CarManufacturerListRight.get(i), 1);
                //     Log.d("CAR MAN 376", "WE HIT ELSE STATEMENT");
                //    Log.d("CAR MAN 377", String.valueOf(CarManufacturerAndIndexRight.get(CarManufacturerListRight.get(i))));
            }

        }


        for(int i = 0; i < CarManufacturerListLeft.size(); i++){
            if(CarManufacturerAndIndexLeft.containsKey(CarManufacturerListLeft.get(i))){// WE HAVE THIS MODEL IN THE HASHMAP
                CarManufacturerAndIndexLeft.put(CarManufacturerListLeft.get(i), CarManufacturerAndIndexLeft.get(CarManufacturerListLeft.get(i))+1);

                //    Log.d("CAR MAN 387", "WE HIT IF STATEMENT");
                //   Log.d("CAR MAN 388", String.valueOf(CarManufacturerAndIndexLeft.get(CarManufacturerListLeft.get(i))));

            }else{ // WE DONT HAVE THIS MODEL IN THE HASHMAP
                CarManufacturerAndIndexLeft.put(CarManufacturerListLeft.get(i), 1);
                //   Log.d("CAR MAN 392", "WE HIT ELSE STATEMENT");
                //   Log.d("CAR MAN 393", String.valueOf(CarManufacturerAndIndexLeft.get(CarManufacturerListLeft.get(i))));
            }

        }


        for (Map.Entry<String, Integer> entry : CarManufacturerAndIndexRight.entrySet()) { //copying carmodelandindex right to carmodelandindexavg
            String key = entry.getKey();
            Integer value = entry.getValue();
            CarManufacturerAndIndexAVG.put(key,value);
            Log.d("CAR MAN 403", "KEY:" + key +" "+"VALUE:"+value);
        }

        for (Map.Entry<String, Integer> entry : CarManufacturerAndIndexLeft.entrySet()) { //
            String key = entry.getKey(); // CAR MODEL
            Integer value = entry.getValue(); // AMOUNT LIKED

            if (CarManufacturerAndIndexAVG.containsKey(key)) { // IF CAR MODEL INDEX AVG CONTAINS A MODEL WHICH THE USER DISLIKES
                CarManufacturerAndIndexAVG.put(key, CarManufacturerAndIndexAVG.get(key) - CarManufacturerAndIndexLeft.get(key));
                Log.d("CAR MAN 412", "KEY:" + key + " " + "VALUE:" + value);
            } else {
                int ok = CarManufacturerAndIndexLeft.get(key);
                ok = -ok;
                CarManufacturerAndIndexAVG.put(key, ok);
                Log.d("CAR MAN417", "KEY:" + key + " " + "ok:" + ok);
            }
        }




    }


    public void CarModelCalculations(){

        CarModelAndIndexRight.clear();
        CarModelAndIndexLeft.clear();
        CarModelAndIndexAVG.clear();




        for(int i = 0; i < CarModelListRight.size(); i++){
            if(CarModelAndIndexRight.containsKey(CarModelListRight.get(i))){// WE HAVE THIS MODEL IN THE HASHMAP
                CarModelAndIndexRight.put(CarModelListRight.get(i), CarModelAndIndexRight.get(CarModelListRight.get(i))+1);

                Log.d("371", "WE HIT IF STATEMENT");
                Log.d("372", String.valueOf(CarModelAndIndexRight.get(CarModelListRight.get(i))));

            }else{ // WE DONT HAVE THIS MODEL IN THE HASHMAP
                CarModelAndIndexRight.put(CarModelListRight.get(i), 1);
                Log.d("376", "WE HIT ELSE STATEMENT");
                Log.d("377", String.valueOf(CarModelAndIndexRight.get(CarModelListRight.get(i))));
            }

        }


        for(int i = 0; i < CarModelListLeft.size(); i++){
            if(CarModelAndIndexLeft.containsKey(CarModelListLeft.get(i))){// WE HAVE THIS MODEL IN THE HASHMAP
                CarModelAndIndexLeft.put(CarModelListLeft.get(i), CarModelAndIndexLeft.get(CarModelListLeft.get(i))+1);

                Log.d("387", "WE HIT IF STATEMENT");
                Log.d("388", String.valueOf(CarModelAndIndexLeft.get(CarModelListLeft.get(i))));

            }else{ // WE DONT HAVE THIS MODEL IN THE HASHMAP
                CarModelAndIndexLeft.put(CarModelListLeft.get(i), 1);
                Log.d("392", "WE HIT ELSE STATEMENT");
                Log.d("393", String.valueOf(CarModelAndIndexLeft.get(CarModelListLeft.get(i))));
            }

        }


        for (Map.Entry<String, Integer> entry : CarModelAndIndexRight.entrySet()) { //copying carmodelandindex right to carmodelandindexavg
            String key = entry.getKey();
            Integer value = entry.getValue();
            CarModelAndIndexAVG.put(key,value);
            Log.d("403", "KEY:" + key +" "+"VALUE:"+value);
        }

        for (Map.Entry<String, Integer> entry : CarModelAndIndexLeft.entrySet()) { //
            String key = entry.getKey(); // CAR MODEL
            Integer value = entry.getValue(); // AMOUNT LIKED

            if(CarModelAndIndexAVG.containsKey(key)){ // IF CAR MODEL INDEX AVG CONTAINS A MODEL WHICH THE USER DISLIKES
                CarModelAndIndexAVG.put(key,CarModelAndIndexAVG.get(key)-CarModelAndIndexLeft.get(key));
                Log.d("412", "KEY:" + key +" "+"VALUE:"+value);
            }else{
                int ok = CarModelAndIndexLeft.get(key);
                ok = -ok;
                CarModelAndIndexAVG.put(key,ok);
                Log.d("417", "KEY:" + key +" "+"ok:"+ok);
            }

        }


    }



    public void CarTrimCalculations(){


        CarTrimAndIndexRight.clear();
        CarTrimAndIndexLeft.clear();
        CarTrimAndIndexAVG.clear();

        for(int i = 0; i < CarTrimListRight.size(); i++){
            if(CarTrimAndIndexRight.containsKey(CarTrimListRight.get(i))){// WE HAVE THIS MODEL IN THE HASHMAP
                CarTrimAndIndexRight.put(CarTrimListRight.get(i), CarTrimAndIndexRight.get(CarTrimListRight.get(i))+1);


            }else{ // WE DONT HAVE THIS MODEL IN THE HASHMAP
                CarTrimAndIndexRight.put(CarTrimListRight.get(i), 1);

            }

        }


        for(int i = 0; i < CarTrimListLeft.size(); i++){
            if(CarTrimAndIndexLeft.containsKey(CarTrimListLeft.get(i))){// WE HAVE THIS MODEL IN THE HASHMAP
                CarTrimAndIndexLeft.put(CarTrimListLeft.get(i), CarTrimAndIndexLeft.get(CarTrimListLeft.get(i))+1);
            }else{ // WE DONT HAVE THIS MODEL IN THE HASHMAP
                CarTrimAndIndexLeft.put(CarTrimListLeft.get(i), 1);
            }

        }


        for (Map.Entry<String, Integer> entry : CarTrimAndIndexRight.entrySet()) { //copying carmodelandindex right to carmodelandindexavg
            String key = entry.getKey();
            Integer value = entry.getValue();
            CarTrimAndIndexAVG.put(key,value);
        }

        for (Map.Entry<String, Integer> entry : CarTrimAndIndexLeft.entrySet()) { //
            String key = entry.getKey(); // CAR MODEL
            Integer value = entry.getValue(); // AMOUNT LIKED

            if(CarTrimAndIndexAVG.containsKey(key)){ // IF CAR MODEL INDEX AVG CONTAINS A MODEL WHICH THE USER DISLIKES
                CarTrimAndIndexAVG.put(key,CarTrimAndIndexAVG.get(key)-CarTrimAndIndexLeft.get(key));
            }else{
                int ok = CarTrimAndIndexLeft.get(key);
                ok = -ok;
                CarTrimAndIndexAVG.put(key,ok);

            }

        }


    }



    public void CarTopSpeedCalculations(){


        CarTopSpeedAndIndexRight.clear();
        CarTopSpeedAndIndexLeft.clear();
        CarTopSpeedAndIndexAVG.clear();

        for(int i = 0; i < CarTopSpeedListRight.size(); i++){
            if(CarTopSpeedAndIndexRight.containsKey(CarTopSpeedListRight.get(i))){// WE HAVE THIS MODEL IN THE HASHMAP
                CarTopSpeedAndIndexRight.put(CarTopSpeedListRight.get(i), CarTopSpeedAndIndexRight.get(CarTopSpeedListRight.get(i))+1);

                Log.d("371", "WE HIT IF STATEMENT");
                Log.d("372", String.valueOf(CarTopSpeedAndIndexRight.get(CarTopSpeedListRight.get(i))));

            }else{ // WE DONT HAVE THIS MODEL IN THE HASHMAP
                CarTopSpeedAndIndexRight.put(CarTopSpeedListRight.get(i), 1);
                Log.d("376", "WE HIT ELSE STATEMENT");
                Log.d("377", String.valueOf(CarTopSpeedAndIndexRight.get(CarTopSpeedListRight.get(i))));
            }

        }


        for(int i = 0; i < CarTopSpeedListLeft.size(); i++){
            if(CarTopSpeedAndIndexLeft.containsKey(CarTopSpeedListLeft.get(i))){// WE HAVE THIS MODEL IN THE HASHMAP
                CarTopSpeedAndIndexLeft.put(CarTopSpeedListLeft.get(i), CarTopSpeedAndIndexLeft.get(CarTopSpeedListLeft.get(i))+1);

                Log.d("387", "WE HIT IF STATEMENT");
                Log.d("388", String.valueOf(CarTopSpeedAndIndexLeft.get(CarTopSpeedListLeft.get(i))));

            }else{ // WE DONT HAVE THIS MODEL IN THE HASHMAP
                CarTopSpeedAndIndexLeft.put(CarTopSpeedListLeft.get(i), 1);
                Log.d("392", "WE HIT ELSE STATEMENT");
                Log.d("393", String.valueOf(CarTopSpeedAndIndexLeft.get(CarTopSpeedListLeft.get(i))));
            }

        }


        for (Map.Entry<Double, Integer> entry : CarTopSpeedAndIndexRight.entrySet()) { //copying carmodelandindex right to carmodelandindexavg
            Double key = entry.getKey();
            Integer value = entry.getValue();
            CarTopSpeedAndIndexAVG.put(key,value);
            Log.d("403", "KEY:" + key +" "+"VALUE:"+value);
        }

        for (Map.Entry<Double, Integer> entry : CarTopSpeedAndIndexLeft.entrySet()) { //
            Double key = entry.getKey(); // CAR MODEL
            Integer value = entry.getValue(); // AMOUNT LIKED

            if(CarTopSpeedAndIndexAVG.containsKey(key)){ // IF CAR MODEL INDEX AVG CONTAINS A MODEL WHICH THE USER DISLIKES
                CarTopSpeedAndIndexAVG.put(key,CarTopSpeedAndIndexAVG.get(key)-CarTopSpeedAndIndexAVG.get(key));
                Log.d("412", "KEY:" + key +" "+"VALUE:"+value);
            }else{
                int ok = CarTopSpeedAndIndexLeft.get(key);
                ok = -ok;
                CarTopSpeedAndIndexAVG.put(key,ok);
                Log.d("417", "KEY:" + key +" "+"ok:"+ok);
            }

        }


    }



    public void NumberOfDoorsCalculations(){


        CarNumberOfDoorsAndIndexRight.clear();
        CarNumberOfDoorsAndIndexLeft.clear();
        CarNumberOfDoorsAndIndexAVG.clear();

        for(int i = 0; i < CarNumberOfDoorsListRight.size(); i++){
            if(CarNumberOfDoorsAndIndexRight.containsKey(CarNumberOfDoorsListRight.get(i))){// WE HAVE THIS MODEL IN THE HASHMAP
                CarNumberOfDoorsAndIndexRight.put(CarNumberOfDoorsListRight.get(i), CarNumberOfDoorsAndIndexRight.get(CarNumberOfDoorsListRight.get(i))+1);



            }else{ // WE DONT HAVE THIS MODEL IN THE HASHMAP
                CarNumberOfDoorsAndIndexRight.put(CarNumberOfDoorsListRight.get(i), 1);

            }

        }


        for(int i = 0; i < CarNumberOfDoorsListLeft.size(); i++){
            if(CarNumberOfDoorsAndIndexLeft.containsKey(CarNumberOfDoorsListLeft.get(i))){// WE HAVE THIS MODEL IN THE HASHMAP
                CarNumberOfDoorsAndIndexLeft.put(CarNumberOfDoorsListLeft.get(i), CarNumberOfDoorsAndIndexLeft.get(CarNumberOfDoorsListLeft.get(i))+1);

            }else{ // WE DONT HAVE THIS MODEL IN THE HASHMAP
                CarNumberOfDoorsAndIndexLeft.put(CarNumberOfDoorsListLeft.get(i), 1);

            }

        }


        for (Map.Entry<Integer, Integer> entry : CarNumberOfDoorsAndIndexRight.entrySet()) { //copying carmodelandindex right to carmodelandindexavg
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            CarNumberOfDoorsAndIndexAVG.put(key,value);
            Log.d("403", "KEY:" + key +" "+"VALUE:"+value);
        }

        for (Map.Entry<Integer, Integer> entry : CarNumberOfDoorsAndIndexLeft.entrySet()) { //
            Integer key = entry.getKey(); // CAR MODEL
            Integer value = entry.getValue(); // AMOUNT LIKED

            if(CarNumberOfDoorsAndIndexAVG.containsKey(key)){ // IF CAR MODEL INDEX AVG CONTAINS A MODEL WHICH THE USER DISLIKES
                CarNumberOfDoorsAndIndexAVG.put(key,CarNumberOfDoorsAndIndexAVG.get(key)-CarNumberOfDoorsAndIndexAVG.get(key));
                Log.d("412", "KEY:" + key +" "+"VALUE:"+value);
            }else{
                int ok = CarTopSpeedAndIndexLeft.get(key);
                ok = -ok;
                CarNumberOfDoorsAndIndexAVG.put(key,ok);
                Log.d("417", "KEY:" + key +" "+"ok:"+ok);
            }

        }


    }


    public void YearMadeCalculations(){


        CarYearMadeAndIndexRight.clear();
        CarYearMadeAndIndexLeft.clear();
        CarYearMadeAndIndexAVG.clear();


        for(int i = 0; i < CarTopSpeedListRight.size(); i++){
            if(CarYearMadeAndIndexRight.containsKey(CarYearMadeListRight.get(i))){// WE HAVE THIS MODEL IN THE HASHMAP
                CarYearMadeAndIndexRight.put(CarYearMadeListRight.get(i), CarYearMadeAndIndexRight.get(CarYearMadeListRight.get(i))+1);



            }else{ // WE DONT HAVE THIS MODEL IN THE HASHMAP
                CarYearMadeAndIndexRight.put(CarYearMadeListRight.get(i), 1);

            }

        }


        for(int i = 0; i < CarTopSpeedListLeft.size(); i++){
            if(CarYearMadeAndIndexLeft.containsKey(CarYearMadeListLeft.get(i))){// WE HAVE THIS MODEL IN THE HASHMAP
                CarYearMadeAndIndexLeft.put(CarYearMadeListLeft.get(i), CarYearMadeAndIndexLeft.get(CarYearMadeListLeft.get(i))+1);

            }else{ // WE DONT HAVE THIS MODEL IN THE HASHMAP
                CarYearMadeAndIndexLeft.put(CarYearMadeListLeft.get(i), 1);

            }

        }


        for (Map.Entry<Integer, Integer> entry : CarYearMadeAndIndexRight.entrySet()) { //copying carmodelandindex right to carmodelandindexavg
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            CarYearMadeAndIndexAVG.put(key,value);
            Log.d("403", "KEY:" + key +" "+"VALUE:"+value);
        }

        for (Map.Entry<Integer, Integer> entry : CarYearMadeAndIndexLeft.entrySet()) { //
            Integer key = entry.getKey(); // CAR MODEL
            Integer value = entry.getValue(); // AMOUNT LIKED

            if(CarYearMadeAndIndexAVG.containsKey(key)){ // IF CAR MODEL INDEX AVG CONTAINS A MODEL WHICH THE USER DISLIKES
                CarYearMadeAndIndexAVG.put(key,CarYearMadeAndIndexAVG.get(key)-CarYearMadeAndIndexAVG.get(key));
                Log.d("412", "KEY:" + key +" "+"VALUE:"+value);
            }else{
                int ok = CarYearMadeAndIndexLeft.get(key);
                ok = -ok;
                CarYearMadeAndIndexAVG.put(key,ok);
                Log.d("417", "KEY:" + key +" "+"ok:"+ok);
            }

        }


    }


    public void FigureOutWhatDoesTheUserLikes(){


        Random random = new Random();

        double bestFitness = -1;
        int bestIndex = 1;


        int value11 = 0, value22 = 0, value33 = 0;
        double value44 = 0;
        int value55 = 0;
        int value66 = 0;

        String value1;
        String value2;
        String value3;
        Double value4;
        int value5;
        int value6;


        for(int i = 0; i < 1000; i++){

            double newfitness = 0;

            int RandomIndex = random.nextInt(CarManufacturerLeftAndRight.size()-1 - 0 + 1) + 0;

            value1 = CarManufacturerLeftAndRight.get(RandomIndex);
            value2 = CarModelLeftAndRight.get(RandomIndex);
            value3 = CarTrimLeftAndRight.get(RandomIndex);
            value4 = CarTopSpeedLeftAndRight.get(RandomIndex);
            value5 = CarNumberOfDoorsLeftAndRight.get(RandomIndex);
            value6 = CarYearMadeListLeftAndRight.get(RandomIndex);

            value11 = CarManufacturerAndIndexAVG.get(value1);
            value22 = CarModelAndIndexAVG.get(value2);
            value33 = CarTrimAndIndexAVG.get(value3);
            value44 = CarTopSpeedAndIndexAVG.get(value4);
            value55 = CarNumberOfDoorsAndIndexAVG.get(value5);
            value66 = CarYearMadeAndIndexAVG.get(value6);


            newfitness = value11+value22+value33+value44+value55+value66;
            Log.d("newfitness", String.valueOf(newfitness));

            if(newfitness > bestFitness){
                bestFitness = newfitness;
                bestIndex = RandomIndex;
            }


        }

        TextView textView = findViewById(R.id.textView14);
        textView.setText(" Car Manufacturer:" + String.valueOf( CarManufacturerLeftAndRight.get(bestIndex)) + " Car Model:" +String.valueOf(CarModelLeftAndRight.get(bestIndex)) + " Car Trim:"+String.valueOf(CarTrimLeftAndRight.get(bestIndex)) + "Car Top Speed:" +CarTopSpeedLeftAndRight.get(bestIndex));
        ImageView iv = findViewById(R.id.imageView);
        Picasso.get().load(CarImageURLLeftAndRight.get(bestIndex)).into(iv);


    }





}
//THIS IS LATEST