package com.example.projectandroid.HelperClasses.SqlLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.projectandroid.HelperClasses.Product.GetImageProductClass;

import java.io.ByteArrayOutputStream;

public class SqlDatabaseHelper extends SQLiteOpenHelper {

    //Database
    private Context context;
    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATABASE_VERSION = 1;
    //Database


    //Login_Signup
    private static final String TABLE_USERS = "USERS";
    private static final String COLUMN_ID_USERS = "ID_Users";
    private static final String COLUMN_USERNAME_USERS = "UserName";
    private static final String COLUMN_PASSWORD_USERS = "Password";
    private static final String COLUMN_NAME_USERS = "Name";
    private static final String COLUMN_EMAIL_USERS = "Email";
    private static final String COLUMN_GENDER_USERS = "Gender";
    private static final String COLUMN_AGE_USERS = "Age";
    private static final String COLUMN_PHONE_USERS = "Phone";
    //Login_Signup


    //TypeProduct
    private static final String TABLE_TYPE_PRODUCT = "TYPE_PRODUCT";
    private static final String COLUMN_ID_TYPE_PRODUCT = "ID_Type_Product";
    private static final String COLUMN_TYPE_PRODUCT_NAME = "Type_Product_Name";
    private static final String COLUMN_TYPE_PRODUCT_DESCRIPTION = "Type_Product_Description";
    //TypeProduct


    //AddProduct
    private static final String TABLE_PRODUCT = "PRODUCT";
    private static final String COLUMN_ID_PRODUCT = "ID_Product";
    private static final String COLUMN_PRODUCT_NAME = "Product_Name";
    private static final String COLUMN_PRODUCT_QUALITY = "Product_QUALITY";
    private static final String COLUMN_PRODUCT_UNIT = "Product_UNIT";
    private static final String COLUMN_PRODUCT_PRICE = "Product_PRICE";
    private static final String COLUMN_PRODUCT_IMAGE = "Product_IMAGE";
    private static final String F_PRODUCT_COLUMN_ID_TYPE_PRODUCT = "ID_TypeProduct";

    private ByteArrayOutputStream byteArrayOutputStreamProduct;
    private byte[] imageProductByte;
    //AddProduct



    public SqlDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    //Login_Signup
    private String Create_Table_Users =  "CREATE TABLE " + TABLE_USERS +
                                        "(" + COLUMN_ID_USERS + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        COLUMN_USERNAME_USERS + " TEXT UNIQUE, " +
                                        COLUMN_PASSWORD_USERS + " TEXT, " +
                                        COLUMN_NAME_USERS + " TEXT, " +
                                        COLUMN_EMAIL_USERS + " TEXT UNIQUE," +
                                        COLUMN_PHONE_USERS + " TEXT UNIQUE, " +
                                        COLUMN_GENDER_USERS + " TEXT, " +
                                        COLUMN_AGE_USERS + " TEXT);";
    //Login_Signup


    //TypeProduct
    private String Create_Table_Type_Product =  "CREATE TABLE " + TABLE_TYPE_PRODUCT +
            "(" + COLUMN_ID_TYPE_PRODUCT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TYPE_PRODUCT_NAME + " TEXT UNIQUE, " +
            COLUMN_TYPE_PRODUCT_DESCRIPTION + " TEXT);";
    //TypeProduct


    //AddProduct
    private String Create_Table_Product =  "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCT +
            "(" + COLUMN_ID_PRODUCT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_PRODUCT_NAME + " TEXT UNIQUE, " +
            COLUMN_PRODUCT_QUALITY + " TEXT, " +
            COLUMN_PRODUCT_UNIT + " TEXT, " +
            COLUMN_PRODUCT_PRICE + " REAL," +
            COLUMN_PRODUCT_IMAGE + " BLOB, " +
            F_PRODUCT_COLUMN_ID_TYPE_PRODUCT + " INTEGER," +
            "FOREIGN KEY ("+F_PRODUCT_COLUMN_ID_TYPE_PRODUCT+") REFERENCES "+TABLE_TYPE_PRODUCT+"("+COLUMN_ID_TYPE_PRODUCT+"));";
    //AddProduct


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Login_Signup
        sqLiteDatabase.execSQL(Create_Table_Users);
        //Login_Signup


        //TypeProduct
        sqLiteDatabase.execSQL(Create_Table_Type_Product);
        //TypeProduct


