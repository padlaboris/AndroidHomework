package com.example.padlabear.androidhomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String LOG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        someMethod();
    }

    public void someMethod(){
        System.out.println("Hello world");
        Log.d(LOG, "some method is called");
    }

    public void someMethodForStash(){
        System.out.println("Stash me plz");
    }
}
