package com.example.projectandroid.HelperClasses.SqlLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqlDatabaseHelper extends SQLiteOpenHelper {

    //Database
    private Context context;
    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATABASE_VERSION = 1;
    //Database


    //Login_Signup
    private static final String TABLE_USERS = "USERS";
    private static final String COLUMN_ID_USERS = "ID_Users";
    private static final String COLUMN_USERNAME = "UserName";
    private static final String COLUMN_PASSWORD = "Password";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_GENDER = "Gender";
    private static final String COLUMN_AGE = "Age";
    private static final String COLUMN_PHONE = "Phone";
    //Login_Signup


    //TypeProduct
    private static final String TABLE_TYPE_PRODUCT = "TYPE_PRODUCT";
    private static final String COLUMN_ID_TYPE_PRODUCT = "ID_Type_Product";
    private static final String COLUMN_TYPE_PRODUCT_NAME = "Type_Product_Name";
    private static final String COLUMN_TYPE_PRODUCT_DESCRIPTION = "Type_Product_Description";
    //TypeProduct



    public SqlDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    //Login_Signup
    private String Create_Table_Users =  "CREATE TABLE " + TABLE_USERS +
                                        "(" + COLUMN_ID_USERS + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        COLUMN_USERNAME + " VARCHAR UNIQUE, " +
                                        COLUMN_PASSWORD + " VARCHAR, " +
                                        COLUMN_NAME + " VARCHAR, " +
                                        COLUMN_EMAIL + " VARCHAR UNIQUE," +
                                        COLUMN_PHONE + " VARCHAR UNIQUE, " +
                                        COLUMN_GENDER + " VARCHAR, " +
                                        COLUMN_AGE + " VARCHAR);";
    //Login_Signup


    //TypeProduct
    private String Create_Table_Type_Product =  "CREATE TABLE " + TABLE_TYPE_PRODUCT +
            "(" + COLUMN_ID_TYPE_PRODUCT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TYPE_PRODUCT_NAME + " VARCHAR UNIQUE, " +
            COLUMN_TYPE_PRODUCT_DESCRIPTION + " TEXT);";
    //TypeProduct


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Login_Signup
        sqLiteDatabase.execSQL(Create_Table_Users);
        //Login_Signup


        //Table TypeProduct
        sqLiteDatabase.execSQL(Create_Table_Type_Product);
        //TypeProduct
    }


    //Login_Signup
    private String Update_Table_Users = "DROP TABLE IF EXISTS " + TABLE_USERS;
    //Login_Signup


    //TypeProduct
    private String Update_Table_Type_Product = "DROP TABLE IF EXISTS " + TABLE_TYPE_PRODUCT;
    //TypeProduct


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Users
        sqLiteDatabase.execSQL(Update_Table_Users);
        //Users


        //TypeProduct
        sqLiteDatabase.execSQL(Update_Table_Type_Product);
        //TypeProduct

        onCreate(sqLiteDatabase);
    }

    //Login_Signup
    public boolean insertData_Users(String username, String password, String name, String email, String phone, String gender, String age){
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

    public boolean checkUsername_Users(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from USERS where UserName = ?", new String[] {username});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkEmail_Users(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from USERS where Email = ?", new String[] {email});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkPhone_Users(String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from USERS where Phone = ?", new String[] {phone});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkUsernamePassword_Users(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from USERS where UserName = ? AND Password = ?", new String[] {username,password});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkEmailPassword_Users(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from USERS where Email = ? AND Password = ?", new String[] {email,password});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkPhonePassword_Users(String phone, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from USERS where Phone = ? AND Password = ?", new String[] {phone,password});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
    //Login_Signup


    //TypeProduct
    public boolean insertData_TypeProduct(String type_product_name, String type_product_description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TYPE_PRODUCT_NAME,type_product_name);
        contentValues.put(COLUMN_TYPE_PRODUCT_DESCRIPTION,type_product_description);

        long result = db.insert(TABLE_TYPE_PRODUCT,null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkNameTypeProduct_TypeProduct(String type_product_name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLE_TYPE_PRODUCT +" where "+ COLUMN_TYPE_PRODUCT_NAME +"  = ?", new String[] {type_product_name});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
    //TypeProduct


    //AddProduct
    public Cursor readTypeProduct_AddProduct(){
        String query= "Select * from "+ TABLE_TYPE_PRODUCT;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    //AddProduct

}
