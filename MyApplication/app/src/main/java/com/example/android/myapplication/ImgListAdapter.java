package com.example.android.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
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

        img.setImageDrawable(item.getImg());

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

    public void addItem(Drawable img) {
        ListViewImgItem item = new ListViewImgItem();
        item.setImg(img);

        listViewItemList.add(item);
    }
}
