package com.example.newsapionrecyclerview.screen.news_list_fragment;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapionrecyclerview.databinding.FragmentListNewsBindingImpl;
import com.example.newsapionrecyclerview.databinding.FragmentListNewsItemBinding;
import com.example.newsapionrecyclerview.modeldata.NewsModel;


public class FragmentHolderRV extends RecyclerView.ViewHolder {
    public FragmentListNewsItemBinding binding;

    public FragmentHolderRV(FragmentListNewsItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }


    public void bind(NewsModel newsModel){
        //Log.e(this.getClass().getSimpleName(), " " + new Throwable().getStackTrace()[0].getMethodName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

        binding.setNewsModel(newsModel);

        binding.executePendingBindings();

    }

}
