package com.example.projectandroid.HelperClasses.Shopping.ListPromotion;

import java.io.Serializable;

public class ListPromotionHelperClass implements Serializable {

    int image;
    String present,startDay,endDay;

    public ListPromotionHelperClass(int image, String present, String startDay, String endDay) {
        this.image = image;
        this.present = present;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public int getImage() {
        return image;
    }

    public String getPresent() {
        return present;
    }

    public String getStartDay() {
        return startDay;
    }

    public String getEndDay() {
        return endDay;
    }
}
