package com.example.projectandroid.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.projectandroid.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.example.projectandroid.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.example.projectandroid.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.projectandroid.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.projectandroid.HelperClasses.HomeAdapter.MostViewAdapter;
import com.example.projectandroid.HelperClasses.HomeAdapter.MostViewHelperClass;
import com.example.projectandroid.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 1f;
    AlertDialog.Builder builder;

    RecyclerView featuredRecycle,mostviewRecycle,categoriesRecycle;
    RecyclerView.Adapter feaadapter,mvadapter,cateadapter;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;

    LinearLayout contentView;

    RelativeLayout btn_product, btn_shopping, btn_analysis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_dash_board);
        builder = new AlertDialog.Builder(this);

        featuredRecycle = findViewById(R.id.featured_recycler);
        mostviewRecycle = findViewById(R.id.most_view_recycler);
        categoriesRecycle = findViewById(R.id.categories_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);
        btn_product = findViewById(R.id.dashboard_product);
        btn_shopping = findViewById(R.id.dashboard_shopping);
        btn_analysis = findViewById(R.id.dashboard_analysis);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        navigationDrawer();

        btn_product();
        btn_shopping();
        btn_analysis();

        featuredRecycle();
        mostviewRecycle();
        categoriesRecycle();
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

        feaadapter = new FeaturedAdapter(featuredLocation);
        featuredRecycle.setAdapter(feaadapter);

    }

    private void mostviewRecycle() {

        mostviewRecycle.setHasFixedSize(true);
        mostviewRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<MostViewHelperClass> featuredLocation = new ArrayList<>();

        featuredLocation.add(new MostViewHelperClass(R.drawable.ls_startupimage,"Mcdonal's","Lorem ipsum dolor sit amet, consectetuer adipiscing elit."));
        featuredLocation.add(new MostViewHelperClass(R.drawable.ls_startupimage,"Mcdonal's","Lorem ipsum dolor sit amet, consectetuer adipiscing elit."));
        featuredLocation.add(new MostViewHelperClass(R.drawable.ls_startupimage,"Mcdonal's","Lorem ipsum dolor sit amet, consectetuer adipiscing elit."));

        mvadapter = new MostViewAdapter(featuredLocation);
        mostviewRecycle.setAdapter(mvadapter);

    }

    private void categoriesRecycle() {

        categoriesRecycle.setHasFixedSize(true);
        categoriesRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<CategoriesHelperClass> featuredLocation = new ArrayList<>();

        featuredLocation.add(new CategoriesHelperClass(R.drawable.ls_startupimage,"Mcdonal's", Color.parseColor("#FF9800")));
        featuredLocation.add(new CategoriesHelperClass(R.drawable.ls_startupimage,"Mcdonal's",Color.parseColor("#FF9800")));
        featuredLocation.add(new CategoriesHelperClass(R.drawable.ls_startupimage,"Mcdonal's",Color.parseColor("#FF9800")));

        cateadapter = new CategoriesAdapter(featuredLocation);
        categoriesRecycle.setAdapter(cateadapter);
    }
}