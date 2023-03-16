package com.example.collegeapp.ui.notice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.collegeapp.R;
import com.github.chrisbanes.photoview.PhotoView;

public class Notice_Full_Image_Activity extends AppCompatActivity {
  PhotoView photo_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_full_image);


        String image = getIntent().getStringExtra("image");
        photo_view=findViewById(R.id.photo_view);
        Glide.with(this).load(image).into(photo_view);

    }
}