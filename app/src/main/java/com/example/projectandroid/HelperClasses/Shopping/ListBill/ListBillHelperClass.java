package com.example.projectandroid.HelperClasses.Shopping.ListBill;

import java.io.Serializable;

public class ListBillHelperClass implements Serializable {

    int image;
    String nameBill,createDay;

    public ListBillHelperClass(int image, String nameBill, String createDay) {
        this.image = image;
        this.nameBill = nameBill;
        this.createDay = createDay;
    }

    public int getImage() {
        return image;
    }

    public String getNameBill() {
        return nameBill;
    }

    public String getCreateDay() {
        return createDay;
    }
}
