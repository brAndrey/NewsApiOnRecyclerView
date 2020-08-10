package com.example.newsapionrecyclerview.network.model;

import androidx.annotation.StringDef;

@StringDef ({NetworkRequest.NEWS_REQUEST})
public @interface NetworkRequest {

    String NEWS_REQUEST = "news";
}
