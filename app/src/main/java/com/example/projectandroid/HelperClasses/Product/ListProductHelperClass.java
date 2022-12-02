package com.example.projectandroid.HelperClasses.Product;

import java.io.Serializable;

public class ListProductHelperClass implements Serializable {

    private String name;
    private String quality;
    private byte[] image;

    public ListProductHelperClass(String name, String quality, byte[] image) {
        this.image = image;
        this.name = name;
        this.quality = quality;
    }


    public String getName() {
        return name;
    }

    public String getQuality() {
        return quality;
    }

    public byte[] getImage() {
        return image;
    }
}
