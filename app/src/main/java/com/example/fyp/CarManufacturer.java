package com.example.fyp;

import java.util.ArrayList;
import java.util.Set;

public class CarManufacturer {

    private String CarManufacturer;
    private Set<String> CarModel;
    private Set<String> CarTrim;

    public CarManufacturer(String carManufacturer,  Set<String> CarModel,  Set<String> CarTrim){

        this.CarManufacturer=carManufacturer;
        this.CarModel=CarModel;
        this.CarTrim=CarTrim;

    }

    public String getCarManufacturer() {
        return CarManufacturer;
    }

    public Set<String> getCarModel() {
        return CarModel;
    }

    public Set<String> getCarTrim() {
        return CarTrim;
    }


}
