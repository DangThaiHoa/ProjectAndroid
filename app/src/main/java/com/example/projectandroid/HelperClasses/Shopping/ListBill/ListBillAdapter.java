package com.example.projectandroid.HelperClasses.Shopping.ListBill;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectandroid.HelperClasses.Shopping.ListPromotion.ListPromotionAdapter;
import com.example.projectandroid.HelperClasses.Shopping.ListPromotion.ListPromotionHelperClass;
import com.example.projectandroid.HelperClasses.Shopping.ListPromotion.ListPromotionInterface;
import com.example.projectandroid.R;

import java.util.ArrayList;

public class ListBillAdapter extends  RecyclerView.Adapter<ListBillAdapter.ListBillViewHolder>{

    private ListBillInterface listBillInterface;

    ArrayList<ListBillHelperClass> lBillLocation;

    public ListBillAdapter(ArrayList<ListBillHelperClass> lBillLocation, ListBillInterface listBillInterface) {
        this.lBillLocation = lBillLocation;
        this.listBillInterface = listBillInterface;
    }

    @NonNull
    @Override
    public ListBillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bill_card_desgin,parent,false);
        ListBillViewHolder listBillViewHolder = new ListBillViewHolder(view);
        return listBillViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListBillViewHolder holder, int position) {

        ListBillHelperClass listBillHelperClass = lBillLocation.get(position);

        holder.ImageBill.setImageResource(listBillHelperClass.getImage());
        holder.TextNBill.setText(listBillHelperClass.getNameBill());
        holder.TextCreBill.setText(listBillHelperClass.getCreateDay());

        holder.DetailBill_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listBillInterface.onClickItemBill(listBillHelperClass);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(lBillLocation != null){
            return lBillLocation.size();
        }
        return 0;
    }

    public class ListBillViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout DetailBill_btn;
        ImageView ImageBill;
        TextView TextNBill, TextCreBill;

        public ListBillViewHolder(@NonNull View itemView) {
            super(itemView);

            DetailBill_btn = itemView.findViewById(R.id.Detail_bill);
            ImageBill = itemView.findViewById(R.id.img_Bill);
            TextNBill = itemView.findViewById(R.id.Txv_NameBill);
            TextCreBill = itemView.findViewById(R.id.Txv_CreateDay);
        }
    }
}