        //AddProduct
        sqLiteDatabase.execSQL(Create_Table_Product);
        //AddProduct
    }


    //Login_Signup
    private String Update_Table_Users = "DROP TABLE IF EXISTS " + TABLE_USERS;
    //Login_Signup


    //TypeProduct
    private String Update_Table_Type_Product = "DROP TABLE IF EXISTS " + TABLE_TYPE_PRODUCT;
    //TypeProduct


    //AddProduct
    private String Update_Table_Product = "DROP TABLE IF EXISTS " + TABLE_PRODUCT;
    //AddProduct


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Users
        sqLiteDatabase.execSQL(Update_Table_Users);
        //Users


        //TypeProduct
        sqLiteDatabase.execSQL(Update_Table_Type_Product);
        //TypeProduct


        //AddProduct
        sqLiteDatabase.execSQL(Update_Table_Product);
        //AddProduct

        onCreate(sqLiteDatabase);
    }

    //Login_Signup
    public boolean insertData_Users(String username, String password, String name, String email, String phone, String gender, String age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME_USERS,username);
        contentValues.put(COLUMN_PASSWORD_USERS,password);
        contentValues.put(COLUMN_NAME_USERS,name);
        contentValues.put(COLUMN_EMAIL_USERS,email);
        contentValues.put(COLUMN_PHONE_USERS,phone);
        contentValues.put(COLUMN_GENDER_USERS,gender);
        contentValues.put(COLUMN_AGE_USERS,age);

        long result = db.insert(TABLE_USERS,null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkUsername_Users(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLE_USERS +" where "+ COLUMN_USERNAME_USERS +" = ?", new String[] {username});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkEmail_Users(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLE_USERS +" where "+ COLUMN_EMAIL_USERS +" = ?", new String[] {email});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkPhone_Users(String phone){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLE_USERS +" where "+ COLUMN_PHONE_USERS +" = ?", new String[] {phone});


        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkUsernamePassword_Users(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLE_USERS +" where "+ COLUMN_USERNAME_USERS +" = ? AND "+ COLUMN_PASSWORD_USERS +" = ?", new String[] {username,password});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkEmailPassword_Users(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLE_USERS +" where "+ COLUMN_EMAIL_USERS +" = ? AND "+ COLUMN_PASSWORD_USERS +" = ?", new String[] {email,password});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkPhonePassword_Users(String phone, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLE_USERS +" where "+ COLUMN_PHONE_USERS +" = ? AND "+ COLUMN_PASSWORD_USERS +" = ?", new String[] {phone,password});

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
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLE_TYPE_PRODUCT +" where "+ COLUMN_TYPE_PRODUCT_NAME +"  = ?", new String[] {type_product_name});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
    //TypeProduct


    //AddProduct
    public Cursor readTypeProduct_Product(){
        String query= "Select * from "+ TABLE_TYPE_PRODUCT;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    public boolean insertData_Product(String product_name, String product_quality, String product_unit, String product_price, GetImageProductClass imageProduct, String type_product_id){

        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap imageStoreBitMap = imageProduct.getImage();

        byteArrayOutputStreamProduct = new ByteArrayOutputStream();
        imageStoreBitMap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamProduct);

        imageProductByte = byteArrayOutputStreamProduct.toByteArray();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_PRODUCT_NAME,product_name);
        contentValues.put(COLUMN_PRODUCT_QUALITY,product_quality);
        contentValues.put(COLUMN_PRODUCT_UNIT,product_unit);
        contentValues.put(COLUMN_PRODUCT_PRICE,product_price);
        contentValues.put(COLUMN_PRODUCT_IMAGE,imageProductByte);
        contentValues.put(F_PRODUCT_COLUMN_ID_TYPE_PRODUCT,type_product_id);

        long result = db.insert(TABLE_PRODUCT,null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getTypeProductID_Product(String type_product_name){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("Select * from "+ TABLE_TYPE_PRODUCT +" Where "+ COLUMN_TYPE_PRODUCT_NAME +" = ?", new String[] {type_product_name},null);
        }
        return cursor;
    }

    public boolean checkNameProduct_Product(String product_name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLE_PRODUCT +" where "+ COLUMN_PRODUCT_NAME +"  = ?", new String[] {product_name});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public Cursor ReadData_Product(){
        String query = "Select "+ COLUMN_TYPE_PRODUCT_NAME +","+
                COLUMN_PRODUCT_NAME +","+
                COLUMN_PRODUCT_QUALITY +","+
                COLUMN_PRODUCT_UNIT +"," +
                COLUMN_PRODUCT_PRICE +","+
                COLUMN_PRODUCT_IMAGE +
                " From "+ TABLE_PRODUCT+ ","+ TABLE_TYPE_PRODUCT +
                " Where "+ TABLE_PRODUCT +"."+ F_PRODUCT_COLUMN_ID_TYPE_PRODUCT +"="+ TABLE_TYPE_PRODUCT +"."+ COLUMN_ID_TYPE_PRODUCT +
                " ORDER BY " + COLUMN_ID_PRODUCT + " ASC ";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
           cursor = db.rawQuery(query ,null);
        }

        return cursor;
    }

    public Cursor ReadAllData_Product(Integer product_id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("Select "+ COLUMN_ID_PRODUCT + "," +
                    COLUMN_TYPE_PRODUCT_NAME +","+
                    COLUMN_PRODUCT_NAME +","+
                    COLUMN_PRODUCT_QUALITY +","+
                    COLUMN_PRODUCT_UNIT +"," +
                    COLUMN_PRODUCT_PRICE +","+
                    COLUMN_PRODUCT_IMAGE +
                    " From "+ TABLE_PRODUCT+ ","+ TABLE_TYPE_PRODUCT +
                    " Where "+ TABLE_PRODUCT +"."+ F_PRODUCT_COLUMN_ID_TYPE_PRODUCT +"="+ TABLE_TYPE_PRODUCT +"."+ COLUMN_ID_TYPE_PRODUCT +
                    " AND " + COLUMN_ID_PRODUCT + " = " +product_id,null);
        }

        return cursor;
    }

    public Cursor getIdProduct_Product(String product_name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery("select " + COLUMN_ID_PRODUCT + " from " + TABLE_PRODUCT + " Where " + COLUMN_PRODUCT_NAME + " = ?", new String[] {product_name});

        }
        return cursor;
    }
    //AddProduct

}
