package com.example.projectandroid.User.MAnalysis;

import static com.example.projectandroid.User.DashBoard.idUser;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Sales extends AppCompatActivity {

    BarChart barChart_sales;

    ArrayList<String> itemYear;
    AutoCompleteTextView SelectYear;
    ArrayAdapter adapterItemYear;

    SqlDatabaseHelper db;

    ArrayList<BarEntry> barEntryArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        db = new SqlDatabaseHelper(this);

        barChart_sales = findViewById(R.id.bart_char_sales);
        SelectYear = findViewById(R.id.sales_select_year);

        SelectYear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setData();
                barChar();
            }
        });

        fillSelectYear();
    }

    private void setData() {

        barEntryArrayList = new ArrayList<>();

        String gDate = SelectYear.getText().toString();
        if(gDate.isEmpty()){

        }else{
            String strSplitGYear[] = gDate.split("-");
            String sYear = strSplitGYear[0];
            String eYear = strSplitGYear[1];

            for (int i = Integer.parseInt(sYear); i<= Integer.parseInt(eYear); i++){

                String Date = "12/13/" + i;

                Cursor cursor = db.readDataBarChart_Bill(Integer.valueOf(idUser), Date);
                if(cursor.getCount() == 0){
                }else{

                    while(cursor.moveToNext()){
                        String strSplit[] = cursor.getString(0).split("/");
                        String gYear = strSplit[2];
                        String gPrice = cursor.getString(1);
                        barEntryArrayList.add(new BarEntry(Integer.parseInt(gYear), Float.parseFloat(gPrice)));
                    }
                }
            }
        }
    }

    private void barChar() {

        BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Tiền Bán Được");

        BarData barData = new BarData(barDataSet);

        barChart_sales.setFitBars(true);
        barChart_sales.setData(barData);
        barChart_sales.getDescription().setText("Doanh So");
        barChart_sales.animateY(2000);
    }

    private void fillSelectYear() {

        Date dateAndTime = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        String gCDay = dateFormat.format(dateAndTime);
        Integer CDay = Integer.parseInt(gCDay);

        itemYear = new ArrayList<>();
        for (int i = 2014; i<=CDay; i++){
            i = i + 2;
            String secYear = String.valueOf(i + 2);
            itemYear.add(i + "-" + secYear);
        }

        adapterItemYear = new ArrayAdapter<String>(this, R.layout.list_item_dropmenu,itemYear);
        SelectYear.setAdapter(adapterItemYear);

    }
}