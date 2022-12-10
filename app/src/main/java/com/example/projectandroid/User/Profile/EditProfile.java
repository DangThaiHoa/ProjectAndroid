package com.example.projectandroid.User.Profile;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.ProgessLoading;
import com.example.projectandroid.R;
import com.example.projectandroid.SessionManager;
import com.example.projectandroid.common.LoginSignUp.StartUpScreen;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

public class EditProfile extends AppCompatActivity {

    String[] itemGender = {"Nam", "Nữ", "Khác"};
    AutoCompleteTextView eGender;
    ArrayAdapter<String> adapterItemGender;

    ImageView btnBack, eImageUser;
    TextInputEditText eName, eUserName, eEmail, ePhone, eDate;
    Button submitBtn, deleteBtn, ConfirmBtnDia, CancelBtnDia;
    TextView ContentDia;
    CardView ChangeImage;

    Dialog dialog;

    ActivityResultLauncher<String> getImage;
    ActivityResultLauncher<Intent> getCamera;

    Uri imageFilePath,camUri;
    Bitmap imageToStore;

    SqlDatabaseHelper db;
    SessionManager sessionManager;
    ProgessLoading progessLoading;

    String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_edit_profile);

        db = new SqlDatabaseHelper(this);

        sessionManager = new SessionManager(this);

        progessLoading = new ProgessLoading(this);

        getImage = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                try {
                    imageFilePath = result;
                    imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);

                    eImageUser.setImageBitmap(imageToStore);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        getCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                try {
                    imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(),camUri);
                    eImageUser.setImageURI(camUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        btnBack = findViewById(R.id.back_btn);
        submitBtn = findViewById(R.id.btn_Edit_profile);
        ChangeImage = findViewById(R.id.change_image_user_editProfile);
        deleteBtn = findViewById(R.id.delete_account_editProfile);
        eName = findViewById(R.id.name_user_editProfile);
        eUserName = findViewById(R.id.userName_user_editProfile);
        eEmail = findViewById(R.id.email_user_editProfile);
        ePhone = findViewById(R.id.phone_user_editProfile);
        eDate = findViewById(R.id.age_user_editProfile);
        eGender = findViewById(R.id.gender_user_editProfile);
        eImageUser = findViewById(R.id.Image_profile);
        adapterItemGender = new ArrayAdapter<String>(this, R.layout.list_item_dropmenu, itemGender);
        eGender.setAdapter(adapterItemGender);

        idUser = sessionManager.setID();

        submitBtn();
        ShowDiaLog();
        btnBack();
        deleteBtn();
        ChangeImage();
        eDate();
        readAllData();
    }

    private void readAllData() {

        Cursor cursor = db.readAllData_User(Integer.valueOf(idUser));
        while (cursor.moveToNext()){

            eUserName.setText(cursor.getString(1));
            eName.setText(cursor.getString(3));
            eEmail.setText(cursor.getString(4));
            ePhone.setText(cursor.getString(5));
            eGender.setText(cursor.getString(6));
            eDate.setText(cursor.getString(7));
//            if(cursor.getBlob(8) == null){
//                eImageUser.setImageResource(R.drawable.image_test);
//            }else{
//
//            }

        }
    }

    private void eDate() {

        eDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickDate();
            }
        });

    }

    private void PickDate(){
        String Day = eDate.getText().toString();
        String splitText[] = Day.split("/");
        int day = Integer.parseInt(splitText[1]);
        int month = Integer.parseInt(splitText[0]) - 1;
        int year = Integer.parseInt(splitText[2]);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                String date = i1 + "/" + i2 + "/" + i;
                eDate.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();

    }

    private void deleteBtn() {

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentDia.setText("Bạn Có Chắc Chắn Muốn Xóa Tài Khoản ?");
                dialog.show();
            }
        });

    }

    private void ChangeImage() {

        ChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(EditProfile.this, view);
                popupMenu.inflate(R.menu.edit_image_menu);
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){

                            case R.id.take_picture:
                                ContentValues values = new ContentValues();
                                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                                values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
                                camUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, camUri);
                                getCamera.launch(intent);
                                break;

                            case R.id.chose_picture:
                                getImage.launch("image/*");
                        }
                        return false;
                    }
                });
            }
        });
    }

    public void ShowDiaLog() {

        dialog = new Dialog(EditProfile.this);
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
}