package com.example.android.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by q on 2017-07-01.
 */

public class GalleryListView extends Fragment {
    private ImgListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.gallery_listview, container, false);

        // ListView에 Image를 추가하기 위한 Adapter를 만든다.
        ImgListAdapter adapter = new ImgListAdapter();

        ListView lv = (ListView)rootView.findViewById(R.id.lv);
        lv.setAdapter(adapter);

        // ListView Item Click EventListener
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                Log.i("Resource Id", String.valueOf(id));
                int size = 1;
                BitmapFactory.Options opt = new BitmapFactory.Options();
                opt.inSampleSize = 4;
                Bitmap bitmapOriginal = BitmapFactory.decodeResource(ct.getResources(), id, opt);
                Bitmap bitmapSimpleSize = Bitmap.createScaledBitmap(bitmapOriginal, bitmapOriginal.getWidth() / size, bitmapOriginal.getHeight() / size, true);
                adapter.addItem(bitmapSimpleSize, id);
            } catch (Exception ex) {
                //ex.printStackTrace();
            }
        }


        return rootView;
    }

    /*@Override
    public void onDetach() {
        super.onDetach();
        int i;
        for(i=0; i<adapter.getCount(); i++) {
            ((ListViewImgItem)adapter.getItem(i)).getSrc().recycle();
        }
    }*/
}
