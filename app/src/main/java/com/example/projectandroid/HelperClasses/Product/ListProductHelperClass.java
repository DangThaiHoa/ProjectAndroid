package com.example.projectandroid.HelperClasses.Product;

import java.io.Serializable;

public class ListProductHelperClass implements Serializable {

    int image;
    String name,qualityItem;

    public ListProductHelperClass(int image, String name, String qualityItem) {
        this.image = image;
        this.name = name;
        this.qualityItem = qualityItem;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getQualityItem() {
        return qualityItem;
    }
}
