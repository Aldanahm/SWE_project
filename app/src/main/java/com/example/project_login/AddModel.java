package com.example.project_login;

import android.content.Intent;
import android.graphics.Bitmap;

public class AddModel {
    private static int id;
    //private static Bitmap image ;
    private static String Title ;
    private static String desc ;
    private static int price ;
    private static String size ;

    public AddModel( int id , String title, String desc, int price, String size) {
        //this.image = image ;
        this.id = id ;
        Title = title;
        this.desc = desc ;
        this.price = price;
        this.size = size;
    }

    /*public static Bitmap getImage() {
        return image;
    }

    public static void setImage(Bitmap image) {
        AddModel.image = image;
    }*/

    public static int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public static String getDesc() {
        return desc;
    }

    public static void setDesc(String desc) {
        AddModel.desc = desc;
    }

    public static int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ID='" + id + '\'' +
                "Title='" + Title + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", size='" + size + '\'' ;
    }

}
