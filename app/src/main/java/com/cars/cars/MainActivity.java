package com.cars.cars;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mText;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = findViewById(R.id.textView);
        String url = "http://10.86.177.253:8080/api/cars";

        final OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        /*
        new AsyncTask<Boolean, Void, Void>(){
            @Override
            protected Void doInBackground(Boolean... booleans) {
                try {
                    Response response = client.newCall(request).execute();
                    String body = response.body().string();
                    mText.setText(body);
                } catch (IOException e){
                    e.printStackTrace();
                }

                return null;
            }
        }.execute(true);
        */

        new AsyncTask<Boolean, Void, Void>() {

            @Override
            protected Void doInBackground(Boolean... booleans) {
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        final String myResponce = response.body().string();

                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mText.setText(myResponce);
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
}
