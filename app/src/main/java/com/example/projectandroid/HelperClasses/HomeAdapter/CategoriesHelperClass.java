package com.example.projectandroid.HelperClasses.HomeAdapter;

public class CategoriesHelperClass {
    int image, background;
    String title;

    public CategoriesHelperClass(int image, String title, int background) {
        this.image = image;
        this.title = title;
        this.background = background;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public int getBackgournd() {
        return background;
    }

}
