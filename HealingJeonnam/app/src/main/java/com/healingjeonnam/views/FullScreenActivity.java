package com.healingjeonnam.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.healingjeonnam.R;
import com.healingjeonnam.models.Mountain;
import com.nostra13.universalimageloader.core.ImageLoader;

public class FullScreenActivity extends AppCompatActivity {

    ImageView imageView;
    ImageView imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);



        Intent intent = getIntent();
        Mountain mountain = (Mountain) intent.getExtras().getSerializable("selected_image");

        imageView1 = (ImageView) findViewById(R.id.fullimage1);
        imageView = (ImageView) findViewById(R.id.fullimage);
        ImageLoader.getInstance().displayImage(mountain.getImages().get(1), imageView);
        ImageLoader.getInstance().displayImage(mountain.getImages().get(2), imageView1);
    }


}
