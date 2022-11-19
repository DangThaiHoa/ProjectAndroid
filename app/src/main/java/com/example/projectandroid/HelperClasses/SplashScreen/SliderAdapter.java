package com.example.projectandroid.HelperClasses.SplashScreen;

import android.animation.RectEvaluator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.projectandroid.R;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<SliderHelperClass> sliderHelperClass;

    public SliderAdapter(List<SliderHelperClass> sliderHelperClass) {
        this.sliderHelperClass = sliderHelperClass;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slides_layout, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setSliderData(sliderHelperClass.get(position));
    }

    @Override
    public int getItemCount() {
        return sliderHelperClass.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView desc;
        private ImageView image;

        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.slider_title);
            desc = itemView.findViewById(R.id.slider_desc);
            image = itemView.findViewById(R.id.slider_image);
        }

        void setSliderData(SliderHelperClass sliderHelperClass){

            title.setText(sliderHelperClass.getTitle());
            desc.setText(sliderHelperClass.getDesc());
            image.setImageResource(sliderHelperClass.getImage());

        }
    }

}
