package com.example.newsapionrecyclerview.screen.news_list_fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapionrecyclerview.repositories.ApiResponse;
import com.example.newsapionrecyclerview.repositories.DataRepozitories;

public class NewsListFragmentViewModel extends ViewModel {

    private MediatorLiveData<ApiResponse> apiResponseMLD;
    private DataRepozitories dataRepozitories = DataRepozitories.getInstance();

    public NewsListFragmentViewModel() {

        apiResponseMLD = new MediatorLiveData<>();
    }

    public LiveData<ApiResponse> getData() {
        dataRepozitories.getDataL().observeForever(apiResponse ->{
            apiResponseMLD.setValue(apiResponse);
        } );
//        apiResponse.addSource(dataRepozitories.getDataL(), new Observer<ApiResponse>() {
//            @Override
//            public void onChanged(@Nullable ApiResponse apiResp) {
//                //apiResponse.setNews(apiResponse);
//                apiResponse.setValue(apiResp);
//            }
//        });

        //apiResponse.hasActiveObservers().
        return apiResponseMLD;
    }

}
