package com.example.android.myapplication;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by q on 2017-07-03.
 */

public class PreloadBitmap {

    private static ArrayList<Bitmap> bitmaps = new ArrayList<>();
    private static ArrayList<Integer> ids = new ArrayList<>();
    private static boolean loaded = false;

    public static void addBitmap(Bitmap src) {
        loaded = true;
        bitmaps.add(src);
    }

    public static void addBitmap(int id) {
        loaded = true;
        ids.add(id);
    }

    public static boolean isInitialized() {
        return loaded;
    }

    public static Bitmap getBitmap(int pos) {
        return bitmaps.get(pos);
    }
    public static int getId(int pos) {
        return ids.get(pos);
    }

    public static int getSize() {
        return bitmaps.size();
    }
}
