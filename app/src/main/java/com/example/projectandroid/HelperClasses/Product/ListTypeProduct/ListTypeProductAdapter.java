package com.example.projectandroid.HelperClasses.Product.ListTypeProduct;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectandroid.HelperClasses.Product.ListProduct.ListProductAdapter;
import com.example.projectandroid.HelperClasses.Product.ListProduct.ListProductHelperClass;
import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.R;

import java.util.ArrayList;

public class ListTypeProductAdapter extends RecyclerView.Adapter<ListTypeProductAdapter.ListTypeProductViewHolder> {

    private Context context;
    ArrayList<ListTypeProductHelperClass> listTypeProductHelperClassArrayList;
    int singleData;
    SQLiteDatabase SQLdb;
    SqlDatabaseHelper db;

    public ListTypeProductAdapter(Context context, int singleData, ArrayList<ListTypeProductHelperClass> listTypeProductHelperClassArrayList, SQLiteDatabase SQLdb) {
        this.context = context;
        this.singleData = singleData;
        this.listTypeProductHelperClassArrayList = listTypeProductHelperClassArrayList;
        this.SQLdb = SQLdb;
    }

    @NonNull
    @Override
    public ListTypeProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_type_product_card_desgin, null);
        return new ListTypeProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTypeProductAdapter.ListTypeProductViewHolder holder, int position) {

        final ListTypeProductHelperClass listTypeProductHelperClass = listTypeProductHelperClassArrayList.get(position);
        holder.TextNTypeProduct.setText(listTypeProductHelperClass.getNameTypeProduct());
        holder.TextDTypeProduct.setText(listTypeProductHelperClass.getDescTypeProduct());
    }

    @Override
    public int getItemCount() {
        return listTypeProductHelperClassArrayList.size();
    }

    public class ListTypeProductViewHolder extends RecyclerView.ViewHolder{

        TextView TextNTypeProduct,TextDTypeProduct;

        public ListTypeProductViewHolder(@NonNull View itemView){
            super(itemView);

            TextNTypeProduct = itemView.findViewById(R.id.Txv_TypeNameProduct);
            TextDTypeProduct = itemView.findViewById(R.id.Txv_DescTypeProduct);
        }
    }
}
