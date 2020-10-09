package com.example.newsapionrecyclerview.screen.news_list_fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapionrecyclerview.R;
import com.example.newsapionrecyclerview.databinding.FragmentListNewsBindingImpl;
import com.example.newsapionrecyclerview.databinding.FragmentListNewsItemBinding;
import com.example.newsapionrecyclerview.modeldata.NewsModel;

import java.util.List;

public class FragmentAdapterRV extends RecyclerView.Adapter<FragmentHolderRV> {
    private List<NewsModel> item;

    public FragmentAdapterRV(List<NewsModel> newsModelList) {
        this.item = newsModelList;
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

    }

    @Override
    public int getItemCount() {
        return item.size();
    }
}
