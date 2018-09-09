package com.cars.cars.presenter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import model.Car;

public class PresentCars implements BaseCarShow{

    @Inject
    PresentCars() {
    }


    @Override
    public List<Car> gson(String s) {
        Gson gs = new Gson();

        Car[] car = gs.fromJson(s, Car[].class);

        return new ArrayList<>(Arrays.asList(car));
    }

    @Override
    public String[] carInfoNames(List<Car> cars) {
        String[] carNames = new String[cars.size()];
        for (int i = 0; i < cars.size(); i++){
            Car car = cars.get(i);

            String s = car.toString();
            carNames[i] = s;
        }

        return carNames;
    }
}
