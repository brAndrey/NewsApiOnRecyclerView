package com.example.newsapionrecyclerview.modeldata;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

// данный класс необходим для парсинга JSON данных полученных через API

public class NewsArray implements Serializable {

    // параметр articles - берётся из JSON файла
    @SerializedName("articles")
    private List<NewsModel> newsElArray;


    public List<NewsModel> getNewsArray() {
        return newsElArray;
    }

}
