package com.example.newsapionrecyclerview.screen.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    // цель сделать переключатель для фрагментов
    //    onFragmentList - true - отображаем список
    //                     false - один элемент

    //   решить проблему какой именно элемент виден



    public MainActivityViewModel(){}

    private MediatorLiveData<Boolean> onFragmentList = new MediatorLiveData<>();

    public LiveData<Boolean> getOnFragmentList() {
        return onFragmentList;
    }

    public void setOnFragmentList(Boolean flag) {
        onFragmentList.setValue(flag);

    }


}
