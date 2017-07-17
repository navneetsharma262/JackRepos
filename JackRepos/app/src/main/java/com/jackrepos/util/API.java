package com.jackrepos.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.jackrepos.networking.interfaces.APIServiceCall;
import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import static java.security.AccessController.getContext;

/**
 * Created by Navneet on 13-07-2017.
 */
public class API {

    private static API instance;

    private APIServiceCall service;

    private API() {
        /* IGNORED */
    }

    public static API get() {
        if (instance == null) {
            instance = new API();
        }
        return instance;
    }

    public APIServiceCall getRetrofitService(String url) {
            OkHttpClient client = new OkHttpClient();
            client.interceptors().add(new LoggingInterceptor());
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            service = retrofit.create(APIServiceCall.class);

        return service;
    }
}
