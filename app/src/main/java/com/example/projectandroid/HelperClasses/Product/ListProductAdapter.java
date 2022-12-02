package com.example.projectandroid.HelperClasses.Product;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.R;
import com.example.projectandroid.User.MProduct.ListProduct.DetailProduct;

import java.util.ArrayList;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ListProductViewHolder> {

    private Context context;
    ArrayList<ListProductHelperClass> listProductHelperClassArrayList;
    int singleData;
    SQLiteDatabase SQLdb;
    SqlDatabaseHelper db;

    public ListProductAdapter(Context context, int singleData, ArrayList<ListProductHelperClass> listProductHelperClassArrayList, SQLiteDatabase SQLdb) {
        this.context = context;
        this.singleData = singleData;
        this.listProductHelperClassArrayList = listProductHelperClassArrayList;
        this.SQLdb = SQLdb;
    }

    @NonNull
    @Override
    public ListProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater inflater = LayoutInflater.from(context);
       View view = inflater.inflate(R.layout.list_product_card_desgin, null);
       return new ListProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductViewHolder holder, int position) {

        final ListProductHelperClass listProductHelperClass = listProductHelperClassArrayList.get(position);
        byte[] image = listProductHelperClass.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            holder.TextNProduct.setText(listProductHelperClass.getName());
            holder.TextQProduct.setText(listProductHelperClass.getQuality());
            holder.ImageProduct.setImageBitmap(bitmap);


            holder.DetailProduct_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db = new SqlDatabaseHelper(context);
                    Cursor cursor = db.getIdProduct_Product(listProductHelperClass.getName());
                    String product_ID = null;
                    if (cursor == null) {
                        Toast.makeText(context, "Không có ID ", Toast.LENGTH_SHORT).show();
                    } else {
                        while (cursor.moveToNext()) {
                            product_ID = cursor.getString(0);
                        }
                    }
                    Intent intent = new Intent(context, DetailProduct.class);
                    intent.putExtra("Id_Product", product_ID);
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return listProductHelperClassArrayList.size();
    }

    public class ListProductViewHolder extends RecyclerView.ViewHolder{

        ImageView ImageProduct,DetailProduct_btn;
        TextView TextNProduct,TextQProduct;

        public ListProductViewHolder(@NonNull View itemView) {
            super(itemView);

            DetailProduct_btn = itemView.findViewById(R.id.detail_Product);
            TextNProduct = itemView.findViewById(R.id.Txv_NameProduct);
            TextQProduct = itemView.findViewById(R.id.Txv_QualityProduct);
            ImageProduct = itemView.findViewById(R.id.img_Product);

        }
    }
}
