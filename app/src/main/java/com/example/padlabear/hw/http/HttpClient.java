package com.example.padlabear.hw.http;

public interface HttpClient {
    void request(final String url, final DefaultHttpClient.ResponseListener listener);

    String request(final String ulr);
}
