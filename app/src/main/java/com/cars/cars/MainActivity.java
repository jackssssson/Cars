package com.cars.cars;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cars.cars.presenter.AllCars;
import com.cars.cars.presenter.DaggerGetAllCars;
import com.cars.cars.presenter.GetAllCars;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Car;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private List<Car> listOfCars;

    @BindView(R.id.list_View_Cars)
    ListView listViewCars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        listOfCars = new ArrayList<>();

        AsyncTaskRequest();
        System.out.println();

    }

    @SuppressLint("StaticFieldLeak")
    public void AsyncTaskRequest() {
        String url = "http://10.242.34.80:8080/api/cars";

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
                                GetAllCars getAllCars = DaggerGetAllCars.create();
                                AllCars allCars = getAllCars.getTheCars();
                                listOfCars = allCars.gson(myResponce);
                                String[] carNames = allCars.carInfoNames(listOfCars);
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
                String s = "";

                switch (position) {
                    case 0:
                        s = "AlfaRomeo";
                        break;
                    case 1:
                        s = "Mercedes";
                        break;
                    case 2:
                        s = "Opel";
                        break;
                    case 3:
                        s = "Ferrari";
                        break;
                    case 4:
                        s = "Maserati";
                        break;
                    case 5:
                        s = "Audi";
                        break;
                    case 6:
                        s ="Bmw";
                        break;
                    case 7:
                        s ="Renault";
                        break;
                }

                Intent intent = new Intent(MainActivity.this, CarImage.class);
                intent.putExtra("car", s);
                startActivity(intent);
            }
        });
    }
}
