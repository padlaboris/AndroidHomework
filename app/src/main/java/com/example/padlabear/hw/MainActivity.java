package com.example.padlabear.hw;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;

import com.example.padlabear.hw.calculator.CalculatorActivity;


public class MainActivity extends AppCompatActivity {

    public static final String LOG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startCalculatorActivity();

        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));
    }

    public void startCalculatorActivity() {
        startActivity(new Intent(this, CalculatorActivity.class));
    }
}
