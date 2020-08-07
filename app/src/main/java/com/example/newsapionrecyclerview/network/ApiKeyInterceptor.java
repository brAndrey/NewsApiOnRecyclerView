package com.example.newsapionrecyclerview.network;

import android.util.Log;

import com.example.newsapionrecyclerview.utils.Constant;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


// Зачем нужен не понятно
// надо для идентификации

// Interceptor - Перехватчик
public class ApiKeyInterceptor implements Interceptor {

    //http://newsapi.org/v2/top-headlines?country=ru&apiKey=124c03fe52cb42f58d1e562b6cc6a662
    private static final String LOG=ApiKeyInterceptor.class.getName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.i(LOG," ApiKeyInterceptor ");
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("country",Constant.API_COUNTRY)
                .addQueryParameter("apiKey", Constant.API_KEY)
                .build();
        request = request.newBuilder().url(url).build();


        return chain.proceed(request);
    }
}
