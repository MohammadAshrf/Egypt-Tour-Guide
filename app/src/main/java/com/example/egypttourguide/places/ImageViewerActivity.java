package com.example.egypttourguide.places;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.egypttourguide.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class ImageViewerActivity extends AppCompatActivity {
    PhotoView ivFullPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_image_viewer);
        ivFullPic = findViewById (R.id.ivFullPic);
        String url = getIntent ().getStringExtra ("url");
        Picasso.get ().load (url).placeholder (R.drawable.placeholder).into (ivFullPic);
    }
}