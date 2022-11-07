package com.example.projectandroid.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.projectandroid.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.example.projectandroid.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.example.projectandroid.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.projectandroid.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.projectandroid.HelperClasses.HomeAdapter.MostViewAdapter;
import com.example.projectandroid.HelperClasses.HomeAdapter.MostViewHelperClass;
import com.example.projectandroid.MainActivity;
import com.example.projectandroid.R;
import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.GenericDeclaration;
import java.util.ArrayList;

public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 1f;

    RecyclerView featuredRecycle,mostviewRecycle,categoriesRecycle;
    RecyclerView.Adapter feaadapter,mvadapter,cateadapter;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;

    LinearLayout contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_dash_board);

        featuredRecycle = findViewById(R.id.featured_recycler);
        mostviewRecycle = findViewById(R.id.most_view_recycler);
        categoriesRecycle = findViewById(R.id.categories_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        navigationDrawer();

        featuredRecycle();
        mostviewRecycle();
        categoriesRecycle();
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

            case R.id.nav_all_categories:
                startActivity(new Intent(getApplicationContext(), AllCategories.class));
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