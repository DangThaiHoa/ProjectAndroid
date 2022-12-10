package com.example.projectandroid.User.Profile;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Patterns;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectandroid.HelperClasses.Product.ListProduct.GetImageProductClass;
import com.example.projectandroid.HelperClasses.Profile.GetImageUserClass;
import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.ProgessLoading;
import com.example.projectandroid.R;
import com.example.projectandroid.SessionManager;
import com.example.projectandroid.common.LoginSignUp.SignUp;
import com.example.projectandroid.common.LoginSignUp.StartUpScreen;
import com.example.projectandroid.common.LoginSignUp.VerifySignUp;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EditProfile extends AppCompatActivity {

    String[] itemGender = {"Nam", "Nữ", "Khác"};
    AutoCompleteTextView eGender;
    ArrayAdapter<String> adapterItemGender;

    ImageView btnBack, eImageUser;
    TextInputEditText eName, eUserName, eEmail, ePhone, eDate;
    Button submitBtn, deleteBtn, ConfirmBtnDia, CancelBtnDia;
    TextView ContentDia, createDayUser;
    CardView ChangeImage;

    Dialog dialog;

    ActivityResultLauncher<String> getImage;
    ActivityResultLauncher<Intent> getCamera;

    Uri imageFilePath,camUri;
    Bitmap imageToStore;

    SqlDatabaseHelper db;
    SessionManager sessionManager;
    ProgessLoading progessLoading;

    String idUser, gName, gUserName, gEmail, gPhone, gDate, gGender;

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
        createDayUser = findViewById(R.id.createDay_User);
        adapterItemGender = new ArrayAdapter<String>(this, R.layout.list_item_dropmenu, itemGender);
        eGender.setAdapter(adapterItemGender);

        idUser = sessionManager.setID();

        eGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterItemGender = new ArrayAdapter<String>(EditProfile.this, R.layout.list_item_dropmenu, itemGender);
                eGender.setAdapter(adapterItemGender);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String gExistsUserName = null;
                String gExistsEmail = null;
                String gExistsPhone = null;

                Cursor cursorReadData = db.readAllData_User(Integer.valueOf(idUser));
                while (cursorReadData.moveToNext()) {

                    gExistsUserName = cursorReadData.getString(1);
                    gExistsEmail = cursorReadData.getString(4);
                    gExistsPhone = cursorReadData.getString(5);

                }

                Date gCurrentDay = null;
                Date gDATE = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/d/yyyy");
                Calendar c = Calendar.getInstance();
                String Day = eDate.getText().toString();
                String gCDate = dateFormat.format(c.getTime());
                try {
                    gCurrentDay = dateFormat.parse(gCDate);
                    gDATE = dateFormat.parse(Day);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                gName = eName.getText().toString();
                gUserName = eUserName.getText().toString();
                gEmail = eEmail.getText().toString();
                gPhone = ePhone.getText().toString();
                gDate = eDate.getText().toString();
                gGender = eGender.getText().toString();

                if (gName.isEmpty() || gUserName.isEmpty() || gEmail.isEmpty() || gPhone.isEmpty() || gDate.isEmpty() || gGender.isEmpty()) {

                    Toast.makeText(EditProfile.this, "Vui Lòng Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();

                }else{

                    if (Patterns.EMAIL_ADDRESS.matcher(gEmail).matches()) {

                        if (gDATE.before(gCurrentDay)) {

                            if (gUserName.equals(gExistsUserName)) {

                                if (gEmail.equals(gExistsEmail)) {

                                    if (gPhone.equals(gExistsPhone)) {

                                        insertIfImageNull();

                                    }else{

                                        Boolean phoneCheckResult = db.checkPhoneExist_Users(gPhone);
                                        if (phoneCheckResult == true) {

                                            Toast.makeText(EditProfile.this, "Số Điện Thoại đã tồn tại. \nVui lòng Nhập Số Điện Thoại Khác", Toast.LENGTH_LONG).show();
                                            eName.forceLayout();

                                        }else{

                                            insertIfImageNull();

                                        }

                                    }

                                }else{

                                    Boolean emailCheckResult = db.checkEmailExist_Users(gEmail);
                                    if (emailCheckResult == true) {

                                        Toast.makeText(EditProfile.this, "Email đã tồn tại. \nVui lòng Nhập Email Khác", Toast.LENGTH_LONG).show();
                                        eName.forceLayout();

                                    }else{

                                        insertIfImageNull();

                                    }

                                }

                            }else{

                                Boolean userCheckResult = db.checkUsernameExist_Users(gUserName);
                                if (userCheckResult == true) {

                                    Toast.makeText(EditProfile.this, "Tên Đăng Nhập đã tồn tại. \nVui lòng Nhập Tên Đăng Nhập Khác", Toast.LENGTH_LONG).show();
                                    eName.forceLayout();

                                }else{

                                    insertIfImageNull();

                                }

                            }

                        }else{

                            Toast.makeText(EditProfile.this, "Vui Lòng Nhập Tuổi Hợp Lệ", Toast.LENGTH_SHORT).show();

                        }

                    }else{

                        Toast.makeText(EditProfile.this, "Vui Lòng Nhập Đúng Định Dạng Email", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });

        ShowDiaLog();
        btnBack();
        deleteBtn();
        ChangeImage();
        eDate();
        readAllData();
        deleteBtn();

    }

    private void insertIfImageNull(){

        if (imageToStore == null) {

            Cursor cursor = db.readImageExists_User(Integer.valueOf(idUser));
            if (cursor.getCount() == 0) {

                imageToStore = BitmapFactory.decodeResource(getResources(), R.drawable.ic_baseline_account_circle_24);
                Boolean resultUpdateData = db.updateData_Users(Integer.parseInt(idUser), gUserName, gName, gEmail, gPhone, gGender, gDate, new GetImageUserClass(imageToStore));
                if (resultUpdateData == true) {

                    progessLoading.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(EditProfile.this, Profile.class);
                            startActivity(intent);
                            Toast.makeText(EditProfile.this, "Sửa Hồ Sơ Thành Công", Toast.LENGTH_SHORT).show();
                            progessLoading.dismiss();
                        }
                    }, 2000);
                } else {

                    Toast.makeText(EditProfile.this, "Sửa Hồ Sơ Thất Bại", Toast.LENGTH_SHORT).show();

                }
            } else {

                while (cursor.moveToNext()){
                    byte[] image = cursor.getBlob(0);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                    imageToStore = bitmap;
                }
                Boolean resultUpdateData = db.updateData_Users(Integer.parseInt(idUser), gUserName, gName, gEmail, gPhone, gGender, gDate, new GetImageUserClass(imageToStore));
                if (resultUpdateData == true) {

                    progessLoading.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(EditProfile.this, Profile.class);
                            startActivity(intent);
                            Toast.makeText(EditProfile.this, "Sửa Hồ Sơ Thành Công", Toast.LENGTH_SHORT).show();
                            progessLoading.dismiss();
                        }
                    }, 2000);
                } else {

                    Toast.makeText(EditProfile.this, "Sửa Hồ Sơ Thất Bại", Toast.LENGTH_SHORT).show();

                }
            }

        } else {

            Cursor cursor = db.readImageExists_User(Integer.valueOf(idUser));
            if (cursor.getCount() == 0) {

                imageToStore = BitmapFactory.decodeResource(getResources(), R.drawable.ic_baseline_account_circle_24);
                Boolean resultUpdateData = db.updateData_Users(Integer.parseInt(idUser), gUserName, gName, gEmail, gPhone, gGender, gDate, new GetImageUserClass(imageToStore));
                if (resultUpdateData == true) {

                    progessLoading.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(EditProfile.this, Profile.class);
                            startActivity(intent);
                            Toast.makeText(EditProfile.this, "Sửa Hồ Sơ Thành Công", Toast.LENGTH_SHORT).show();
                            progessLoading.dismiss();
                        }
                    }, 2000);
                } else {

                    Toast.makeText(EditProfile.this, "Sửa Hồ Sơ Thất Bại", Toast.LENGTH_SHORT).show();

                }
            } else {

                Boolean resultUpdateData = db.updateData_Users(Integer.parseInt(idUser), gUserName, gName, gEmail, gPhone, gGender, gDate, new GetImageUserClass(imageToStore));
                if (resultUpdateData == true) {

                    progessLoading.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(EditProfile.this, Profile.class);
                            startActivity(intent);
                            Toast.makeText(EditProfile.this, "Sửa Hồ Sơ Thành Công", Toast.LENGTH_SHORT).show();
                            progessLoading.dismiss();
                        }
                    }, 2000);
                } else {

                    Toast.makeText(EditProfile.this, "Sửa Hồ Sơ Thất Bại", Toast.LENGTH_SHORT).show();

                }
            }
        }

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
            if(cursor.getBlob(8) == null){
                eImageUser.setImageResource(R.drawable.ic_baseline_account_circle_24);
            }else{
                byte[] image = cursor.getBlob(8);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                eImageUser.setImageBitmap(bitmap);
            }
            createDayUser.setText(cursor.getString(9));
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
                Boolean resultDeleteData = db.deleteAccount_User(Integer.valueOf(idUser));
                if(resultDeleteData == true){
                    Intent intent = new Intent(getApplicationContext(), StartUpScreen.class);
                    startActivity(intent);
                    sessionManager.setLogin(false);
                    dialog.dismiss();
                }else{
                    Toast.makeText(EditProfile.this, "Xóa Tài Khoản Thất Bại", Toast.LENGTH_SHORT).show();
                }

            }
        });

        CancelBtnDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
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