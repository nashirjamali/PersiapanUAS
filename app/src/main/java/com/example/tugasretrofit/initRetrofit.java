package com.example.tugasretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class initRetrofit {
    private static final String URL = "http://10.0.3.2/AppRetrofit/";

    public static Retrofit setInit(){
        return new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static ApiInterface getInstance(){
        return setInit().create(ApiInterface.class);
    }
}
