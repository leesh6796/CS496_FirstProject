package com.example.android.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by q on 2017-06-30.
 */

public class Tab2Gallery extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2gallery, container, false);

        ImgListAdapter adapter = new ImgListAdapter();
        ListView lv = (ListView)rootView.findViewById(R.id.lv);
        lv.setAdapter(adapter);

        Context ct = getContext();
        adapter.addItem(ContextCompat.getDrawable(ct, R.drawable.i2));
        adapter.addItem(ContextCompat.getDrawable(ct, R.drawable.i3));
        adapter.addItem(ContextCompat.getDrawable(ct, R.drawable.i4));

        return rootView;
    }
}
