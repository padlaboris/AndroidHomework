package com.example.padlabear.hw.http;

import android.support.annotation.VisibleForTesting;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class DefaultHttpClient implements HttpClient {

    private HttpURLConnection connection;

    @Override
    public void request(final String url, final ResponseListener listener) {
        try {
            final InputStream is = openStream(url);
            listener.onResponse(is);
            connection.disconnect();
        } catch (final Throwable t) {
            listener.onError(t);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    @Override
    public String request(final String ulr) {
        return null;
    }

    @VisibleForTesting
    InputStream openStream(final String url) throws IOException {
        connection = (HttpURLConnection) (new URL(url)).openConnection();
        return connection.getInputStream();
    }

    public interface ResponseListener {
        void onResponse(InputStream pInputStream) throws Exception;
        void onError(Throwable pThrowable);
    }
}
