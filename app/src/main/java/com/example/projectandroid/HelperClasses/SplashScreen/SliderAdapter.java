package com.example.projectandroid.HelperClasses.SplashScreen;

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

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {

    ArrayList<SliderHelperClass> sliderHelperClassArrayList;

    public SliderAdapter(ArrayList<SliderHelperClass> sliderHelperClasses) {
        this.sliderHelperClassArrayList = sliderHelperClassArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slides_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SliderHelperClass sliderHelperClass = sliderHelperClassArrayList.get(position);

        holder.imageView.setImageResource(sliderHelperClass.image);
        holder.tvHeading.setText(sliderHelperClass.heading);
        holder.tvDesc.setText(sliderHelperClass.desc);

    }

    @Override
    public int getItemCount() {
        return sliderHelperClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView  imageView;
        TextView tvHeading, tvDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.slider_image);
            tvHeading = itemView.findViewById(R.id.slider_heading);
            tvDesc = itemView.findViewById(R.id.slider_desc);
        }
    }

}
