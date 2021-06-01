package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ContentBasedFiltering extends AppCompatActivity {

    Button RecommendationButton;

    Boolean raj = false;
    TextView TextViewRecommendedCar;

    String userAobjectID;
    String userAcurrentCarManufacturer;
    String userAcurrentCarModel;
    String userAcurrentCarTrim;
    String userAcurrentCarYearWasMade;
    String userAcurrentCarWhenCarWasBought;
    String userAcurrentCarHowMuchWasBoughtFor;

    String userAIdealCarManufacturer;
    String userAIdealCarModel;
    String userAIdealCarTrim;
    String userAIdealCarYearWasMade;
    String userAIdealCarWhenToPurchase;
    String userAIdealCarHowMuchToBuyFor;

    String userAcurrentCarComfort;
   String userAcurrentCarSpeedRating;
    String userAcurrentCarSpaceRating;
   String userAcurrentCarSafetyRating;
   String userAcurrentCarSuitabilityForEverydayUseRating;
    String userAidealCarComfortRating;
   String userAidealCarSpeedRating ;
    String userAidealCarSpaceRating ;
   String userAidealCarSafetyRating ;
    String userAidealCarSuitabilityForEverydayUseRating;

    //
    ArrayList<String> UserBobjectID = new ArrayList<String>();
    ArrayList<String> UserBcurrentCarManufacturer = new ArrayList<String>();
    ArrayList<String> UserBcurrentCarModel = new ArrayList<String>();
    ArrayList<String> UserBcurrentCarTrim = new ArrayList<String>();
    ArrayList<String> UserBcurrentCarYearWasMade = new ArrayList<String>();
    ArrayList<String> UserBcurrentCarWhenCarWasMade = new ArrayList<String>();
    ArrayList<String> UserBcurrentCarWhenCarWasBought = new ArrayList<String>();
    ArrayList<String> UserBcurrentCarHowMuchWasBoughtFor = new ArrayList<String>();

    ArrayList<String> UserBidealCarManufacturer = new ArrayList<String>();
    ArrayList<String> UserBidealCarModel = new ArrayList<String>();
    ArrayList<String> UserBIdealCarTrim = new ArrayList<String>();
    ArrayList<String> UserBIdealCarYearWasMade = new ArrayList<String>();
    ArrayList<String> UserBIdealCarWhenToPurchase = new ArrayList<String>();
    ArrayList<String> UserBIdealCarHowMuchToBuyFor = new ArrayList<String>();

    ArrayList<String> UserBcurrentCarComfort = new ArrayList<String>();
    ArrayList<String> UserBcurrentCarSpeedRating = new ArrayList<String>();
    ArrayList<String> UserBcurrentCarSpaceRating = new ArrayList<String>();
    ArrayList<String> UserBcurrentCarSafetyRating = new ArrayList<String>();
    ArrayList<String> UserBcurrentCarSuitabilityForEverydayUseRating = new ArrayList<String>();
    ArrayList<String> UserBidealCarComfortRating = new ArrayList<String>();
    ArrayList<String> UserBidealCarSpeedRating = new ArrayList<String>();
    ArrayList<String> UserBidealCarSpaceRating = new ArrayList<String>();
    ArrayList<String> UserBidealCarSafetyRating = new ArrayList<String>();
    ArrayList<String> UserBidealCarSuitabilityForEverydayUseRating = new ArrayList<String>();

    //
    //


    String BestUserCurrentCarManufacturer = "";
    String BestUserCurrentCarModel = "";
    String BestUserCurrentCarTrim = "";
    String BestUserCurrentCarYearWasMade = "";
    String BestUserCurrentCarHowMuchWasBoughtFor = "";
    String BestUserCurrentCarWhenCarWasBought = "";

    String BestUserIdealCarManufacturer = "";
    String BestUserIdealCarModel = "";
    String BestUserIdealNextCarTrim = "";
    String BestUserIdealCarYearWasMade = "";
    String BestUserIdealCarWhenToPurchase = "";
    String BestUserIdealCarPrice = "";

    String BestusercurrentCarComfort;
    String BestusercurrentCarSpeedRating;
    String BestusercurrentCarSpaceRating;
    String BestusercurrentCarSafetyRating;
    String BestusercurrentCarSuitabilityForEverydayUseRating;
    String BestuseridealCarComfortRating;
    String BestuseridealCarSpeedRating ;
    String BestuseridealCarSpaceRating ;
    String BestuseridealCarSafetyRating ;
    String BestuseridealCarSuitabilityForEverydayUseRating;


    /**/
    double BestUserIdealCarPriceDepreciated = 0;
    double BestUserCurrentCarPriceDepreciated = 0;
    double BestUserCurrentCarManufacturerDepreciation = 0;
    double BestUserIdealCarManufacturerDepreciation = 0;
    //

    boolean recommendCurrentCar;
    boolean didWeFindAMatch = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_based_filtering);

      //  TextViewRecommendedCar = (TextView) findViewById(R.id.textView14);

        RecommendationButton = findViewById(R.id.RecommendationButton);
        RecommendationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentBasedFiltering();
            }
        });

    }


    public void contentBasedFiltering() {

        getUserAInformation();
        getUserBInformation();

        //  getRecommendations();
        Log.d("LINE 99", BestUserCurrentCarManufacturer);

    }


    public void getUserAInformation() { // WORKS AS INTENDED

        ParseUser userA = ParseUser.getCurrentUser();

        userAobjectID = userA.getObjectId();

        userAcurrentCarManufacturer = userA.getString("currentCarManufacturer");
        Log.d("74", userAcurrentCarManufacturer);

        userAcurrentCarModel = userA.getString("currentCarModel");
        Log.d("76", userAcurrentCarModel);

        userAcurrentCarTrim = userA.getString("currentCarTrim");
        Log.d("78", userAcurrentCarTrim);

        userAcurrentCarYearWasMade = userA.getString("currentCarYearWasMade");
        Log.d("80", userAcurrentCarYearWasMade);

        userAcurrentCarWhenCarWasBought = userA.getString("currentCarWhenCarWasBought");
        Log.d("82", userAcurrentCarWhenCarWasBought);

        userAcurrentCarHowMuchWasBoughtFor = userA.getString("currentCarHowMuchWasBoughtFor");
        Log.d("84", userAcurrentCarHowMuchWasBoughtFor);

        userAIdealCarManufacturer = userA.getString("idealCarManufacturer");
        Log.d("86", userAIdealCarManufacturer);

        userAIdealCarModel = userA.getString("idealCarModel");
        Log.d("88", userAIdealCarModel);

        userAIdealCarTrim = userA.getString("idealNextCarTrim");
        Log.d("90", userAIdealCarTrim);

        userAIdealCarYearWasMade = userA.getString("idealCarYearWasMade");
        Log.d("92", userAIdealCarYearWasMade);

        userAIdealCarWhenToPurchase = userA.getString("idealCarWhenToPurchase");
        Log.d("94", userAIdealCarWhenToPurchase);

        userAIdealCarHowMuchToBuyFor = userA.getString("idealCarHowMuchToBuyFor");
        Log.d("96", userAIdealCarHowMuchToBuyFor);

        userAcurrentCarComfort = userA.getString("idealCarComfortRating");
        userAcurrentCarSpeedRating = userA.getString("currentCarSpeedRating");
        userAcurrentCarSpaceRating = userA.getString("currentCarSpaceRating");
        userAcurrentCarSafetyRating = userA.getString("currentCarSafetyRating");
        userAcurrentCarSuitabilityForEverydayUseRating = userA.getString("currentCarSuitabilityForEverydayUseRating");
        userAidealCarComfortRating = userA.getString("idealCarComfortRating");
        userAidealCarSpeedRating = userA.getString("idealCarSpeedRating");
        userAidealCarSpaceRating = userA.getString("idealCarSpaceRating");
        userAidealCarSafetyRating = userA.getString("idealCarSafetyRating");
        userAidealCarSuitabilityForEverydayUseRating = userA.getString("idealCarSuitabilityForEverydayUseRating");


    }

    public void getUserBInformation() {

        ParseQuery<ParseUser> query = ParseUser.getQuery();

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {

                    if (objects.size() > 0) {

                        for (int i = 0; i < objects.size(); i++) {


                            if (!(objects.get(i).getObjectId().equals(userAobjectID))) {
                                ParseObject p = objects.get(i);
                                UserBobjectID.add(p.getObjectId());
                                UserBcurrentCarManufacturer.add(p.getString("currentCarManufacturer"));
                                UserBcurrentCarModel.add(p.getString("currentCarModel"));
                                UserBcurrentCarTrim.add(p.getString("currentCarTrim"));
                                UserBcurrentCarYearWasMade.add(p.getString("currentCarYearWasMade"));
                                UserBcurrentCarWhenCarWasMade.add(p.getString("currentCarWhenCarWasMade"));
                                UserBcurrentCarWhenCarWasBought.add(p.getString("currentCarWhenCarWasBought"));
                                UserBcurrentCarHowMuchWasBoughtFor.add(p.getString("currentCarHowMuchWasBoughtFor"));
                                UserBidealCarManufacturer.add(p.getString("idealCarManufacturer"));
                                UserBidealCarModel.add(p.getString("idealCarModel"));
                                UserBIdealCarTrim.add(p.getString("idealNextCarTrim"));
                                UserBIdealCarYearWasMade.add(p.getString("idealCarYearWasMade"));
                                UserBIdealCarWhenToPurchase.add(p.getString("idealCarWhenToPurchase"));
                                UserBIdealCarHowMuchToBuyFor.add(p.getString("idealCarHowMuchToBuyFor"));

                                UserBcurrentCarComfort.add(p.getString("currentCarComfortRating"));
                                UserBcurrentCarSpeedRating.add(p.getString("currentCarSpeedRating"));
                                UserBcurrentCarSpaceRating.add(p.getString("currentCarSpaceRating"));
                                UserBcurrentCarSafetyRating.add(p.getString("currentCarSafetyRating"));
                                UserBcurrentCarSuitabilityForEverydayUseRating.add(p.getString("currentCarSuitabilityForEverydayUseRating"));

                                UserBidealCarComfortRating.add(p.getString("idealCarComfortRating"));
                                        UserBidealCarSpeedRating.add(p.getString("idealCarSpeedRating"));
                                UserBidealCarSpaceRating.add(p.getString("idealCarSpaceRating"));
                                        UserBidealCarSafetyRating.add(p.getString("idealCarSafetyRating"));
                                UserBidealCarSuitabilityForEverydayUseRating.add(p.getString("idealCarSuitabilityForEverydayUseRating"));






                                Log.d("line 182", "182");

                            }
                        }
                    }
                    getBestUser();
                    //outputRecommendedCar();
                    ShouldWeRecommendCurrentCar();
                }

            }
        });


    }


    public void getBestUser() {

        int fitness = 0;
        int bestFitness = 0;

        Log.d("204", Integer.toString(UserBcurrentCarManufacturer.size()));

        for (int i = 0; i < UserBcurrentCarManufacturer.size(); i++) {
            fitness = 0;

            Log.d("209", userAIdealCarWhenToPurchase);
            Log.d("210", UserBIdealCarWhenToPurchase.get(i));

            if(userAcurrentCarComfort.equals(UserBcurrentCarComfort.get(i))){
                fitness += 100;
            }

            if(userAcurrentCarSpaceRating.equals(UserBcurrentCarSpaceRating.get(i))){
                fitness += 100;
            }

            if(userAcurrentCarSafetyRating.equals(UserBcurrentCarSafetyRating.get(i))){
                fitness += 100;
            }

            if(userAcurrentCarSuitabilityForEverydayUseRating.equals(UserBcurrentCarSuitabilityForEverydayUseRating.get(i))){
                fitness += 100;
            }

            if(userAcurrentCarComfort.equals(UserBcurrentCarComfort.get(i))){
                fitness+=100;
            }

            if(userAidealCarSpeedRating.equals(UserBidealCarSpeedRating.get(i))){
                fitness+=100;
            }

            if(userAidealCarSpaceRating.equals(UserBidealCarSpaceRating.get(i))){
                fitness+=100;
            }

            if(userAidealCarSafetyRating.equals(UserBidealCarSafetyRating.get(i))){
                fitness+=100;
            }

            if(userAidealCarSuitabilityForEverydayUseRating.equals(UserBidealCarSafetyRating.get(i))){
                fitness+=100;
            }

            if (fitness >= bestFitness) {
                bestFitness = fitness;

                didWeFindAMatch = true;

                BestUserCurrentCarManufacturer = UserBcurrentCarManufacturer.get(i);
                BestUserCurrentCarModel = UserBcurrentCarModel.get(i);
                BestUserCurrentCarTrim = UserBcurrentCarTrim.get(i);
                BestUserCurrentCarYearWasMade = UserBcurrentCarYearWasMade.get(i);
                BestUserCurrentCarHowMuchWasBoughtFor = UserBcurrentCarHowMuchWasBoughtFor.get(i);
                BestUserCurrentCarWhenCarWasBought = UserBcurrentCarWhenCarWasBought.get(i);

                BestUserIdealCarManufacturer = UserBidealCarManufacturer.get(i);
                BestUserIdealCarModel = UserBidealCarModel.get(i);
                BestUserIdealNextCarTrim = UserBIdealCarTrim.get(i);
                BestUserIdealCarYearWasMade = UserBIdealCarYearWasMade.get(i);
                BestUserIdealCarWhenToPurchase = UserBIdealCarWhenToPurchase.get(i);

                BestusercurrentCarComfort = UserBcurrentCarComfort.get(i);
                BestusercurrentCarSpeedRating = UserBcurrentCarSpeedRating.get(i);
              BestusercurrentCarSpaceRating = UserBcurrentCarSpaceRating.get(i);
               BestusercurrentCarSafetyRating = UserBcurrentCarSafetyRating.get(i);
               BestusercurrentCarSuitabilityForEverydayUseRating = UserBcurrentCarSuitabilityForEverydayUseRating.get(i);

                BestuseridealCarComfortRating = UserBidealCarComfortRating.get(i);
              BestuseridealCarSpeedRating = UserBidealCarSpeedRating.get(i);
               BestuseridealCarSpaceRating  = UserBidealCarSpaceRating.get(i);
               BestuseridealCarSafetyRating = UserBidealCarSafetyRating.get(i);
                BestuseridealCarSuitabilityForEverydayUseRating = UserBidealCarSuitabilityForEverydayUseRating.get(i);

                Log.d("Line 284", BestUserCurrentCarManufacturer);
                Log.d("Line 286", BestUserCurrentCarModel);
                Log.d("Line 288", BestUserCurrentCarTrim);
                Log.d("Line 290", BestUserCurrentCarYearWasMade);
                Log.d("Line 292", BestUserCurrentCarHowMuchWasBoughtFor);
                Log.d("Line 294", BestUserCurrentCarWhenCarWasBought);
                Log.d("Line 296", BestUserIdealNextCarTrim);
                Log.d("Line 298", BestUserIdealCarYearWasMade);
                Log.d("Line 300", BestUserIdealCarWhenToPurchase);
            }


        }


    }

    public void ShouldWeRecommendCurrentCar (){

        Log.d("300", BestUserIdealNextCarTrim);
        Log.d("300", BestUserIdealCarYearWasMade);


        //double UserAbudgetMinusUserBcurrentCar = depreciateCarValue(userAIdealCarHowMuchToBuyFor, BestUserCurrentCarHowMuchWasBoughtFor);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Cars");

        query.whereEqualTo("CarTrim", BestUserIdealNextCarTrim);
        query.whereEqualTo("CarYearMade", BestUserIdealCarYearWasMade);
        query.setLimit(1);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {


                    if (objects.size() > 0) {

                        for (ParseObject object : objects) {
                            BestUserIdealCarPrice = object.getString("CarPrice");
                            Log.d("yhyh", BestUserIdealCarPrice);
                        }




                }
                calculateToFigureCurrentOrIdealCar();


            }
        });
        Log.d("shouldok", BestUserCurrentCarHowMuchWasBoughtFor);

        // double UserAbudgetMinusUserBidealCar = depreciateCarValue(userAIdealCarHowMuchToBuyFor,BestUserIdealCarPrice );

    }


    public void calculateToFigureCurrentOrIdealCar(){


        BestUserCurrentCarPriceDepreciated = Integer.valueOf(BestUserCurrentCarHowMuchWasBoughtFor);


        int BestUserCurrentCarYearBoughtMinusUserAYearToBuy = Integer.valueOf(userAIdealCarWhenToPurchase) - Integer.valueOf(BestUserCurrentCarWhenCarWasBought);

        if (BestUserCurrentCarManufacturer.equals("FORD") || BestUserCurrentCarManufacturer.equals("AUDI")){
            BestUserCurrentCarManufacturerDepreciation = 0.4;
            Log.d("i is", "361");
        }else if(BestUserCurrentCarManufacturer.equals("BMW")){
            BestUserCurrentCarManufacturerDepreciation = 0.35;
            Log.d("i is", "363");
        }
        Log.d("365", BestUserCurrentCarManufacturer);

        BestUserCurrentCarManufacturerDepreciation = BestUserCurrentCarManufacturerDepreciation/3;



        for(int i = 0; i < BestUserCurrentCarYearBoughtMinusUserAYearToBuy; i++){
            Log.d("i is", Integer.toString(i));
            BestUserCurrentCarPriceDepreciated = BestUserCurrentCarPriceDepreciated*(1.00-BestUserCurrentCarManufacturerDepreciation);
            Log.d("392", String.valueOf(BestUserCurrentCarPriceDepreciated));
        }

        BestUserIdealCarPriceDepreciated = Integer.valueOf(BestUserIdealCarPrice);

        int BestUserIdealCarYearBoughtMinusUserAYearToBuy = Integer.valueOf(userAIdealCarWhenToPurchase) - 2019;

        Log.d("1000",BestUserIdealCarManufacturer);

        if (BestUserIdealCarManufacturer.equals("FORD") || BestUserIdealCarManufacturer.equals("AUDI"))
        {
            BestUserIdealCarManufacturerDepreciation = 0.4;
            Log.d("2000", "2000");
        }
        else if(BestUserIdealCarManufacturer.equals("BMW")){
            BestUserIdealCarManufacturerDepreciation = 0.35;
            Log.d("3000","3000");
        }
        Log.d("390", String.valueOf(BestUserIdealCarManufacturerDepreciation));

        BestUserIdealCarManufacturerDepreciation = BestUserIdealCarManufacturerDepreciation/3;

        for(int i = 0; i < BestUserIdealCarYearBoughtMinusUserAYearToBuy; i++){
            BestUserIdealCarPriceDepreciated = BestUserIdealCarPriceDepreciated*(1.00-BestUserIdealCarManufacturerDepreciation);
            Log.d("391", String.valueOf(BestUserIdealCarPriceDepreciated));
        }

//        Log.d("391", String.valueOf(BestUserIdealCarPriceDepreciated));
//        Log.d("392", String.valueOf(BestUserCurrentCarPriceDepreciated));

        if(BestUserCurrentCarPriceDepreciated <= Double.valueOf(userAIdealCarHowMuchToBuyFor)){
            recommendCurrentCar = true;
        }
        else{
            recommendCurrentCar = false;
        }

        TextView carManufacturerTextView = findViewById(R.id.carManufacturerTextView);
        TextView carModelTextView = findViewById(R.id.carModelTextView);
        TextView carTrimTextView = findViewById(R.id.CarTrimTextView);
        TextView carYearWasMadeTextView = findViewById(R.id.carYearWasMadeTextView);
        TextView carPriceTextView = findViewById(R.id.carPriceTextView);

        //
        TextView carYearToBuyTextView = findViewById(R.id.carYearToBuyTextView);


        if(didWeFindAMatch == false){
            carManufacturerTextView.setText("NO MATCH WAS FOUND");
            carModelTextView.setText("NO MATCH WAS FOUND");
            carTrimTextView.setText("NO MATCH WAS FOUND");
            carYearWasMadeTextView.setText("NO MATCH WAS FOUND");
            carPriceTextView.setText("NO MATCH WAS FOUND");
            carYearToBuyTextView.setText("NO MATCH WAS FOUND");


        }
        else if(recommendCurrentCar == true){
            carManufacturerTextView.setText(BestUserCurrentCarManufacturer);
            carModelTextView.setText(BestUserCurrentCarModel);
            carTrimTextView.setText(BestUserCurrentCarTrim);
            carYearWasMadeTextView.setText(BestUserCurrentCarYearWasMade);
            carPriceTextView.setText(Double.toString(BestUserCurrentCarPriceDepreciated));
            carYearToBuyTextView.setText(userAIdealCarWhenToPurchase);
        }else {
            carManufacturerTextView.setText(BestUserIdealCarManufacturer);
            carModelTextView.setText(BestUserIdealCarModel);
            carTrimTextView.setText(BestUserIdealNextCarTrim);
            carYearWasMadeTextView.setText(BestUserIdealCarYearWasMade);
            carPriceTextView.setText(Double.toString(BestUserIdealCarPriceDepreciated));
            carYearToBuyTextView.setText(userAIdealCarWhenToPurchase);
        }


    }



    public void test(String test) {

        TextViewRecommendedCar.setText(UserBcurrentCarManufacturer.get(0));

        Log.d("line 234", Integer.toString(UserBobjectID.size()));
        for (int i = 0; i < UserBobjectID.size(); i++) {
            Log.d("line 236", UserBobjectID.get(i));


        }


    }


}


