package com.example.newsapionrecyclerview.utils;

import android.util.Log;

public class ServisClass {

    public static String stringLenth(String srt) {
        int k=30;
        if (srt != null){
        //Log.e("stringLenth", "  " + srt.length() + "  " + srt);
        if (srt.length() > k-1) {
          //  Log.e("", srt.substring(0, k));
            return srt.substring(0, k);
        }}
        return srt;
    }
}
