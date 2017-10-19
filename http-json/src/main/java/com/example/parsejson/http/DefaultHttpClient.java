package com.example.parsejson.http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class DefaultHttpClient implements HttpClient {
    @Override
    public void request(final String url, final ResponseListener listener) {
        try {
            final HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
            final InputStream is = con.getInputStream();
            listener.onResponse(is);
            con.disconnect();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    //TODO: implement method
    @Override
    public String request(final String ulr) {
        return null;
    }

    public interface ResponseListener {
        void onResponse(final InputStream inputStream);
    }
}
