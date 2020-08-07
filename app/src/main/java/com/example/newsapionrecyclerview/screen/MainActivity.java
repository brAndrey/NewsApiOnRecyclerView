package com.example.newsapionrecyclerview.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.newsapionrecyclerview.BuildConfig;
import com.example.newsapionrecyclerview.R;
import com.example.newsapionrecyclerview.data.model.NewsModel;
import com.example.newsapionrecyclerview.network.NetworkService;
import com.example.newsapionrecyclerview.network.NewsApi;
import com.example.newsapionrecyclerview.utils.Constant;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String LOG= MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ********************
        NetworkService.start();
// ********************

        HttpLoggingInterceptor interceptor  = new HttpLoggingInterceptor();

        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        NewsApi newsApi = retrofit.create(NewsApi.class);

        Call<List<NewsModel>> messeges = newsApi.gatData(Constant.API_COUNTRY,Constant.API_KEY);

        messeges.enqueue(new Callback<List<NewsModel>>() {
            @Override
            public void onResponse(Call<List<NewsModel>> call, Response<List<NewsModel>> response) {

                if (response.isSuccessful()) {
                    Log.i("onResponse", "  " + response.body().size());
                } else {
                    Log.i("onResponse code", "  " + response.code());
                }
                Log.i("response ",response.body().toString() );
//                posts.addAll(response.body());
//                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<NewsModel>> call, Throwable t) {
                Log.i("onFailure", t.getMessage());
            }
        });


    }
}