package com.example.android.myapplication;

import android.graphics.drawable.Drawable;

/**
 * Created by q on 2017-06-30.
 */

public class ListViewImgItem {
    private Drawable img;
    private int id;

    public void setImg(Drawable item) {
        this.img = item;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Drawable getImg() {
        return this.img;
    }
    public int getId() { return this.id; }
}
