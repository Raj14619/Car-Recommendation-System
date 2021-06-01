package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class UpdateUserInformation extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_information2);


        setSpinners();

        Button registerButton = findViewById(R.id.updateInformationButon);

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){

                updateUser();

            }
        });

//        Button loginButton = findViewById(R.id.backButton);
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToHomepage();
//            }
//        });



    }




    /*START OF SPINNER RELATED METHODS*/
    public void setSpinners(){

        setCurrentCarManufacturerSpinner();
        setCurrentCarModelSpinner();
        setCurrentCarTrimSpinner();
        setCurrentCarYearMadeSpinner();
        setCurrentCarYearBoughtSpinner();
        setCurrentCarHowMuchBoughtForSpinner();

        setIdealNextCarManufacturerSpinner();
        setIdealNextCarModelSpinner();
        setIdealNextCarTrimSpinner();
        setIdealNextCarYearMadeSpinner();
        setIdealNextCarYearBoughtSpinner();
        setIdealNextCarHowMuchBoughtForSpinner();

        setCurrentCarSpeedRatingSpinner();
        setCurrentCarSpaceRatingSpinner();
        setCurrentCarComfortRatingSpinner();
        setCurrentCarSafetyRatingSpinner();
        setCurrentCarSuitabilityForEverydayUseRatingSpinner();

        setIdealCarComfortRatingSpinner();
        setIdealCarSpeedRatingSpinner();
        setIdealCarSpaceRatingSpinner();
        setIdealCarSafetyRatingSpinner();
        setIdealCarSuitabilityForEverydayUseRatingSpinner();

    }


    public void setCurrentCarComfortRatingSpinner(){



        Spinner dropdown = findViewById(R.id.currentCarComfortSpinner);
        String[] items = new String[]{"1","2","3","4","5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


    }

    public void setCurrentCarSpeedRatingSpinner(){

        Spinner dropdown = findViewById(R.id.currentCarSpeedRatingSpinner);
        String[] items = new String[]{"1","2","3","4","5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }

    public void setCurrentCarSpaceRatingSpinner(){



        Spinner dropdown = findViewById(R.id.currentCarSpaceRatingSpinner);
        String[] items = new String[]{"1","2","3","4","5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


    }

    public void setCurrentCarSafetyRatingSpinner(){



        Spinner dropdown = findViewById(R.id.currentCarSafetyRatingSpinner);
        String[] items = new String[]{"1","2","3","4","5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


    }



    public void setCurrentCarSuitabilityForEverydayUseRatingSpinner(){



        Spinner dropdown = findViewById(R.id.currentCarSuitabilityForEverydayUseRatingSpinner);
        String[] items = new String[]{"1","2","3","4","5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


    }

    //
    public void setIdealCarComfortRatingSpinner(){



        Spinner dropdown = findViewById(R.id.idealCarComfortRatingSpinner);
        String[] items = new String[]{"1","2","3","4","5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


    }

    public void setIdealCarSpeedRatingSpinner(){

        Spinner dropdown = findViewById(R.id.idealCarSpeedRatingSpinner);
        String[] items = new String[]{"1","2","3","4","5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }

    public void setIdealCarSpaceRatingSpinner(){



        Spinner dropdown = findViewById(R.id.idealCarSpaceRatingSpinner);
        String[] items = new String[]{"1","2","3","4","5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


    }

    public void setIdealCarSafetyRatingSpinner(){



        Spinner dropdown = findViewById(R.id.idealCarSafetyRatingSpinner);
        String[] items = new String[]{"1","2","3","4","5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


    }



    public void setIdealCarSuitabilityForEverydayUseRatingSpinner(){



        Spinner dropdown = findViewById(R.id.idealCarSuitabilityForEverydayUseRatingSpinner);
        String[] items = new String[]{"1","2","3","4","5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


    }
    //



    public void setCurrentCarManufacturerSpinner(){

        Spinner dropdown = findViewById(R.id.currentCarManufacturerSpinner);
        String[] items = new String[]{"FORD", "BMW", "AUDI"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }



    public void setCurrentCarModelSpinner(){

        Spinner dropdown = findViewById(R.id.currentCarModelSpinner);
        String[] items = new String[]{
                                        "FORD FIESTA", "BMW 1SERIES", "FORD FOCUS", "FORD FIESTA", "AUDI A4", "AUDI A3"
                                    };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }



    public void setCurrentCarTrimSpinner(){

        Spinner dropdown = findViewById(R.id.currentCarTrimSpinner);
        String[] items = new String[]{
                                    "FORD FIESTA TITANIUM","BMW 1SERIES MSPORT","FORD FOCUS T","FORD FIESTA ZECTEC","AUDI A4 SLINE","AUDI A3 SLINE","BMW 1SERIES MSPORT"
                                     };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }



    public void setCurrentCarYearMadeSpinner(){

        Spinner dropdown = findViewById(R.id.currentCarYearMade);
        String [] items = new String[]{
                "2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }


    public void setCurrentCarYearBoughtSpinner(){

        Spinner dropdown = findViewById(R.id.currentCarYearBought);
        String [] items = new String[]{
                "2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }


    public void setCurrentCarHowMuchBoughtForSpinner(){

        Spinner dropdown = findViewById(R.id.currentCarPuchasePrice);
        String [] items = new String[]{
                "1000","2000","3000","4000","5000","6000","7000","8000","9000","10000","11000","12000","13000","14000","15000","16000","17000","18000","19000","20000","22000","24000","26000","28000","30000","32000","34000","36000","38000","40000","42000","44000","46000","48000","50000","52000","54000","56000",
                "58000","60000","62000","64000","66000","68000","70000","72000","74000","76000","78000","80000"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }



    public void setIdealNextCarManufacturerSpinner(){

        Spinner dropdown = findViewById(R.id.idealNextCarManufacturerSpinner);
        String[] items = new String[]{"Ford", "BMW", "Audi"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }



    public void setIdealNextCarModelSpinner(){

        Spinner dropdown = findViewById(R.id.idealNextCarModelSpinner);
        String[] items = new String[]{
                "FORD FIESTA", "BMW 1SERIES", "FORD FOCUS", "FORD FIESTA", "AUDI A4", "AUDI A3"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }



    public void setIdealNextCarTrimSpinner(){

        Spinner dropdown = findViewById(R.id.idealNextCarTrimSpinner);
        String[] items = new String[]{
                "FORD FIESTA TITANIUM","BMW 1SERIES MSPORT","FORD FOCUS T","FORD FIESTA ZECTEC","AUDI A4 SLINE","AUDI A3 SLINE","BMW 1SERIES MSPORT"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }



    public void setIdealNextCarYearMadeSpinner(){

        Spinner dropdown = findViewById(R.id.idealNextCarYearMadeSpinner);
        String [] items = new String[]{
                "2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }


    public void setIdealNextCarYearBoughtSpinner(){

        Spinner dropdown = findViewById(R.id.idealNextCarYearToBuySpinner);
        String [] items = new String[]{
               "2019","2020","2021","2022","2023","2024","2025"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }


    public void setIdealNextCarHowMuchBoughtForSpinner(){

        Spinner dropdown = findViewById(R.id.idealNextCarPurchasePriceSpinner);
        String [] items = new String[]{
                "1000","2000","3000","4000","5000","6000","7000","8000","9000","10000","11000","12000","13000","14000","15000","16000","17000","18000","19000","20000","22000","24000","26000","28000","30000","32000","34000","36000","38000","40000","42000","44000","46000","48000","50000","52000","54000","56000",
                "58000","60000","62000","64000","66000","68000","70000","72000","74000","76000","78000","80000"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }

    /*END OF SPINNER RELATED METHODS*/











    public void updateUser(){


        //START OF CONVERT ANDROID VIEWS TO DATA TYPES

        EditText emailEditText = findViewById(R.id.emailEditText);
        String email = emailEditText.getText().toString();

        EditText firstNameEditText = findViewById(R.id.firstNameEditText);
        String firstName = firstNameEditText.getText().toString();

        EditText lastNameEditText = findViewById(R.id.lastNameEditText);
        String lastName = lastNameEditText.getText().toString();

        EditText PasswordEditText = findViewById(R.id.passwordEditText);
        String password = PasswordEditText.getText().toString();

        EditText PasswordConfirmationEditText = findViewById(R.id.passwordConfirmationEditText);
        String passwordConfirmation = PasswordConfirmationEditText.getText().toString();

        Spinner currentCarManufacturerSpinner = findViewById(R.id.currentCarManufacturerSpinner);
        String currentCarManufacturer = currentCarManufacturerSpinner.getSelectedItem().toString();

        Spinner currentCarModelSpinner = findViewById(R.id.currentCarModelSpinner);
        String currentCarModel = currentCarModelSpinner.getSelectedItem().toString();

        Spinner currentCarTrimSpinner = findViewById(R.id.currentCarTrimSpinner);
        String currentCarTrim = currentCarTrimSpinner.getSelectedItem().toString();

        Spinner currentCarYearWasMadeSpinner = findViewById(R.id.currentCarYearMade);
        String currentCarYearWasMade = currentCarYearWasMadeSpinner.getSelectedItem().toString();

        Spinner currentCarYearBoughtSpinner = findViewById(R.id.currentCarYearBought);
        String currentCarYearBought = currentCarYearBoughtSpinner.getSelectedItem().toString();

        Spinner currentCarHowMuchBoughtForSpinner = findViewById(R.id.currentCarPuchasePrice);
        String currentCarHowMuchBoughtFor = currentCarHowMuchBoughtForSpinner.getSelectedItem().toString();

        Spinner idealCarManufacturerSpinner = findViewById(R.id.idealNextCarManufacturerSpinner);
        String idealCarManufacturer = idealCarManufacturerSpinner.getSelectedItem().toString();

        Spinner idealCarModelSpinner = findViewById(R.id.idealNextCarModelSpinner);
        String idealCarModel = idealCarModelSpinner.getSelectedItem().toString();

        Spinner idealCarTrimSpinner = findViewById(R.id.idealNextCarTrimSpinner);
        String idealCarTrim = idealCarTrimSpinner.getSelectedItem().toString();

        Spinner idealCarYearMadeSpinner = findViewById(R.id.idealNextCarYearMadeSpinner);
        String idealCarYearMade = idealCarYearMadeSpinner.getSelectedItem().toString();

        Spinner idealCarWhenToBuySpinner = findViewById(R.id.idealNextCarYearToBuySpinner);
        String idealCarWhenToBuy = idealCarWhenToBuySpinner.getSelectedItem().toString();

        Spinner idealCarHowMuchToBuySpinner = findViewById(R.id.idealNextCarPurchasePriceSpinner);
        String idealCarHowMuchToBuy = idealCarHowMuchToBuySpinner.getSelectedItem().toString();

        //
        Spinner currentCarComfortRatingSpinner = findViewById(R.id.currentCarComfortSpinner);
        String currentCarComfortRating = currentCarComfortRatingSpinner.getSelectedItem().toString();

        Spinner currentCarSpeedRatingSpinner = findViewById(R.id.currentCarSpeedRatingSpinner);
        String currentCarSpeedRating = currentCarSpeedRatingSpinner.getSelectedItem().toString();

        Spinner currentCarSpaceRatingSpinner = findViewById(R.id.currentCarSpaceRatingSpinner);
        String currentCarSpaceRating = currentCarSpaceRatingSpinner.getSelectedItem().toString();

        Spinner currentCarSafetyRatingSpinner = findViewById(R.id.currentCarSafetyRatingSpinner);
        String currentCarSafetyRating = currentCarSafetyRatingSpinner.getSelectedItem().toString();

        Spinner currentCarSuitabilityForEverydayUseRatingSpinner = findViewById(R.id.currentCarSuitabilityForEverydayUseRatingSpinner);
        String currentCarSuitabilityRating = currentCarSuitabilityForEverydayUseRatingSpinner.getSelectedItem().toString();


        Spinner idealCarComfortRatingSpinner = findViewById(R.id.idealCarComfortRatingSpinner);
        String idealCarComfortRating = idealCarComfortRatingSpinner.getSelectedItem().toString();

        Spinner idealCarSpeedRatingSpinner = findViewById(R.id.idealCarSpeedRatingSpinner);
        String idealCarSpeedRating = idealCarSpeedRatingSpinner.getSelectedItem().toString();

        Spinner idealCarSpaceRatingSpinner = findViewById(R.id.idealCarSpaceRatingSpinner);
        String idealCarSpaceRating = idealCarSpaceRatingSpinner.getSelectedItem().toString();

        Spinner idealCarSafetyRatingSpinner = findViewById(R.id.idealCarSafetyRatingSpinner);
        String idealCarSafetyRating = idealCarSafetyRatingSpinner.getSelectedItem().toString();

        Spinner idealCarSuitabilityForEverydayUseRatingSpinner = findViewById(R.id.idealCarSuitabilityForEverydayUseRatingSpinner);
        String idealCarSuitabilityRating = idealCarSuitabilityForEverydayUseRatingSpinner.getSelectedItem().toString();


        //END OF CONVERT ANDROID VIEWS TO DATA TYPES




        //  START OF CHECK IF INPUT IS VALID

//            if(currentCarManufacturer.isEmpty() || currentCarModel.isEmpty() || currentCarTrim.isEmpty() || currentCarYearWasMade.isEmpty() || currentCarYearBought.isEmpty() || currentCarHowMuchBoughtFor.isEmpty() || idealCarManufacturer.isEmpty() || idealCarModel.isEmpty() || idealCarTrim.isEmpty() || idealCarYearMade.isEmpty() || idealCarWhenToBuy.isEmpty() || idealCarHowMuchToBuy.isEmpty()){
//                Toast.makeText(this, "Vehicle information cannot be empty", Toast.LENGTH_LONG);
//                return;
//            }
//
//            if(password != passwordConfirmation){
//                Toast.makeText(this, "password and password confirmation is different", Toast.LENGTH_LONG);
//                return;
//            }
//
//            if(password.isEmpty() || passwordConfirmation.isEmpty()){
//                Toast.makeText(this, "password or password confirmation is empty", Toast.LENGTH_LONG);
//                return;
//            }
//
//            if(password.isEmpty() || passwordConfirmation.isEmpty()){
//                Toast.makeText(this, "password or password confirmation is empty", Toast.LENGTH_LONG);
//                return;
//            }
//
//            if(email.isEmpty()){
//                Toast.makeText(this, "email is empty", Toast.LENGTH_LONG);
//                return;
//            }
//
//            if(firstName.isEmpty() || lastName.isEmpty()){
//                Toast.makeText(this, "first name and last name is empty", Toast.LENGTH_LONG);
//                return;
//            }



        // END OF CHECK IF INPUT IS VALID





        // REGISTER USER ON PARSE

        ParseUser user = ParseUser.getCurrentUser();

        user.setUsername(email);
        user.setPassword(password);
        user.put("email", email);
        user.put("firstName", firstName );
        user.put("lastName", lastName );
        user.put("currentCarManufacturer", currentCarManufacturer);
        user.put("currentCarModel", currentCarModel);
        user.put("currentCarTrim", currentCarTrim);
        user.put("currentCarYearWasMade", currentCarYearWasMade);
        user.put("currentCarWhenCarWasBought", currentCarYearBought);
        user.put("currentCarHowMuchWasBoughtFor", currentCarHowMuchBoughtFor);
        user.put("idealCarManufacturer", idealCarManufacturer);
        user.put("idealCarModel", idealCarModel);
        user.put("idealNextCarTrim", idealCarTrim);
        user.put("idealCarYearWasMade", idealCarYearMade);
        user.put("idealCarWhenToPurchase",idealCarWhenToBuy);
        user.put("idealCarHowMuchToBuyFor",idealCarHowMuchToBuy);

        user.put("currentCarComfort", currentCarComfortRating);
        user.put("currentCarSpeedRating", currentCarSpeedRating);
        user.put("currentCarSpaceRating", currentCarSpaceRating);
        user.put("currentCarSafetyRating", currentCarSafetyRating);
        user.put("currentCarSuitabilityForEverydayUseRating",currentCarSuitabilityRating);


        user.put("idealCarComfortRating", idealCarComfortRating);
        user.put("idealCarSpeedRating", idealCarSpeedRating);
        user.put("idealCarSpaceRating", idealCarSpaceRating);
        user.put("idealCarSafetyRating", idealCarSafetyRating);
        user.put("idealCarSuitabilityForEverydayUseRating", idealCarSuitabilityRating);


        user.saveInBackground();

        Toast.makeText(this, "Information updated", Toast.LENGTH_LONG).show();



        // END OF REGISTER ON PARSE





    }


//    public void goToHomepage(){
//
//        Intent myIntent123 = new Intent(getBaseContext(), Homepage.class);
//        startActivity(myIntent123);
//
//    }













}
