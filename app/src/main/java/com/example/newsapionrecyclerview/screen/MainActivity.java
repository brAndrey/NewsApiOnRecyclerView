package com.example.newsapionrecyclerview.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.newsapionrecyclerview.R;
import com.example.newsapionrecyclerview.network.NetworkService;
import com.example.newsapionrecyclerview.network.SimpleApp;
import com.example.newsapionrecyclerview.screen.news_list_fragment.NewsListFragment;


public class MainActivity extends AppCompatActivity {

    private static final String LOG= MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment =fragmentManager.findFragmentById(R.id.fragmentConteiner);
        if (fragment == null) {
            fragment = new NewsListFragment();
            fragmentManager.beginTransaction().
                    add(R.id.fragmentConteiner, fragment)
                    .commit();
        }

        // ********************
       // NetworkService.start(this);
        // ********************



    }
}