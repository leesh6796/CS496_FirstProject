package com.example.android.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

/**
 * Created by q on 2017-07-01.
 */

public class GalleryGridView extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.gallery_gridview, container, false);

        // ListView에 Image를 추가하기 위한 Adapter를 만든다.
        ImgGridAdapter adapter = new ImgGridAdapter();

        GridView gv = (GridView)rootView.findViewById(R.id.gv);
        gv.setAdapter(adapter);

        // ListView Item Click EventListener
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int pos, long id) {
                ListViewImgItem item = (ListViewImgItem)parent.getItemAtPosition(pos);

                //int id = R.drawable.class.get

                Intent intent = new Intent(getActivity(), ImgViewerActivity.class);
                intent.putExtra("imgNumber", item.getId());
                startActivity(intent);
            }
        });

        // array.xml을 만들고 그 안에 integer-array를 정의한다. obtainTypedArray로 불러온다.
        //TypedArray imgs = getResources().obtainTypedArray(R.array.imglist);

        int i;
        Context ct = getContext();

        Resources res = getResources();
        int numGirls = res.getInteger(R.integer.numGirls);


        for(i=1; i<=numGirls; i++) {
            try {
                String imgName = "i" + String.valueOf(i);
                int id = R.drawable.class.getField(imgName).getInt(null);
                if(i == 12) break;
                adapter.addItem(ContextCompat.getDrawable(ct, id), id);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return rootView;
    }
}
