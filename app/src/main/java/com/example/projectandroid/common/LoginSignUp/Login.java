package com.example.projectandroid.common.LoginSignUp;

import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.biometric.BiometricManager;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectandroid.HelperClasses.SqlLite.SqlDatabaseHelper;
import com.example.projectandroid.ProgessLoading;
import com.example.projectandroid.R;
import com.example.projectandroid.SessionManager;
import com.example.projectandroid.User.DashBoard;
import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.Executor;

public class Login extends AppCompatActivity {

    private static final int REQUEST_CODE = 2;
    ImageView backbtn, loginBio;

    TextInputEditText username, password;
    Button loginBtn,signupBtn;
    TextView forgetPassword, swAccount, textBio;

    String gID;

    SqlDatabaseHelper db;

    SessionManager sessionManager;

    String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_login);

        db = new SqlDatabaseHelper(this);

        final ProgessLoading progessLoading = new ProgessLoading(Login.this);

        sessionManager = new SessionManager(this);

        backbtn = findViewById(R.id.login_back_button);
        loginBtn = findViewById(R.id.button_login);
        signupBtn = findViewById(R.id.button_signup_login);
        username = findViewById(R.id.UserNameorEmail_login);
        password = findViewById(R.id.Password_login);
        loginBio = findViewById(R.id.bio_login);
        forgetPassword = findViewById(R.id.forgetPassword_button);
        swAccount = findViewById(R.id.sw_account);
        textBio = findViewById(R.id.text_bio);

        idUser = sessionManager.getID();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gUsername = username.getText().toString();
                String gPassword = password.getText().toString();

                if (gUsername.isEmpty() || gPassword.isEmpty()){

                    Toast.makeText(Login.this, "Vui L??ng Nh???p ?????y ????? Th??ng Tin", Toast.LENGTH_SHORT).show();

                }else{

                    Boolean resultUserName = db.checkUsernamePassword_Users(gUsername,gPassword);
                    Boolean resultEmail = db.checkEmailPassword_Users(gUsername,gPassword);
                    Boolean resultPhone = db.checkPhonePassword_Users(gUsername,gPassword);
                    if (resultUserName == true || resultEmail == true || resultPhone == true){

                        Cursor cursor = db.getId_User(gUsername,gUsername,gUsername);
                        while (cursor.moveToNext()){
                            gID = cursor.getString(0);
                        }
                        progessLoading.show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(Login.this, DashBoard.class);
                                startActivity(intent);
                                sessionManager.setId(gID);
                                sessionManager.setLogin(true);
                                progessLoading.dismiss();
                                finish();
                            }
                        },2000);

                    }else{

                        progessLoading.show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Login.this, "Sai T??n ????ng Nh???p Ho???c M???t Kh???u", Toast.LENGTH_SHORT).show();
                                progessLoading.dismiss();
                            }
                        },2000);

                    }

                }
            }
        });

        loginBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (idUser.equals("0") || idUser.equals("")) {

                    Toast.makeText(Login.this, "Vui L??ng ????ng Nh???p L???n ?????u Tr?????c Khi S??? D???ng V??n Tay", Toast.LENGTH_LONG).show();

                } else {

                    if(sessionManager.isTurnOnBio()){

                        checkSPBiometric();

                        String gUsername = username.getText().toString();

                        if (gUsername.isEmpty()) {

                            Toast.makeText(Login.this, "Vui L??ng Nh???p ?????y ????? Th??ng Tin", Toast.LENGTH_SHORT).show();

                        } else {

                            Boolean resultUserName = db.checkUsernameExist_Users(gUsername);
                            Boolean resultEmail = db.checkEmailExist_Users(gUsername);
                            Boolean resultPhone = db.checkPhoneExist_Users(gUsername);
                            if (resultUserName == true || resultEmail == true || resultPhone == true) {

                                Executor executor = ContextCompat.getMainExecutor(Login.this);
                                androidx.biometric.BiometricPrompt biometricPrompt = new androidx.biometric.BiometricPrompt(Login.this, executor, new androidx.biometric.BiometricPrompt.AuthenticationCallback() {
                                    @Override
                                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                                        super.onAuthenticationError(errorCode, errString);
                                    }

                                    @Override
                                    public void onAuthenticationSucceeded(@NonNull androidx.biometric.BiometricPrompt.AuthenticationResult result) {
                                        super.onAuthenticationSucceeded(result);
                                        Cursor cursor = db.getId_User(gUsername, gUsername, gUsername);
                                        while (cursor.moveToNext()) {
                                            gID = cursor.getString(0);
                                        }
                                        progessLoading.show();

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(Login.this, DashBoard.class);
                                                startActivity(intent);
                                                sessionManager.setId(gID);
                                                sessionManager.setLogin(true);
                                                progessLoading.dismiss();
                                                finish();
                                            }
                                        }, 2000);
                                    }

                                    @Override
                                    public void onAuthenticationFailed() {
                                        super.onAuthenticationFailed();
                                        Toast.makeText(Login.this, "V??n Tay Ho???c Khu??ng M???t Kh??ng Kh???p", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                androidx.biometric.BiometricPrompt.PromptInfo.Builder promptInfo = new androidx.biometric.BiometricPrompt.PromptInfo.Builder()
                                        .setTitle("X??c Th???c V??n Tay Ho???c Khu??ng M???t")
                                        .setNegativeButtonText("H???y");
                                biometricPrompt.authenticate(promptInfo.build());

                            } else {

                                Toast.makeText(Login.this, "Sai T??n ????ng Nh???p", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }else{

                        password.requestFocus();
                        loginBio.setVisibility(View.INVISIBLE);
                        loginBio.setAnimation(AnimationUtils.loadAnimation(Login.this, R.anim.fade_out));
                        textBio.setText("");
                        textBio.setAnimation(AnimationUtils.loadAnimation(Login.this, R.anim.fade_out));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                textBio.setText("Vui L??ng ????ng Nh???p B???ng M???t Kh???u\n V?? B???t Ch??? ????? X??c Th???c Sinh Tr???c H???c");
                                textBio.setTextColor(Color.rgb(255, 0, 0));
                                textBio.setTextSize(15);
                                textBio.setAnimation(AnimationUtils.loadAnimation(Login.this, R.anim.fade_in));
                            }
                        },500);
                    }
                }
            }
        });

        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().isEmpty()){
                    
                }else{

                    if( idUser.equals("0") || idUser.equals("")){

                    }else{
                        Toast.makeText(Login.this, "N???u b???n mu???n ?????i t??i kho???n kh??c h??y ???n v??o n??t ?????i t??i kho???n", Toast.LENGTH_SHORT).show();
                        swAccount.setTextColor(Color.rgb(255,0,0));
                    }
                }
            }
        });

        backbtn();
        signupBtn();
        forgetPassword();
        setOldAccount();
        swAccount();
    }

    private void swAccount() {

        swAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.setId("0");
                sessionManager.setLogin(false);
                sessionManager.setBio(false);
                Intent intent = new Intent(getApplicationContext(), StartUpScreen.class);
                startActivity(intent);
            }
        });

    }

    private void setOldAccount() {

        if(idUser.equals("0") || idUser.equals("")){

            username.setFocusableInTouchMode(true);
            swAccount.setVisibility(View.INVISIBLE);
            username.setText("");

        }else{

            username.setFocusableInTouchMode(false);
            swAccount.setVisibility(View.VISIBLE);
            Cursor cursor = db.setRememberUserName_User(Integer.valueOf(idUser));
            if(cursor.getCount() == 0){
                Toast.makeText(this, "Kh??ng C?? T??i Kho???n", Toast.LENGTH_SHORT).show();
            }else{
                while (cursor.moveToNext()){
                    username.setText(cursor.getString(0));
                }
            }

        }

    }

    private void forgetPassword() {

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ForgetPassword.class);
                startActivity(intent);
            }
        });

    }

    private void checkSPBiometric() {
        BiometricManager biometricManager = BiometricManager.from(Login.this);
        switch (biometricManager.canAuthenticate(BIOMETRIC_STRONG | DEVICE_CREDENTIAL)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(Login.this, "Thi???t B??? c???a B???n Kh??ng H??? Tr??? C???m Bi???n V??n Tay", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(Login.this, "Thi???t B??? c???a B???n Kh??ng H??? Tr??? C???m Bi???n V??n Tay Ho???c ??ang L???i", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                // Prompts the user to create credentials that your app accepts.
                final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
                enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG | DEVICE_CREDENTIAL);
                startActivityForResult(enrollIntent, REQUEST_CODE);
                break;
        }
    }

    private void signupBtn() {

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
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
}