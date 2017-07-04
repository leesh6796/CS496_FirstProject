package com.example.android.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImgViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_viewer);

        Intent intent = getIntent();
        int id = intent.getIntExtra("imgNumber", 0);

        ImageView img = (ImageView)findViewById(R.id.targetImg);
        Glide.with(this).load(id).centerCrop().into(img);
        //img.setImageBitmap(PreloadBitmap.getBitmap(intent.getIntExtra("imgNumber", 0)));
    }
}
