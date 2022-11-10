package com.example.projectandroid.HelperClasses.Product;

import android.content.Context;
import android.graphics.Color;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectandroid.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.example.projectandroid.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.example.projectandroid.R;
import com.example.projectandroid.User.MProduct.ListProduct.ListProduct;

import java.util.ArrayList;
import java.util.List;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ListProductViewHolder> {

    private ListProductInterface listProductInterface;

    ArrayList<ListProductHelperClass> lProductLocation;

    public ListProductAdapter(ArrayList<ListProductHelperClass> lProductLocation, ListProductInterface listProductInterface) {
        this.lProductLocation = lProductLocation;
        this.listProductInterface = listProductInterface;
    }

    @NonNull
    @Override
    public ListProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_desgin_list_product,parent,false);
        ListProductViewHolder listProductViewHolder = new ListProductViewHolder(view);
        return listProductViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductViewHolder holder, int position) {

        ListProductHelperClass listProductHelperClass = lProductLocation.get(position);

        holder.ImageProduct.setImageResource(listProductHelperClass.getImage());
        holder.TextNProduct.setText(listProductHelperClass.getName());
        holder.TextQProduct.setText(listProductHelperClass.getQualityItem());

        holder.DetailProduct_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listProductInterface.onClickItemProduct(listProductHelperClass);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(lProductLocation != null){
            return lProductLocation.size();
        }
        return 0;
    }

    public class ListProductViewHolder extends RecyclerView.ViewHolder{

        ImageView ImageProduct,DetailProduct_btn;
        TextView TextNProduct,TextQProduct;

        public ListProductViewHolder(@NonNull View itemView) {
            super(itemView);

            DetailProduct_btn = itemView.findViewById(R.id.detail_Product);
            ImageProduct = itemView.findViewById(R.id.img_Product);
            TextNProduct = itemView.findViewById(R.id.Txv_NameProduct);
            TextQProduct = itemView.findViewById(R.id.Txv_QualityProduct);

        }
    }
}
