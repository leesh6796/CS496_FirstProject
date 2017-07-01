package com.example.android.myapplication;

import android.graphics.drawable.Drawable;

public class ListViewItem implements Comparable<ListViewItem> {
    private Drawable iconDrawable ;
    private String nameStr ;
    private String phoneNumberStr ;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setName(String name) {
        nameStr = name ;
    }
    public void setPhoneNumber(String number) {
        phoneNumberStr = number ;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getName() {
        return this.nameStr ;
    }
    public String getPhoneNumber() {
        return this.phoneNumberStr ;
    }

    @Override
    public int compareTo(ListViewItem i) {
        return nameStr.compareTo(i.getName());
    }
}
