package com.example.newsapionrecyclerview.network;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.newsapionrecyclerview.utils.Constant;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory extends Application {

    private static final String LOG=ApiFactory.class.getName();

    private static volatile NewsApi newsApi;
    private static OkHttpClient sClient;

    @NonNull
    public static NewsApi getNewsApi() {
        Log.i(LOG," NewsApi ");
        NewsApi api = newsApi;
        if (api == null) {
            synchronized (NewsApi.class) {
                api = newsApi;
                if (api == null) {
                    api = newsApi = buildRetrofit().create(NewsApi.class);
                }
            }
        }
        return api;

    }

    @NonNull
    private static Retrofit buildRetrofit() {
        Log.i(LOG," buildRetrofit ");
        return new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URI)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient getClient() {
        Log.i(LOG," getClient ");
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (NewsApi.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = buildClient();
                }
            }
        }
        return client;
    }

    private static OkHttpClient buildClient() {
        Log.i(LOG," buildClient ");
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor())
                .addInterceptor(new ApiKeyInterceptor())
                .build();
    }


}
