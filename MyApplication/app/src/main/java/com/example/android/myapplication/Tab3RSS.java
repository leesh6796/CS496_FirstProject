package com.example.android.myapplication;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by q on 2017-06-30.
 */

public class Tab3RSS extends Fragment {

    RssParser rss;
    private ListView newsList;
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3rss, container, false);
        newsList = (ListView)rootView.findViewById(R.id.newsList);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        newsList.setAdapter(adapter);

        // ListView Item Click EventListener
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int pos, long id) {
                //int id = R.drawable.class.get

                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("title", rss.getNewsTitle(pos));
                intent.putExtra("link", rss.getNewsURL(pos));
                startActivity(intent);
            }
        });

        new AsyncTask<Void, Void, Void>() {
            @Override
            public Void doInBackground(final Void... params) {
                rss = new RssParser("http://myhome.chosun.com/rss/www_section_rss.xml");
                return null;
            }

            @Override
            protected void onPostExecute( final Void result ) {
                // continue what you are doing...
                int i;
                for(i=0; i<rss.getNewsCount(); i++) {
                    adapter.add(rss.getNewsTitle(i));
                }
            }
        }.execute();

        return rootView;
    }
}
