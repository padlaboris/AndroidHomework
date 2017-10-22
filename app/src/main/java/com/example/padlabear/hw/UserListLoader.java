package com.example.padlabear.hw;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


import com.example.padlabear.hw.http.DefaultHttpClient;
import com.example.padlabear.hw.http.HttpClient;
import com.example.padlabear.hw.json.UserList;
import com.example.padlabear.hw.json.gson.GsonUser;

import java.io.InputStream;
import java.util.List;

public class UserListLoader extends AsyncTask<Context, Void, String> {

    public static final String NO_DATA = "No data";
    private UserList userListWithObject;
    private Context context;

    @Override
    protected String doInBackground(Context... params) {

        final UsersListParserFactory usersListParserFactory = new UsersListParserFactory();
        HttpClient httpClient = new DefaultHttpClient();

        userListWithObject = null;

        httpClient.request(Api.USER_URL, new DefaultHttpClient.ResponseListener() {
            @Override
            public void onResponse(InputStream InputStream) {
                try {
                    userListWithObject = usersListParserFactory.createParserForResponceWithObject(InputStream).parse();
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        });

        context = params[0];

        if (userListWithObject == null) {
            return NO_DATA;
        }

        List<GsonUser> usersList = userListWithObject.getUserList();

        if (usersList == null || usersList.isEmpty()) {
            return NO_DATA;
        }

        return usersList.get(usersList.size() -1).getFirstName();
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

}
