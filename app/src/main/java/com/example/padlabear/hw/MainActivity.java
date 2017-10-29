package com.example.padlabear.hw;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.widget.Toast;

import com.example.padlabear.hw.calculator.CalculatorActivity;
import com.example.padlabear.myapplication.backend.userApi.UserApi;
import com.example.padlabear.myapplication.backend.userApi.model.User;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.List;

class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static UserApi userApi = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if (userApi == null) {  // Only do this once
            UserApi.Builder builder = new UserApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            userApi = builder.build();
        }

        context = params[0].first;

        try {
            final List<User> users = userApi.list().execute().getItems();
            if (users == null || users.isEmpty()) {
                return "No Data";
            }
            return users.iterator().next().getFirstName() + " Registered!";
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}

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


