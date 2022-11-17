package com.example.projectandroid.HelperClasses.Shopping.ListPromotion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projectandroid.R;

import java.util.ArrayList;

public class ListPromotionAdapter extends  RecyclerView.Adapter<ListPromotionAdapter.ListPromotionViewHolder>{

    private ListPromotionInterface listProductInterface;

    ArrayList<ListPromotionHelperClass> lPromotionLocation;

    public ListPromotionAdapter(ArrayList<ListPromotionHelperClass> lPromotionLocation, ListPromotionInterface listProductInterface) {
        this.lPromotionLocation = lPromotionLocation;
        this.listProductInterface = listProductInterface;
    }

    @NonNull
    @Override
    public ListPromotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_promotion_card_desgin,parent,false);
        ListPromotionViewHolder listPromotionViewHolder = new ListPromotionViewHolder(view);
        return listPromotionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListPromotionViewHolder holder, int position) {

        ListPromotionHelperClass listPromotionHelperClass = lPromotionLocation.get(position);

        holder.ImagePromotion.setImageResource(listPromotionHelperClass.getImage());
        holder.TextPPromotion.setText(listPromotionHelperClass.getPresent());
        holder.TextSPromotion.setText((CharSequence) listPromotionHelperClass.getStartDay());
        holder.TextEPromotion.setText((CharSequence) listPromotionHelperClass.getEndDay());

        holder.DetailPromotion_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listProductInterface.onClickItemPromotion(listPromotionHelperClass);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(lPromotionLocation != null){
            return lPromotionLocation.size();
        }
        return 0;
    }

    public class ListPromotionViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout DetailPromotion_btn;
        ImageView ImagePromotion;
        TextView TextPPromotion, TextSPromotion, TextEPromotion;

        public ListPromotionViewHolder(@NonNull View itemView) {
            super(itemView);

            DetailPromotion_btn = itemView.findViewById(R.id.Detail_promotion);
            ImagePromotion = itemView.findViewById(R.id.img_Promotion);
            TextPPromotion = itemView.findViewById(R.id.Txv_PercentPromotion);
            TextSPromotion = itemView.findViewById(R.id.Txv_startDayPromotion);
            TextEPromotion = itemView.findViewById(R.id.Txv_endDayPromotion);

        }
    }
}
