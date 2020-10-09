package com.example.newsapionrecyclerview.repositories;

import com.example.newsapionrecyclerview.modeldata.NewsModel;

import java.util.List;

public class ApiResponse {
    public List<NewsModel> news;
    private Throwable error;

    public ApiResponse(List<NewsModel> news) {
        this.news = news;
        this.error = null;
    }

    public ApiResponse(Throwable error) {
        this.error = error;
        this.news = null;
    }

    public List<NewsModel> getNews() {
        return news;
    }

    public void setNews(List<NewsModel> news) {
        this.news = news;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
