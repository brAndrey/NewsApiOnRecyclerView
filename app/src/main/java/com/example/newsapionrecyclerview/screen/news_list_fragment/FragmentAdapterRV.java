package com.example.newsapionrecyclerview.screen.news_list_fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapionrecyclerview.R;
import com.example.newsapionrecyclerview.databinding.FragmentListNewsItemBinding;
import com.example.newsapionrecyclerview.modeldata.NewsModel;
import com.example.newsapionrecyclerview.screen.activity.MainActivityViewModel;

import java.util.List;

public class FragmentAdapterRV extends RecyclerView.Adapter<FragmentHolderRV> {
    private List<NewsModel> item;
    MainActivityViewModel viewModelUI;

    public FragmentAdapterRV(List<NewsModel> newsModelList, MainActivityViewModel viewModelUI) {
        this.item = newsModelList;
        this.viewModelUI=viewModelUI;

    }

    @NonNull
    @Override
    public FragmentHolderRV onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FragmentListNewsItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_news_item, parent, false);

        return new FragmentHolderRV(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentHolderRV holder, int position) {
        holder.bind(item.get(position));
        FragmentListNewsItemBinding binding= holder.getBinding();

        binding.setLictener(new ListFragmentListener() {
            @Override
            public void onTaskClicked(NewsModel newsModel) {
                Log.e("onTaskClicked"," пора открыть новость");
                viewModelUI.setOnFragmentList(false);
            }
        });

    }

    @Override
    public int getItemCount() {
        return item.size();
    }


}
