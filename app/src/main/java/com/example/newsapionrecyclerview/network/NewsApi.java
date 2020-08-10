package com.example.newsapionrecyclerview.network;

import com.example.newsapionrecyclerview.data.model.NewsArray;
import com.example.newsapionrecyclerview.data.model.NewsModel;
import com.example.newsapionrecyclerview.utils.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
//"http://newsapi.org/v2/top-headlines?country=ru&apiKey=124c03fe52cb42f58d1e562b6cc6a662";
    // ? - должен добавится автоматически т.к. есть параметры
    // & - должен добавится автоматически т.к. параметров 2

    @GET ("/v2/top-headlines")
    Call<List<NewsModel>> gatData (@Query("country") String ru,@Query("apiKey") String apiKey);

    @GET ("/v2/top-headlines")
    Call<NewsArray> gatNewsList (@Query("country") String ru, @Query("apiKey") String apiKey);


    @GET ("/v2/top-headlines")
    Call<List<NewsModel>> gatDataInter ();

    @GET ("/v2/top-headlines")
    Call<NewsArray> gatDataList ();





    @GET ("")
        //"apiKey="+ Constant.API_KEY)
        //Call<List<NewsModel>> gatData (@Query("key") String key, @Query("num") int count);
    Call<List<NewsModel>> gatData0 ();


}
