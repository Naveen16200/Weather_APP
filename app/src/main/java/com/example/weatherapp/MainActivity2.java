package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class MainActivity2 extends AppCompatActivity {

    TextView textView,ettemp,etmain,etcity,etpre,ethum,etlow,ethigh,etwind,textView1,textView2;
    ConstraintLayout constraintLayout;
    StringRequest stringRequest;
    private final String url = "http://api.openweathermap.org/data/2.5/weather";
    private final String api = "c13b5181049109a637b3ed5117a361eb";
    DecimalFormat df = new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main2);

        etcity = findViewById(R.id.city);
        etmain = findViewById(R.id.Main);
        ettemp = findViewById(R.id.feel);
        etpre = findViewById(R.id.pa);
        ethum = findViewById(R.id.humiditycal);
        etlow = findViewById(R.id.min);
        ethigh = findViewById(R.id.max);
        etwind = findViewById(R.id.windspeed);
        textView = findViewById(R.id.textView2);
        textView1 = findViewById(R.id.textView3);
        textView2 = findViewById(R.id.textView4);
        constraintLayout = findViewById(R.id.ConstraintLayout);

        Intent intent = getIntent();
        String extra = intent.getStringExtra("name");

        etcity.setText(extra);

        String tempUrl = "";

        tempUrl = url  + "?q=" + extra + "&appid=" + api;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.d("response", response);
                String Output,Output1,Output2,Output3,Output4,Output5,Output6,Output7,Output8;
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                    String main = jsonObjectWeather.getString("main");
                    int id = jsonObjectWeather.getInt("id");
                    JSONObject jsonObjectMains = jsonResponse.getJSONObject("main");
                    double temp = jsonObjectMains.getDouble("temp")-273.15;
                    double feelLike = jsonObjectMains.getDouble("feels_like")-273.15;
                    float press = jsonObjectMains.getInt("pressure");
                    int humidity = jsonObjectMains.getInt("humidity");
                    double tempMin = jsonObjectMains.getDouble("temp_min")-273.15;
                    double tempMax = jsonObjectMains.getDouble("temp_max")-273.15;
                    JSONObject jsonObjectWinds = jsonResponse.getJSONObject("wind");
                    String wind = jsonObjectWinds.getString("speed");
                    Output = main;
                    etmain.setText(Output);
                    Output1 = df.format(temp);
                    textView.setText(Output1);
                    Output2 = df.format(feelLike)+"°C";
                    ettemp.setText(Output2);
                    Output3 = String.valueOf(humidity)+"%";
                    ethum.setText(Output3);
                    Output4 = String.valueOf(press)+"hpa";
                    etpre.setText(Output4);
                    Output5 = df.format(tempMin)+"°C";
                    etlow.setText(Output5);
                    Output6 = df.format(tempMax)+"°C";
                    ethigh.setText(Output6);
                    Output7 = wind + "m/s";
                    etwind.setText(Output7);
                    if(id >=0 && id < 300){
                        constraintLayout.setBackgroundResource(R.drawable.thunder);
                    }
                    else if(id >= 200 && id < 500){
                        constraintLayout.setBackgroundResource(R.drawable.rainy);
                    }
                    else if(id >= 500 && id < 600){
                        constraintLayout.setBackgroundResource(R.drawable.rain1);
                    }
                    else if(id >= 600 && id < 700){
                        constraintLayout.setBackgroundResource(R.drawable.snow);
                        etcity.setTextColor(Color.BLACK);
                        etmain.setTextColor(Color.BLACK);
                        etlow.setTextColor(Color.BLACK);
                        ethigh.setTextColor(Color.BLACK);
                        ethum.setTextColor(Color.BLACK);
                        etwind.setTextColor(Color.BLACK);
                        etpre.setTextColor(Color.BLACK);
                        ettemp.setTextColor(Color.BLACK);
                        textView.setTextColor(Color.BLACK);
                        textView1.setTextColor(Color.BLACK);
                        textView2.setTextColor(Color.BLACK);

                    }
                    else if(id >= 700 && id < 745){
                        constraintLayout.setBackgroundResource(R.drawable.haze);
                        etcity.setTextColor(Color.BLACK);
                        etmain.setTextColor(Color.BLACK);
                        etlow.setTextColor(Color.BLACK);
                        ethigh.setTextColor(Color.BLACK);
                        ethum.setTextColor(Color.BLACK);
                        etwind.setTextColor(Color.BLACK);
                        etpre.setTextColor(Color.BLACK);
                        ettemp.setTextColor(Color.BLACK);
                        textView.setTextColor(Color.BLACK);
                        textView1.setTextColor(Color.BLACK);
                        textView2.setTextColor(Color.BLACK);
                    }
                    else if(id >= 745 && id < 775){
                        constraintLayout.setBackgroundResource(R.drawable.sandstroms);
                    }
                    else if(id >= 775 && id < 800){
                        constraintLayout.setBackgroundResource(R.drawable.tornadoes);
                    }
                    else if(id==800){
                        constraintLayout.setBackgroundResource(R.drawable.clear);
                        etcity.setTextColor(Color.BLACK);
                        etmain.setTextColor(Color.BLACK);
                        etlow.setTextColor(Color.BLACK);
                        ethigh.setTextColor(Color.BLACK);
                        ethum.setTextColor(Color.BLACK);
                        etwind.setTextColor(Color.BLACK);
                        etpre.setTextColor(Color.BLACK);
                        ettemp.setTextColor(Color.BLACK);
                        textView.setTextColor(Color.BLACK);
                        textView1.setTextColor(Color.BLACK);
                        textView2.setTextColor(Color.BLACK);
                    }
                    else{
                        constraintLayout.setBackgroundResource(R.drawable.cloudy1);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"PLEASE TRY AGAIN WITH CORRECT CITY NAME",Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}