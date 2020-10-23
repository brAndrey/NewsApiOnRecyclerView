package com.example.newsapionrecyclerview.screen.news_fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapionrecyclerview.modeldata.NewsModel;
import com.example.newsapionrecyclerview.repositories.ApiResponse;
import com.example.newsapionrecyclerview.repositories.DataRepozitories;

public class NewsFragmentViewModel extends ViewModel {

    private MediatorLiveData<NewsModel> newsModelMediatorLiveData;

    private DataRepozitories dataRepozitories = DataRepozitories.getInstance();

    private NewsModel lastNewsmodel;

    public NewsFragmentViewModel() {

        newsModelMediatorLiveData = new MediatorLiveData<>();

    }

    public LiveData<NewsModel> getData() {
        dataRepozitories.getLastPosition().observeForever(lastNewsModel ->{
            newsModelMediatorLiveData.setValue(lastNewsModel);
        } );

        return newsModelMediatorLiveData;
    }

}
