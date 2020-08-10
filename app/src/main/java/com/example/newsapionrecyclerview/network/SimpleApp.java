package com.example.newsapionrecyclerview.network;

import android.app.Application;
import android.util.Log;


import com.example.newsapionrecyclerview.data.model.NewsArray;
import com.example.newsapionrecyclerview.data.model.NewsModel;
import com.example.newsapionrecyclerview.network.model.NetworkRequest;
import com.example.newsapionrecyclerview.utils.Constant;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SimpleApp extends Application {

    private static NewsApi newsApi;

    private static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        // логизация
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        // log of

        retrofit = new Retrofit.Builder()
                //Базовая часть адреса
                .baseUrl(Constant.API_BASE_URI)
                //Конвертер, необходимый для преобразования JSON'а в объекты
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        newsApi = retrofit.create(NewsApi.class); //Создаем объект, при помощи которого будем выполнять запросы

    }
public static NewsApi gatApi(){
        return newsApi;
}

    public void gatData() {

        if (newsApi==null){onCreate();}

        Call<NewsArray> messeges = newsApi.gatNewsList(Constant.API_COUNTRY, Constant.API_KEY);

        messeges.enqueue(new Callback<NewsArray>() {
            @Override
            public void onResponse(Call<NewsArray> call, Response<NewsArray> response) {
                if (response.isSuccessful()) {
                    Log.i("onResponse", " 1  " + response.body());
                    List<NewsModel> newsModels = response.body().getNewsArray();

                    if (newsModels != null) {
                        Log.i("onResponse", "size " + newsModels.size());
                        for (NewsModel elem:newsModels){

                            Log.i("elem", elem.toString());

                        }
                    }

                } else {
                    Log.i("onResponse code", " 2  " + response.code());
                }
            }

            @Override
            public void onFailure(Call<NewsArray> call, Throwable t) {
                Log.i("onFailure", t.getMessage());
            }
        });
    }

}