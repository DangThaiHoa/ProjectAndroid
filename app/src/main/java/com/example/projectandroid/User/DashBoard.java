package com.example.projectandroid.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectandroid.ChannelNotification;
import com.example.projectandroid.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.example.projectandroid.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.example.projectandroid.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.projectandroid.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.projectandroid.HelperClasses.HomeAdapter.MostViewAdapter;
import com.example.projectandroid.HelperClasses.HomeAdapter.MostViewHelperClass;
import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.MainActivity;
import com.example.projectandroid.R;
import com.example.projectandroid.SessionManager;
import com.example.projectandroid.User.MShopping.ListPromotion.ListPromotion;
import com.example.projectandroid.User.Profile.Profile;
import com.example.projectandroid.common.LoginSignUp.StartUpScreen;
import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 1f;

    RecyclerView featuredRecycle,mostviewRecycle,categoriesRecycle;
    RecyclerView.Adapter feadapter,mvadapter,cateadapter;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    TextView menu_UserName;

    LinearLayout contentView;

    RelativeLayout btn_product, btn_shopping, btn_analysis;

    SqlDatabaseHelper db;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_dash_board);

        db = new SqlDatabaseHelper(this);

        sessionManager = new SessionManager(this);

        featuredRecycle = findViewById(R.id.featured_recycler);
        mostviewRecycle = findViewById(R.id.most_view_recycler);
        categoriesRecycle = findViewById(R.id.categories_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        menu_UserName = findViewById(R.id.app_name);
        contentView = findViewById(R.id.content);
        btn_product = findViewById(R.id.dashboard_product);
        btn_shopping = findViewById(R.id.dashboard_shopping);
        btn_analysis = findViewById(R.id.dashboard_analysis);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        String a = sessionManager.setID();
        menu_UserName.setText(a);

        navigationDrawer();

        btn_product();
        btn_shopping();
        btn_analysis();

        featuredRecycle();
        mostviewRecycle();
        categoriesRecycle();

        deletePromotion();
    }

    private void sendNotification(Integer promotion_id) {

        Intent intent = new Intent(this, ListPromotion.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        String gName = null;
        String gPercent = null;
        String gSDate = null;
        String gEDate = null;
        byte[] image;
        Bitmap bitmap = null;
        Cursor cursor = db.readAllData_Promotion(promotion_id);
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Không Có ID", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                gName = cursor.getString(2);
                gPercent = cursor.getString(4) + "%";
                gSDate = cursor.getString(6);
                gEDate = cursor.getString(7);
                image = cursor.getBlob(8);
                bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);;
            }

            Notification notification = new NotificationCompat.Builder(this, ChannelNotification.CHANNEL_ID)
                    .setSmallIcon(R.drawable.promotion)
                    .setLargeIcon(bitmap)
                    .setContentTitle("Khuyến Mãi Sản Phẩm '"+ gName  +"' Đã Hết Hạn")
                    .setContentText("Giảm: "+ gPercent +" / Ngày Bắt Đầu: "+ gSDate +" / Ngày Kết Thúc: " +gEDate)
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null))
                    .setColor(getResources().getColor(R.color.mainColor))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build();

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(getNotificationId(), notification);
        }
    }

    private int getNotificationId(){
        return  (int) new Date().getTime();
    }

    private void deletePromotion() {

        Date dateAndTime = Calendar.getInstance().getTime();
        Cursor cursor = db.readEndDay_Promotion();
        if (cursor.getCount() == 0){

        }else {
            while (cursor.moveToNext()) {
                Integer gID = cursor.getInt(0);
                String gEDate = cursor.getString(1);
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
                try {
                    Date gDate = dateFormat.parse(gEDate);
                    if (dateAndTime.after(gDate)) {
                        sendNotification(gID);
                        db.deleteData_Promotion(gID);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void btn_product() {
        btn_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, Product.class));
            }
            });
        }

    private void btn_shopping() {
        btn_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this, Shopping.class);
                startActivity(intent);
            }
        });
    }

    private void btn_analysis() {
        btn_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this, Analysis.class);
            startActivity(intent);
        }
    });
}

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(DashBoard.this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {

        drawerLayout.setScrimColor(Color.parseColor("#FF9800"));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                                           @Override
                                           public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                                               final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                                               final float offsetScale = 1 - diffScaledOffset;
                                               contentView.setScaleX(offsetScale);
                                               contentView.setScaleY(offsetScale);


                                               final float xOffset = drawerView.getWidth() * slideOffset;
                                               final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                                               final float xTranslation = xOffset - xOffsetDiff;
                                               contentView.setTranslationX(xTranslation);
                                           }
                                       }
        );

    }

    @Override
    public void onBackPressed(){

        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(), DashBoard.class));
                break;

            case R.id.nav_product:
                startActivity(new Intent(getApplicationContext(), Product.class));
                break;

            case R.id.nav_shopping:
                startActivity(new Intent(getApplicationContext(), Shopping.class));
                break;

            case R.id.nav_analysis:
                startActivity(new Intent(getApplicationContext(), Analysis.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(getApplicationContext(), Profile.class));
                break;
            case R.id.nav_logout:
                startActivity(new Intent(getApplicationContext(), StartUpScreen.class));
                sessionManager.setLogin(false);
                finish();

        }
        return true;
    }

    private void featuredRecycle() {

        featuredRecycle.setHasFixedSize(true);
        featuredRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocation = new ArrayList<>();

        featuredLocation.add(new FeaturedHelperClass(R.drawable.ls_startupimage,"Mcdonal's","Lorem ipsum dolor sit amet, consectetuer adipiscing elit."));
        featuredLocation.add(new FeaturedHelperClass(R.drawable.ls_startupimage,"Mcdonal's","Lorem ipsum dolor sit amet, consectetuer adipiscing elit."));
        featuredLocation.add(new FeaturedHelperClass(R.drawable.ls_startupimage,"Mcdonal's","Lorem ipsum dolor sit amet, consectetuer adipiscing elit."));

        feadapter = new FeaturedAdapter(featuredLocation);
        featuredRecycle.setAdapter(feadapter);

    }

    private void mostviewRecycle() {

        mostviewRecycle.setHasFixedSize(true);
        mostviewRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<MostViewHelperClass> mostviewLocation = new ArrayList<>();

        mostviewLocation.add(new MostViewHelperClass(R.drawable.ls_startupimage,"Mcdonal's","Lorem ipsum dolor sit amet, consectetuer adipiscing elit."));
        mostviewLocation.add(new MostViewHelperClass(R.drawable.ls_startupimage,"Mcdonal's","Lorem ipsum dolor sit amet, consectetuer adipiscing elit."));
        mostviewLocation.add(new MostViewHelperClass(R.drawable.ls_startupimage,"Mcdonal's","Lorem ipsum dolor sit amet, consectetuer adipiscing elit."));

        mvadapter = new MostViewAdapter(mostviewLocation);
        mostviewRecycle.setAdapter(mvadapter);

    }

    private void categoriesRecycle() {

        categoriesRecycle.setHasFixedSize(true);
        categoriesRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<CategoriesHelperClass> categoriesLocation = new ArrayList<>();

        categoriesLocation.add(new CategoriesHelperClass(R.drawable.ls_startupimage,"Mcdonal's", Color.parseColor("#FF9800")));
        categoriesLocation.add(new CategoriesHelperClass(R.drawable.ls_startupimage,"Mcdonal's",Color.parseColor("#FF9800")));
        categoriesLocation.add(new CategoriesHelperClass(R.drawable.ls_startupimage,"Mcdonal's",Color.parseColor("#FF9800")));

        cateadapter = new CategoriesAdapter(categoriesLocation);
        categoriesRecycle.setAdapter(cateadapter);
    }
}