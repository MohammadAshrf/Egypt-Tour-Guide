package com.example.egypttourguide;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ImageViewerActivity extends AppCompatActivity {
    ImageView ivFullPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_image_viewer);
        ivFullPic = findViewById (R.id.ivFullPic);
        String url = getIntent ().getStringExtra ("url");
        Picasso.get ().load (url).placeholder (R.drawable.placeholder).into (ivFullPic);
    }
}