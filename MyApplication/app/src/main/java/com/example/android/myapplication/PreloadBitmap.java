package com.example.android.myapplication;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by q on 2017-07-03.
 */

public class PreloadBitmap {

    private static ArrayList<Bitmap> bitmaps = new ArrayList<>();

    public static void addBitmap(Bitmap src) {
        bitmaps.add(src);
    }

    public static Bitmap getBitmap(int pos) {
        return bitmaps.get(pos);
    }

    public static int getSize() {
        return bitmaps.size();
    }
}
