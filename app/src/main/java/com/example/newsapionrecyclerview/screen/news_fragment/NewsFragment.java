package com.example.newsapionrecyclerview.screen.news_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.newsapionrecyclerview.R;
import com.example.newsapionrecyclerview.databinding.FragmentNewsBinding;
import com.example.newsapionrecyclerview.modeldata.NewsModel;

public class NewsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentNewsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);
        View view = binding.getRoot();


        NewsFragmentViewModel viewModelData = ViewModelProviders.of(getActivity()).get(NewsFragmentViewModel.class);

        viewModelData.getData().observe(this, new Observer<NewsModel>() {
            @Override
            public void onChanged(NewsModel newsModel) {
                binding.setNewsModel(newsModel);
            }
        });

        return view;


    }
}