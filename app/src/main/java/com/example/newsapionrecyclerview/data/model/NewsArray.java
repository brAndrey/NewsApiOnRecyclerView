package com.example.newsapionrecyclerview.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NewsArray implements Serializable {
    @SerializedName("articles")
    private List<NewsModel> newsElArray;


    public List<NewsModel> getNewsArray() {
        return newsElArray;
    }

}
