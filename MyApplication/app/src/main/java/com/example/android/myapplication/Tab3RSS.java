package com.example.android.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

        rss = new RssParser("http://myhome.chosun.com/rss/www_section_rss.xml");
        AsyncTask task = new AsyncTask<Object, Void, Void>() {
            @Override
            public Void doInBackground(Object... params) {
                rss.parse();
                return null;
            }

            @Override
            public void onPostExecute(Void result) {
                int i;
                ArrayList<String> items = (ArrayList<String>)rss.getItemTitles();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);

                titles = (ListView)rootView.findViewById(R.id.newsList);
                titles.setAdapter(adapter);

                for(i=0; i<items.size(); i++) {
                    //plainText += items.get(i) + "\n";
                    adapter.add(items.get(i));
                }
            }
        };
        task.execute();

        return rootView;
    }
}
