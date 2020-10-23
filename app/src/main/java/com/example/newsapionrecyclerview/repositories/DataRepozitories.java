package com.example.newsapionrecyclerview.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.newsapionrecyclerview.modeldata.NewsArray;
import com.example.newsapionrecyclerview.modeldata.NewsModel;
import com.example.newsapionrecyclerview.network.SimpleApp;

public class DataRepozitories {

    MediatorLiveData<NewsModel> lastPosition;
    MediatorLiveData<ApiResponse> apiResponse;

    private SimpleApp simpleApp;

    // инициируем синглтон
    private static DataRepozitories INSTANCE;

    private DataRepozitories() {
        lastPosition = new MediatorLiveData<NewsModel>();
        apiResponse     = new MediatorLiveData<>();
        //simpleApp = new SimpleApp;
    }

    public static DataRepozitories getInstance() {
        if (INSTANCE == null) {
            synchronized (DataRepozitories.class) {
                INSTANCE = new DataRepozitories();
            }
        }
        return INSTANCE;
    }
    //--------------------------

    public void getData() {
        new SimpleApp().gatData();
    }

    public LiveData<ApiResponse> getDataL() {
        apiResponse.addSource(new SimpleApp().getPosts(), new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiRespons) {
                apiResponse.setValue(apiRespons);
            }
        });
        return apiResponse;
    }


    public void setLastPosition(NewsModel newsModel) {
       lastPosition.setValue(newsModel);
    }

    public LiveData<NewsModel> getLastPosition(){
      return lastPosition;
    }
}
