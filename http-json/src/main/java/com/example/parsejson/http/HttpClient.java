package com.example.parsejson.http;

import java.io.InputStream;

public interface HttpClient {
    void request(final String url, final DefaultHttpClient.ResponseListener listener);

    String request(final String ulr);
}
