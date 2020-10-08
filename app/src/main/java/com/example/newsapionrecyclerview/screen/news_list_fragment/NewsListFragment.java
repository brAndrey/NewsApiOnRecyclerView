package com.example.newsapionrecyclerview.screen.news_list_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newsapionrecyclerview.R;
import com.example.newsapionrecyclerview.modeldata.NewsModel;
import com.example.newsapionrecyclerview.network.SimpleApp;

import java.util.List;

public class NewsListFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_news, container,false);
        //return super.onCreateView(inflater, container, savedInstanceState);

        new SimpleApp().gatData();

        return view;
    }
}
