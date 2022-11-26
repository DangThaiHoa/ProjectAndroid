package com.example.projectandroid.User.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.projectandroid.R;
import com.example.projectandroid.User.MShopping.CreateBill.CreateBill;
import com.example.projectandroid.common.LoginSignUp.StartUpScreen;

public class Profile extends AppCompatActivity {

    ImageView btnBack;
    CardView Setting, changePassword, Information, ChangeImage;
    Button editProfile, ConfirmBtnDia, CancelBtnDia;
    TextView ContentDia;
    
    RelativeLayout btnLogout;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_profile);

        btnBack = findViewById(R.id.back_btn);
        Setting = findViewById(R.id.card_icon_Setting_profile);
        changePassword = findViewById(R.id.card_icon_Password_profile);
        Information = findViewById(R.id.card_icon_Info_profile);
        btnLogout = findViewById(R.id.logout_btn);
        editProfile = findViewById(R.id.btn_Edit_profile);
        ChangeImage = findViewById(R.id.card_Image_profile);

        ChangeImage();
        btnBack();
        Setting();
        changePassword();
        Information();
        btnLogout();
        editProfile();
        ShowDiaLog();
    }

    public void ShowDiaLog() {

        dialog = new Dialog(Profile.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_dialog));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        ConfirmBtnDia = dialog.findViewById(R.id.Confirm_dialog_btn);
        CancelBtnDia = dialog.findViewById(R.id.Cancel_dialog_btn);
        ContentDia = dialog.findViewById(R.id.tv_Content_dialog);

        ConfirmBtnDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartUpScreen.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        CancelBtnDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    private void ChangeImage() {

        ChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(Profile.this, view);
                popupMenu.inflate(R.menu.edit_image_menu);
                popupMenu.show();
            }
        });

    }

    private void editProfile() {

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditProfile.class);
                intent.putExtra("NameProfile","QuafBanhMi");
                intent.putExtra("EmailProfile","quafbanhmi@gmail.com");
                intent.putExtra("PhoneProfile","0903209212");
                intent.putExtra("ImageProfile",R.drawable.image_test);
                startActivity(intent);
            }
        });

    }

    private void btnLogout() {

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentDia.setText("Bạn có chắc chắn muốn đăng xuất!?");
                dialog.show();
            }
        });


    }

    private void Information() {
    }

    private void changePassword() {

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChangePassword.class);
                startActivity(intent);
            }
        });

    }

    private void Setting() {
    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile.super.onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_image_menu,menu);
        return true;
    }
}