package com.example.newsapionrecyclerview.network;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.newsapionrecyclerview.data.model.NewsModel;
import com.example.newsapionrecyclerview.screen.MainActivity;
import com.example.newsapionrecyclerview.utils.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NetworkService   {

    private static final String LOG= MainActivity.class.getName();

    // https://devcolibri.com/getting-started-with-retrofit-in-android/

    public static void start() {

        List<NewsModel> newsModels = new ArrayList();
        try {

//            String tet= ApiFactory.getNewsApi()
//                    .gatDataInter()
//                    .execute()
//                    .message();

            newsModels = ApiFactory.getNewsApi()
                    .gatDataInter()
                    .execute()
                    .body();

            Log.i("newsModels", "Good");


        } catch (IOException e) {
            e.printStackTrace();
            Log.i(LOG, " Запрос не состоялся");
        }

        if (newsModels == null) {
            Log.i(LOG, " Запрос не null");
        } else {
            Log.i(LOG, " size " + newsModels.size());
        }
    }



}
