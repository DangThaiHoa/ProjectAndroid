package com.example.projectandroid;

import android.app.Application;

public class UserSetting  extends Application {
    public static final String PREFERENCES = "preferences";

    public static final String Custom_Theme = "customtheme";
    public static final String Light_Mode = "lightmode";
    public static final String Dark_Mode = "darkmode";

    private String customTheme;

    public String getCustomTheme() {
        return customTheme;
    }

    public void setCustomTheme(String customTheme) {
        this.customTheme = customTheme;
    }
}
