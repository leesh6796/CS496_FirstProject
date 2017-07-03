package com.example.android.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class ImgViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_viewer);

        Intent intent = getIntent();

        ImageView img = (ImageView)findViewById(R.id.targetImg);
        img.setImageResource(intent.getIntExtra("imgNumber", 0));
    }
}
