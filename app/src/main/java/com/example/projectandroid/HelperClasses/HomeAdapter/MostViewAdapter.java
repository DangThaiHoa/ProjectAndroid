package com.example.projectandroid.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectandroid.R;

import java.util.ArrayList;

public class MostViewAdapter extends RecyclerView.Adapter<MostViewAdapter.MostViewViewHolder>{

    ArrayList<MostViewHelperClass> mostviewLocation;

    public MostViewAdapter(ArrayList<MostViewHelperClass> mostviewLocation) {
        this.mostviewLocation = mostviewLocation;
    }

    @NonNull
    @Override
    public MostViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_card_design,parent,false);
        MostViewViewHolder mostViewViewHolder = new MostViewViewHolder(view);
        return mostViewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewViewHolder holder, int position) {
        MostViewHelperClass mostViewHelperClass = mostviewLocation.get(position);

        holder.image.setImageResource(mostViewHelperClass.getImage());
        holder.title.setText(mostViewHelperClass.getTitle());
        holder.desc.setText(mostViewHelperClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return mostviewLocation.size();
    }

    public static class MostViewViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, desc;

        public MostViewViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.mv_image);
            title = itemView.findViewById(R.id.mv_title);
            desc = itemView.findViewById(R.id.mv_desc);

        }
    }
}
