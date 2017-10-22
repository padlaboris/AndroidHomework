package com.example.padlabear.hw;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.padlabear.hw.http.DefaultHttpClient;
import com.example.padlabear.hw.http.HttpClient;
import com.example.padlabear.hw.json.gson.GsonUserList;
import com.example.padlabear.hw.util.IOUtils;

import java.io.InputStream;

public class UserListLoader extends AsyncTask<Context, Void, String> {

    public static final String NO_DATA = "No data";
    private GsonUserList users;
    private Context context;

    @Override
    protected String doInBackground(final Context... params) {

        final UserParserFactory factory = new UserParserFactory();
        final HttpClient httpClient = new DefaultHttpClient();


        httpClient.request(Api.USER_URL, new DefaultHttpClient.ResponseListener() {
            @Override
            public void onResponse(final InputStream inputStream) {
                try {
                    users = factory.createUsersParser(IOUtils.toString(inputStream)).parse();
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(final Throwable pThrowable) {

            }
        });

        context = params[0];

        if (users != null && !users.getUsers().isEmpty()) {
            return users.getUsers().iterator().next().getFirstName();
        }
        return NO_DATA;
    }

    @Override
    protected void onPostExecute(final String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

}
