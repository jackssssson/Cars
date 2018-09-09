package com.cars.cars.presenter;

import java.util.List;

import javax.inject.Inject;

import model.Car;

public class AllCars {
    private PresentCars presentCars;

    @Inject
    AllCars(PresentCars presentCars) {
        this.presentCars = presentCars;
    }

    public List<Car> gson(String s){
        return presentCars.gson(s);
    }

    public String[] carInfoNames(List<Car> cars){
        return presentCars.carInfoNames(cars);
    }
}
