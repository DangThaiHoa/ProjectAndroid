package com.example.projectandroid.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.projectandroid.HelperClasses.SplashScreen.OnBoardingItem;
import com.example.projectandroid.HelperClasses.SplashScreen.OnboardingAdapter;
import com.example.projectandroid.MainActivity;
import com.example.projectandroid.R;
import com.example.projectandroid.common.LoginSignUp.StartUpScreen;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OnBoarding extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonOnBoardingAction, skipBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_on_boarding);

        layoutOnboardingIndicators = findViewById(R.id.layoutOnboardingIndicators);
        buttonOnBoardingAction = findViewById(R.id.buttonOnboardingAction);
        skipBtn = findViewById(R.id.skipOnboardingAction);


        setupOnboardingItems();

        ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

        buttonOnBoardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()){
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                }else{
                    startActivity(new Intent(getApplicationContext(), StartUpScreen.class));
                    finish();
                }
            }
        });

        SkipOnBoarding();

    }

    private void SkipOnBoarding() {

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), StartUpScreen.class));
                finish();
            }
        });

    }

    private void setupOnboardingItems(){

        List<OnBoardingItem> onBoardingItems = new ArrayList<>();

        OnBoardingItem first = new OnBoardingItem();
        first.setTitle("Search Your Location");
        first.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.");
        first.setImage(R.drawable.search_place);

        OnBoardingItem second = new OnBoardingItem();
        second.setTitle("Make A Call");
        second.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.");
        second.setImage(R.drawable.make_a_call);

        OnBoardingItem third = new OnBoardingItem();
        third.setTitle("Add Missing Place");
        third.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.");
        third.setImage(R.drawable.add_missing_place);

        OnBoardingItem four = new OnBoardingItem();
        four.setTitle("Sit Back And Relax");
        four.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.");
        four.setImage(R.drawable.sit_back_and_relax);

        onBoardingItems.add(first);
        onBoardingItems.add(second);
        onBoardingItems.add(third);
        onBoardingItems.add(four);

        onboardingAdapter = new OnboardingAdapter(onBoardingItems);

    }

    private void setupOnboardingIndicators(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i < indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentOnboardingIndicator(int index){
        int childCount = layoutOnboardingIndicators.getChildCount();
        for(int i = 0; i < childCount; i++){
            ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if(i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_active)
                );
            }else{
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_inactive)
                );
            }
        }
        if(index == onboardingAdapter.getItemCount() - 1){
            Animation Anim = AnimationUtils.loadAnimation(this, R.anim.fade_out);
            buttonOnBoardingAction.setText("Bắt Đầu");
            skipBtn.setVisibility(View.INVISIBLE);
            skipBtn.setAnimation(Anim);
        }else{
            buttonOnBoardingAction.setText("Tiếp Tục");
                skipBtn.setVisibility(View.VISIBLE);
        }
    }
}