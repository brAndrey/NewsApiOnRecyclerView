package com.example.newsapionrecyclerview.screen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.example.newsapionrecyclerview.R;
import com.example.newsapionrecyclerview.screen.news_fragment.NewsFragment;
import com.example.newsapionrecyclerview.screen.news_list_fragment.NewsListFragment;


public class MainActivity extends AppCompatActivity {

    private static final String LOG= MainActivity.class.getName();
    private MainActivityViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);




        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment =fragmentManager.findFragmentById(R.id.fragmentConteiner);
        Fragment fragmentList = new NewsListFragment();

        if (fragment == null) {
            fragmentManager.beginTransaction().
                    add(R.id.fragmentConteiner, fragmentList)
                    .commit();
        }


        viewModel.getOnFragmentList().observe(this,initListFragment ->{
            Log.e(this.getClass().getSimpleName(), "getOnFragmentList  "+initListFragment);
            Fragment fragmentOneNews = new NewsFragment();
            //fragmentManager.beginTransaction().remove(fragmentList);
            fragmentManager.beginTransaction().replace(R.id.fragmentConteiner,fragmentOneNews).commit();
        } );




        // ********************
       // NetworkService.start(this);
        // ********************



    }
}