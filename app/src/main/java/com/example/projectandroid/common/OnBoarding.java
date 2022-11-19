package com.example.projectandroid.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.projectandroid.HelperClasses.SplashScreen.SliderAdapter;
import com.example.projectandroid.HelperClasses.SplashScreen.SliderHelperClass;
import com.example.projectandroid.R;

import java.util.ArrayList;

public class OnBoarding extends AppCompatActivity {

    ViewPager2 viewPager2;
    ArrayList<SliderHelperClass> sliderHelperClassArrayList;
    LinearLayout dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_on_boarding);

        viewPager2 = findViewById(R.id.slider);
        dots = findViewById(R.id.LLLdost);

        int image[] = {
                R.drawable.sit_back_and_relax,
                R.drawable.make_a_call,
                R.drawable.add_missing_place,
                R.drawable.search_place
        };

        String headings[] = {
                String.valueOf(R.string.first_slide_title),
                String.valueOf(R.string.second_slide_title),
                String.valueOf(R.string.third_slide_title),
                String.valueOf(R.string.four_slide_title),
        };

        String description[] = {
                String.valueOf(R.string.first_slide_description),
                String.valueOf(R.string.second_slide_description),
                String.valueOf(R.string.third_slide_description),
                String.valueOf(R.string.four_slide_description),
        };

        sliderHelperClassArrayList = new ArrayList<>();

        for (int i=0;i<image.length; i++){

            SliderHelperClass sliderHelperClass = new SliderHelperClass(image[i],headings[i],description[i]);
            sliderHelperClassArrayList.add(sliderHelperClass);

        }

        SliderAdapter sliderAdapter = new SliderAdapter(sliderHelperClassArrayList);
        viewPager2.setAdapter(sliderAdapter);


    }
}