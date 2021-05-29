package com.example.lookmenu;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private int iconDrawable ;
    private String titleStr ;
    private String priceStr ;
    private String descStr ;

    public void setImage(int icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setPrice(String price) {
        priceStr = price ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }

    public int getImage() {
        return this.iconDrawable;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public String getPrice() {
        return this.priceStr ;
    }
}
