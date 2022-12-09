package com.example.projectandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {

    private static  String TAG = SessionManager.class.getSimpleName();
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    String idLogin;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "AndroidCheckLogin";
    private static final String KEY_IS_LOGGED_IN = "IsLoggedIn";

    public SessionManager (Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin (boolean isLoggedIn){
        editor.putBoolean(KEY_IS_LOGGED_IN,isLoggedIn);
        editor.putString("saveID",idLogin);
        editor.commit();
        Log.d(TAG,"User login session modified");
    }

    public String setID (){
        String ID = pref.getString("saveID","");
        return ID;
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGED_IN,false);
    }

    public String getId(String myID){
        return idLogin = myID;
    }
}
