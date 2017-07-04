package com.example.android.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by q on 2017-06-30.
 */

public class Tab3RSS extends Fragment {

    RssParser rss;
    private ListView titles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab3rss, container, false);
        Log.i("Entry", "진입");

        new Thread() {
            public void run() {
                rss = new RssParser("http://myhome.chosun.com/rss/www_section_rss.xml");

                Bundle bundle = new Bundle();
                Message msg = handler.obtainMessage();
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        }.start();

        return rootView;
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
        }
    };
}
