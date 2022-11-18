package com.example.projectandroid.User.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectandroid.R;
import com.example.projectandroid.common.LoginSignUp.StartUpScreen;
import com.google.android.material.textfield.TextInputEditText;

public class EditProfile extends AppCompatActivity {

    ImageView btnBack;
    TextInputEditText EName, EEmail, EPhone;
    Button submitBtn, deleteBtn, ConfirmBtnDia, CancelBtnDia;
    TextView ContentDia;
    CardView ChangeImage;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_edit_profile);

        btnBack = findViewById(R.id.back_btn);
        EName = findViewById(R.id.Txt_username_Profile);
        EEmail = findViewById(R.id.Txt_email_Profile);
        EPhone = findViewById(R.id.Txt_phone_Profile);
        submitBtn = findViewById(R.id.btn_Submit_Edit_profile);
        deleteBtn = findViewById(R.id.btn_Delete_profile);
        ChangeImage = findViewById(R.id.card_Image_profile);

        Intent intent = getIntent();

        EName.setText(intent.getStringExtra("NameProfile"));
        EEmail.setText(intent.getStringExtra("EmailProfile"));
        EPhone.setText(intent.getStringExtra("PhoneProfile"));

        submitBtn();
        deleteBtn();
        ShowDiaLog();
        btnBack();
        ChangeImage();
    }

    private void ChangeImage() {

        ChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(EditProfile.this, view);
                popupMenu.inflate(R.menu.edit_image_menu);
                popupMenu.show();
            }
        });

    }

    public void ShowDiaLog() {

        dialog = new Dialog(EditProfile.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_dialog));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.ActivityAnim;

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

    private void deleteBtn() {

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentDia.setText("Bạn có chắc chắn muốn xóa tài khoản?!");
                dialog.show();
            }
        });


    }

    private void submitBtn() {

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditProfile.this, "Thay Đổi Thông Tin Thành Công", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
            }
        });

    }

    private void btnBack() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditProfile.super.onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_image_menu,menu);
        return true;
    }
}