package com.cars.cars;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import cars.Car;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private List<Car> listOfCars;
    private ListView listViewCars;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfCars = new ArrayList<>();
        listViewCars = findViewById(R.id.list_View_Cars);

        AsyncTaskRequest();

        System.out.println();
    }

    @SuppressLint("StaticFieldLeak")
    public void AsyncTaskRequest(){
        String url = "http://79.100.147.125:8080/api/cars";

        final OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        new AsyncTask<Boolean, Void, Void>() {

            @Override
            protected Void doInBackground(Boolean... booleans) {
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        final String myResponce = Objects.requireNonNull(response.body()).string();

                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listOfCars = gson(myResponce);
                                String[] carNames = carInfoNames(listOfCars);
                                showListView(carNames);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(true);
    }

    private void showListView(String[] carNames) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, carNames);

        listViewCars.setAdapter(adapter);
        listViewCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, CarImage.class));
            }
        });
    }

    public List<Car> gson(String s) {
        Gson gs = new Gson();

        Car[] car = gs.fromJson(s, Car[].class);

        return new ArrayList<>(Arrays.asList(car));
    }

    public String[] carInfoNames(List<Car> cars){
        String[] carNames = new String[listOfCars.size()];
        for (int i = 0; i < cars.size(); i++){
            Car car = cars.get(i);

            String s = car.toString();
            carNames[i] = s;
        }

        return carNames;
    }
}
