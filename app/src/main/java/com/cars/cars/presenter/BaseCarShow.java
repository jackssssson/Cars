package com.cars.cars.presenter;

import java.util.List;

import model.Car;

public interface BaseCarShow {
    List<Car> gson(String s);
    String[] carInfoNames(List<Car> cars);
}
