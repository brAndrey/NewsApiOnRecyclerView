package com.example.newsapionrecyclerview.screen.news_list_fragment;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.example.newsapionrecyclerview.utils.ServisClass;
import com.squareup.picasso.Picasso;

public class BindingAdapters {

    @BindingAdapter({"app:url"})
    public static void loadImage(ImageView view, String url){
        if(url!=""){
            Picasso.get().load(url).into(view);
        }
    }

    @BindingAdapter({"android:text"})
    public static void stringLenth(TextView textView,String string) {
        textView.setText(ServisClass.stringLenth(string));
    }
}
