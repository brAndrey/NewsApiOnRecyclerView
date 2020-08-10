package com.example.newsapionrecyclerview.screen;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.newsapionrecyclerview.R;
import com.example.newsapionrecyclerview.network.SimpleApp;



public class MainActivity extends AppCompatActivity {

    private static final String LOG= MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new SimpleApp().gatData();


        // ********************
   //     NetworkService.start(this);
// ********************



    }
}