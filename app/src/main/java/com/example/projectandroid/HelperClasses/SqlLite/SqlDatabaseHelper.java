package com.example.projectandroid.HelperClasses.SqlLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqlDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "USERS";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_USERNAME = "UserName";
    private static final String COLUMN_PASSWORD = "Password";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_GENDER = "Gender";
    private static final String COLUMN_AGE = "Age";
    private static final String COLUMN_PHONE = "Phone";

    public SqlDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    private String Create_Table_Users =  "CREATE TABLE " + TABLE_USERS +
                                        "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        COLUMN_USERNAME + " VARCHAR UNIQUE, " +
                                        COLUMN_PASSWORD + " VARCHAR, " +
                                        COLUMN_NAME + " VARCHAR, " +
                                        COLUMN_EMAIL + " VARCHAR UNIQUE," +
                                        COLUMN_PHONE + " VARCHAR UNIQUE, " +
                                        COLUMN_GENDER + " VARCHAR, " +
                                        COLUMN_AGE + " VARCHAR);";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Create_Table_Users);
    }

    private String Update_Table_Users = "DROP TABLE IF EXISTS " + TABLE_USERS;

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String username, String password, String name, String email, String phone, String gender, String age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME,username);
        contentValues.put(COLUMN_PASSWORD,password);
        contentValues.put(COLUMN_NAME,name);
        contentValues.put(COLUMN_EMAIL,email);
        contentValues.put(COLUMN_PHONE,phone);
        contentValues.put(COLUMN_GENDER,gender);
        contentValues.put(COLUMN_AGE,age);

        long result = db.insert(TABLE_USERS,null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from USERS where UserName = ?", new String[] {username});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from USERS where Email = ?", new String[] {email});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkPhone(String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from USERS where Phone = ?", new String[] {phone});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from USERS where UserName = ? AND Password = ?", new String[] {username,password});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkEmailPassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from USERS where Email = ? AND Password = ?", new String[] {email,password});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkPhonePassword(String phone, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from USERS where Phone = ? AND Password = ?", new String[] {phone,password});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
}
