package com.example.android.myapplication;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;


public class Tab1Contacts extends Fragment {

    public Tab1Contacts() {
    }
    private ListView mListView;
    private ImageButton sortButton;
    private ContentResolver resolver;
    private static final int REQUEST_READ_CONTACTS = 1;
    private JSONArray contactList;
    private String title="";
    private String link="";
    private boolean sharePage;
    ListViewAdapter adapter;

    public void shareSetting(String t, String l) {
        this.link = l;
        this.title = t;
        this.sharePage = true;
    }
    private void readContacts() {
        resolver = getActivity().getApplicationContext().getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        contactList = new JSONArray();

        while (cursor.moveToNext()) {
            JSONObject personInfo = new JSONObject();
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            try {
                personInfo.put("id", id);
                personInfo.put("name", name);
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            Log.i("name", id + " = "+ name);

            Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[] { id }, null);

            while (phoneCursor.moveToNext()) {
                String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                try {
                    personInfo.put("phoneNumber", phoneNumber);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }
            contactList.put(personInfo);
        }
        cursor.close();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        checkPermission();

        View rootView = inflater.inflate(R.layout.tab1contacts, container, false);
        mListView = (ListView) rootView.findViewById(R.id.contacts);
        adapter = new ListViewAdapter(sharePage, title, link) ;
        mListView.setAdapter(adapter);
        adapter.addItem(contactList, getResources().getDrawable(R.drawable.user));
        sortButton = (ImageButton)rootView.findViewById(R.id.sortButton);
        sortButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.sortList();
                adapter.notifyDataSetChanged();
            }
        });


        EditText editTextFilter = (EditText)rootView.findViewById(R.id.editTextFilter);
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String filterText = s.toString();
                ((ListViewAdapter) mListView.getAdapter()).getFilter().filter(filterText);
            }
        });

        return rootView;
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                // Explain to the user why we need to write the permission.
            }
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS);
        } else {
            readContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "Read Contacts permission granted", Toast.LENGTH_SHORT).show();
                readContacts();
            } else {
                Toast.makeText(getActivity(), "Read Contacts permission denied", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
