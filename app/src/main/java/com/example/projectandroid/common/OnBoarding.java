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
import java.util.List;

public class OnBoarding extends AppCompatActivity {

    private SliderAdapter setAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_on_boarding);

        setupSliderItems();

        ViewPager2 sliderViewPager = findViewById(R.id.slider);
        sliderViewPager.setAdapter(setAdapter);

    }

    private  void setupSliderItems(){

        List<SliderHelperClass> sliderHelperClass = new ArrayList<>();

        SliderHelperClass first = new SliderHelperClass();
        first.setTitle("Search Your Location");
        first.setDesc("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.");
        first.setImage(R.drawable.search_place);

        SliderHelperClass second = new SliderHelperClass();
        second.setTitle("Make A Call");
        second.setDesc("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.");
        second.setImage(R.drawable.make_a_call);

        SliderHelperClass third = new SliderHelperClass();
        third.setTitle("Add Missing Place");
        third.setDesc("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.");
        third.setImage(R.drawable.add_missing_place);

        SliderHelperClass four = new SliderHelperClass();
        four.setTitle("Sit Back And Relax");
        four.setDesc("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.");
        four.setImage(R.drawable.sit_back_and_relax);

        sliderHelperClass.add(first);
        sliderHelperClass.add(second);
        sliderHelperClass.add(third);
        sliderHelperClass.add(four);

        setAdapter = new SliderAdapter(sliderHelperClass);

    }
}