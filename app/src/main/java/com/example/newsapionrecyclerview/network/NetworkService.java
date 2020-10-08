package com.example.newsapionrecyclerview.network;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.newsapionrecyclerview.data.GsonHolder;
import com.example.newsapionrecyclerview.modeldata.NewsArray;

import java.io.IOException;

import okhttp3.Request;

public class NetworkService  {

    private static final String LOG= NetworkService.class.getName();

    private static final String REQUEST_KEY = "request";
    // https://devcolibri.com/getting-started-with-retrofit-in-android/

    public static void start(@NonNull Context context
     )  {

        Intent intent = new Intent(context, NetworkService.class);
        //intent.putExtra(REQUEST_KEY, GsonHolder.getGson().toJson(request));
        onHandleWork(intent);

    }
    @SuppressWarnings("unused")
    public NetworkService() {
        super();
    }


    protected static void onHandleWork(@NonNull Intent intent) {
        Log.i(LOG,"onHandleWork");
        Request request = GsonHolder.getGson().fromJson(intent.getStringExtra(REQUEST_KEY), Request.class);
        NewsArray newsModels = new NewsArray();
        try {

            newsModels = ApiFactory.getNewsApi()
                    //.gatDataInter()
                    .gatDataList()
                    .execute()
                    .body();

            Log.i("newsModels", "Good");

        } catch (IOException e) {
            e.printStackTrace();
        }


        Log.i(LOG, " Запрос не состоялся");


        if (newsModels == null) {
            Log.i(LOG, " Запрос не null");
        } else {

            Log.i(LOG, " size " + newsModels);
        }
    }
}
