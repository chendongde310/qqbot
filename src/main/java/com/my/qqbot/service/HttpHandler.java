package com.my.qqbot.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpHandler {
    private static HttpHandler httpHandler;
    public  OkHttpClient client ;

    public static HttpHandler Handler() {
        if (httpHandler == null) {
            httpHandler = new HttpHandler();
        }

        return httpHandler;
    }


    private HttpHandler( ) {
        this.client = new OkHttpClient();
    }
}
