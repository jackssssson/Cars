package com.cars.cars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fragments.ShowCarsFragment;

public class CarImage extends AppCompatActivity {
    private String carName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_image);

        Intent intend = getIntent();
        carName = intend.getStringExtra("car");

        ShowCarsFragment fragment = new ShowCarsFragment();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }

    public String getCarName(){
        return this.carName;
    }
}
