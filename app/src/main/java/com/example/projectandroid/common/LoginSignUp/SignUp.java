package com.example.projectandroid.common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.ProgessLoading;
import com.example.projectandroid.R;
import com.example.projectandroid.User.Profile.EditProfile;
import com.google.android.material.textfield.TextInputEditText;

public class SignUp extends AppCompatActivity {

    String[] itemGender = {"Nam", "Nữ", "Khác"};
    AutoCompleteTextView gender;
    ArrayAdapter<String> adapterItemGender;

    Button signup,login, ConfirmBtnDia, CancelBtnDia;
    TextView ContentDia;
    ImageView backbtn;
    TextInputEditText name, username, email, phone, password, rePassword;
    DatePicker age;

    SqlDatabaseHelper db;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_sign_up);

        db = new SqlDatabaseHelper(this);

        final ProgessLoading progessLoading = new ProgessLoading(SignUp.this);

        backbtn = findViewById(R.id.signup_back_button);
        login = findViewById(R.id.signup_login_button);
        signup = findViewById(R.id.sigun_button);
        name = findViewById(R.id.Name_signup);
        username = findViewById(R.id.UserNane_signup);
        email = findViewById(R.id.Email_signup);
        phone = findViewById(R.id.Phone_signup);
        password = findViewById(R.id.Password_signup);
        rePassword = findViewById(R.id.rePassword_signup);
        age = findViewById(R.id.Age_signup);
        gender = findViewById(R.id.Gender_signup);
        adapterItemGender = new ArrayAdapter<String>(this, R.layout.list_item_dropmenu, itemGender);
        gender.setAdapter(adapterItemGender);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String gName = name.getText().toString();
                String gUserName = username.getText().toString();
                String gPassword = password.getText().toString();
                String grePassword = rePassword.getText().toString();
                String gEmail = email.getText().toString();
                String gPhone = phone.getText().toString();
                String gGender = gender.getText().toString();


                int Day = age.getDayOfMonth();
                int Month = age.getMonth();
                int Year = age.getYear();
                String gAge = Year + "-" + Month + "-" + Day;

                if (gName.isEmpty() || gUserName.isEmpty() || gPassword.isEmpty() || grePassword.isEmpty() || gEmail.isEmpty() || gPhone.isEmpty() || gGender.isEmpty() || gAge.isEmpty()) {

                    Toast.makeText(SignUp.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();

                } else {

                    if (gPassword.equals(grePassword)) {

                        Boolean userCheckResult = db.checkUsername_Users(gUserName);
                        if (userCheckResult == true) {

                            Toast.makeText(SignUp.this, "Tên đăng nhập đã tồn tại. \nVui lòng Đăng Nhập", Toast.LENGTH_LONG).show();

                        } else {

                            Boolean emailCheckResult = db.checkEmail_Users(gEmail);
                            if (emailCheckResult == true) {

                                Toast.makeText(SignUp.this, "Email đã tồn tại. \nVui lòng Đăng Nhập", Toast.LENGTH_LONG).show();

                            } else {

                                Boolean phoneCheckResult = db.checkPhone_Users(gPhone);
                                if (phoneCheckResult == true) {

                                    Toast.makeText(SignUp.this, "Số Điện Thoại đã tồn tại. \nVui lòng Đăng Nhập", Toast.LENGTH_LONG).show();

                                } else {

                                    Boolean regResult = db.insertData_Users(gUserName, gPassword, gName, gEmail, gPhone, gGender, gAge);
                                    if (regResult == true) {

                                        progessLoading.show();

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                ContentDia.setText("Đăng Ký Thành Công, Bạn Có Muốn Chuyển Sang Đăng Nhập Không?");
                                                dialog.show();
                                                progessLoading.dismiss();
                                            }
                                        }, 2000);

                                    } else {

                                        progessLoading.show();

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(SignUp.this, "Đăng Ký Thất Bại", Toast.LENGTH_SHORT).show();
                                                progessLoading.dismiss();
                                            }
                                        }, 2000);

                                    }

                                }

                            }
                        }
                    } else {

                        Toast.makeText(SignUp.this, "Vui lòng nhập hai mật khẩu giống nhau", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        backbtn();
        login();
        ShowDiaLog();
    }

    public void ShowDiaLog() {

        dialog = new Dialog(SignUp.this);
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
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                dialog.dismiss();
                finish();
            }
        });

        CancelBtnDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    private void backbtn() {
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),StartUpScreen.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }
}