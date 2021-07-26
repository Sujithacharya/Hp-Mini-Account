package com.example.hp_mini_account;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;

public class MyClient {
    private static final String BASE_URL="https://hpminiaccount.000webhostapp.com/miniaccount/";
//    private OkHttpClient.Builder builder=new OkHttpClient.Builder();
//    private HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
    private static MyClient myClient;
    private static Retrofit retrofit;

    private MyClient()
    {

//        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel((HttpLoggingInterceptor.Level.BODY));
//        OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();


//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
//        builder.addInterceptor(httpLoggingInterceptor);

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
//                .client(builder.build())
                .build();
    }
    public  static synchronized MyClient getInstance()
    {
        if (myClient==null)
        {
            myClient=new MyClient();
        }
        return myClient;
    }
    public Myapi getMyapi()
    {
        return retrofit.create(Myapi.class);
    }
}
