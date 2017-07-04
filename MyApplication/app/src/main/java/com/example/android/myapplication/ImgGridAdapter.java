package com.example.android.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by q on 2017-07-01.
 */

public class ImgGridAdapter extends BaseAdapter {

    private ArrayList<ListViewImgItem> gridViewItemList = new ArrayList<ListViewImgItem>();

    public ImgGridAdapter() {

    }

    @Override
    public int getCount() {
        return gridViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_grid, parent, false);
        }

        ImageView img = (ImageView)convertView.findViewById(R.id.gridImg);
        ListViewImgItem item = gridViewItemList.get(pos);

        Glide.with(context).load(item.getId()).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(img);

        //img.setImageBitmap(item.getSrc());

        return convertView;
    }

    @Override
    public long getItemId(int pos) { return pos; }

    @Override
    public Object getItem(int pos) {
        return gridViewItemList.get(pos);
    }

    public void addItem(Drawable img, int id) {
        ListViewImgItem item = new ListViewImgItem();
        item.setImg(img);
        item.setId(id);

        gridViewItemList.add(item);
    }

    public void addItem(Bitmap img, int id) {
        ListViewImgItem item = new ListViewImgItem();
        item.setSrc(img);
        item.setId(id);

        gridViewItemList.add(item);
    }

    public void addItem(int id) {
        ListViewImgItem item = new ListViewImgItem();
        item.setId(id);

        gridViewItemList.add(item);
    }
}
