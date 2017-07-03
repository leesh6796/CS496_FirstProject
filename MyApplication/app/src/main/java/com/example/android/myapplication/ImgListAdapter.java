package com.example.android.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by q on 2017-06-30.
 */

public class ImgListAdapter extends BaseAdapter {
    private ArrayList<ListViewImgItem> listViewItemList = new ArrayList<ListViewImgItem>();

    public ImgListAdapter() {

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_img, parent, false);
        }

        ImageView img = (ImageView)convertView.findViewById(R.id.imgitem);

        ListViewImgItem item = listViewItemList.get(pos);
        if(item.getImg() != null) {
            int size = 1;
            Bitmap bitmapOriginal = BitmapFactory.decodeResource(context.getResources(), item.getId());
            Bitmap bitmapSimpleSize = Bitmap.createScaledBitmap(bitmapOriginal, bitmapOriginal.getWidth() / size, bitmapOriginal.getHeight() / size, true);
            if(!bitmapSimpleSize.isRecycled())
                bitmapSimpleSize.recycle();
            img.setImageBitmap(bitmapSimpleSize);
        }

        //img.setImageResource(item.getId());
        //img.setImageDrawable(item.getImg());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int pos) {
        return listViewItemList.get(pos);
    }

    /*public void addItem(Drawable img, int id) {
        ListViewImgItem item = new ListViewImgItem();
        item.setImg(img);
        item.setId(id);

        listViewItemList.add(item);
    }*/

    public void addItem(Drawable img, int id) {
        ListViewImgItem item = new ListViewImgItem();
        item.setImg(img);
        item.setId(id);

        listViewItemList.add(item);
    }
}