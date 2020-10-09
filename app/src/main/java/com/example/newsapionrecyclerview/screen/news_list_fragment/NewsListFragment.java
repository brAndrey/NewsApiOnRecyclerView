package com.example.newsapionrecyclerview.screen.news_list_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.newsapionrecyclerview.R;
import com.example.newsapionrecyclerview.modeldata.NewsModel;
import com.example.newsapionrecyclerview.network.SimpleApp;
import com.example.newsapionrecyclerview.repositories.ApiResponse;
import com.example.newsapionrecyclerview.repositories.DataRepozitories;

import java.util.List;

public class NewsListFragment extends Fragment {
 private    FragmentAdapterRV fragmentAdapterRV;

    private RecyclerView recyclerView;

    private  List<NewsModel> newsModelList = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_news, container,false);
        //return super.onCreateView(inflater, container, savedInstanceState);



// получаем данные из репозитория , без учёта жизненного цикла observeForever
//        DataRepozitories dataRepozitories = DataRepozitories.getInstance();
//        dataRepozitories.getData();

recyclerView = view.findViewById(R.id.recyclerViewFragment);

recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));



        NewsListFragmentViewModel viewModel =  ViewModelProviders.of(getActivity()).get(NewsListFragmentViewModel.class);

        viewModel.getData().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                if (apiResponse == null) {
                    // handle error here
                    return;
                }
                if (apiResponse.getError() == null) {
                    // call is successful
                    //Log.i(TAG, "Data response is " + apiResponse.);
                    List<NewsModel> apiModels = apiResponse.getNews(); //.body().getNewsArray();

                    if (apiModels != null) {
                        //notifyAboutNewItems(apiModels);
                        fragmentAdapterRV = new FragmentAdapterRV(apiModels);
                        recyclerView.setAdapter(fragmentAdapterRV);

                        Log.i(this.getClass().getSimpleName(), "apiModels size " + apiModels.size());
                        for (NewsModel elem : apiModels) {
                            Log.e("onResponse", elem.toString());
                        }
                    }

                } else {
                    // call failed.
                    Throwable e = apiResponse.getError();
                    //Toast.makeText(NewsListFragment.this, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    //Log.e(TAG, "Error is " + e.getLocalizedMessage());

                }
            }
        });




        return view;
    }

    private void notifyAboutNewItems(List<NewsModel> apiModels) {
        newsModelList.addAll(apiModels);
    }
}
