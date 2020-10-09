package com.example.newsapionrecyclerview.network;

import android.app.Application;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newsapionrecyclerview.modeldata.NewsArray;
import com.example.newsapionrecyclerview.modeldata.NewsModel;
import com.example.newsapionrecyclerview.repositories.ApiResponse;
import com.example.newsapionrecyclerview.utils.Constant;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SimpleApp extends Application {

    private static NewsApi newsApi;

    private static Retrofit retrofit;

    List<NewsModel> newsModelList;

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

    public static NewsApi gatApi() {
        return newsApi;
    }

    public void gatData() {
        Log.e(this.getClass().getSimpleName(), " " + new Object() {}.getClass().getEnclosingMethod().getName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

        if (newsApi == null) {
            onCreate();
        }

        Call<NewsArray> messeges = newsApi.gatNewsList(Constant.API_COUNTRY, Constant.API_KEY);

        messeges.enqueue(new Callback<NewsArray>() {
            @Override
            public void onResponse(Call<NewsArray> call, Response<NewsArray> response) {
                if (response.isSuccessful()) {
                    Log.i("onResponse", " 1  " + response.body());

                    // пересылаем данные в основной поток , там же и печатаем их
                    notifyAboutNewItems(response.body().getNewsArray());

                } else {
                    Log.i("onResponse code", " 2  " + response.code());

                    switch (response.code()) {
                        case 404:
                            // страница не найдена. можно использовать ResponseBody, см. ниже
                            break;
                        case 500:
                            // ошибка на сервере. можно использовать ResponseBody, см. ниже
                            break;
                    }
                    ResponseBody errorBody = response.errorBody();
                    try {
                        Log.i("errorBody", " " + errorBody.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsArray> call, Throwable t) {
                Log.i("onFailure", t.getMessage());
            }
        });

    }


    private void notifyAboutNewItems(List<NewsModel> newsArray) {
    // печать полученных данных в основном потоке

        if (newsArray != null) {
            Log.i("onResponse", "size " + newsArray.size());

            for (NewsModel elem : newsArray) {
                            String name = new Object() {}.getClass().getEnclosingMethod().getName();
                            Log.e(this.getClass().getSimpleName(), " " + name + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                            Log.i("elem", elem.toString());
            }
            newsModelList=newsArray;
        }
    }

//    public ApiResponse gatDataL() {
//        Log.e(this.getClass().getSimpleName(), " " + new Object() {}.getClass().getEnclosingMethod().getName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
//        if (newsModelList == null) {
//            gatData();
//        }
//        ApiResponse apiResponse = new ApiResponse(newsModelList);
//        Log.e(this.getClass().getSimpleName(), " " + new Object() {}.getClass().getEnclosingMethod().getName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
//
//        return apiResponse;
//    }


    public LiveData<ApiResponse> getPosts() {

        Log.e(this.getClass().getSimpleName(), " " + new Object() {}.getClass().getEnclosingMethod().getName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

        if (newsApi == null) {
            onCreate();
        }
        final MutableLiveData<ApiResponse> apiResponse = new MutableLiveData<>();

        Call<NewsArray> messeges = newsApi.gatNewsList(Constant.API_COUNTRY, Constant.API_KEY);

        messeges.enqueue(new Callback<NewsArray>() {
            @Override
            public void onResponse(Call<NewsArray> call, Response<NewsArray> response) {
                if (response.isSuccessful()) {
                    Log.i("onResponse", " 1  " + response.body());

                    apiResponse.postValue(new ApiResponse(response.body().getNewsArray()));

                    // пересылаем данные в основной поток, там же и печатаем их
                //    notifyAboutNewItems(response.body().getNewsArray());

                    // заносим данные в систему отслеживания
                    apiResponse.postValue(new ApiResponse(response.body().getNewsArray()));

                } else {
                    Log.i("onResponse code", " 2  " + response.code());

                    switch (response.code()) {
                        case 404:
                            // страница не найдена. можно использовать ResponseBody, см. ниже
                            break;
                        case 500:
                            // ошибка на сервере. можно использовать ResponseBody, см. ниже
                            break;
                    }
                    ResponseBody errorBody = response.errorBody();
                    try {
                        Log.i("errorBody", " " + errorBody.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsArray> call, Throwable t) {
                Log.i("onFailure", t.getMessage());
                apiResponse.postValue(new ApiResponse(t));
            }
        });
        return apiResponse;
    }
}