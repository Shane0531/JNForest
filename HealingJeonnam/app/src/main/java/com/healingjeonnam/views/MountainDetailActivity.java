package com.healingjeonnam.views;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.healingjeonnam.R;
import com.healingjeonnam.models.Mountain;
import com.nostra13.universalimageloader.core.ImageLoader;




public class MountainDetailActivity extends AppCompatActivity {

    TextView addrview;
    TextView infoview;
    TextView meterview;
    static Mountain mountain;
    NestedScrollView nestedScrollView;
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain_detail);
        
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedscrollview1);

        Intent intent = getIntent();
        mountain = (Mountain) intent.getExtras().getSerializable("forest");




        infoview = (TextView) findViewById(R.id.infotextview1);
        addrview = (TextView) findViewById(R.id.addrtext);
        meterview = (TextView) findViewById(R.id.metertext);
        imageView1 = (ImageView) findViewById(R.id.imageview1);
        imageView2 = (ImageView) findViewById(R.id.imageview2);
        imageView = (ImageView) findViewById(R.id.mountainimage);
        ImageLoader.getInstance().displayImage(mountain.getImages().get(0), imageView);
        ImageLoader.getInstance().displayImage(mountain.getImages().get(1), imageView1);
        ImageLoader.getInstance().displayImage(mountain.getImages().get(2), imageView2);

        imageView1.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Intent intent = new Intent(MountainDetailActivity.this, FullScreenActivity.class);
                                              intent.putExtra("selected_image", mountain);
                                              startActivity(intent);
                                          }
                                      }
        );
        imageView2.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Intent intent = new Intent(MountainDetailActivity.this, FullScreenActivity.class);
                                              intent.putExtra("selected_image",mountain);
                                              startActivity(intent);
                                          }
                                      }
        );


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.back);


        infoview.setText(mountain.getIntroduce());
        addrview.setText("주 소 : " + mountain.getAddr());
        meterview.setText("해발고도 : "+mountain.getHeight() + "미터");

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar1);
        collapsingToolbar.setTitle(mountain.getName());
        collapsingToolbar.setCollapsedTitleTextColor(Color.BLACK);


        ViewPager mapviewpager = (ViewPager) findViewById(R.id.mapviewpager1);
        if (mapviewpager != null) {
            setupViewPager(mapviewpager);
        }

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new MountainmapFragment());
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private Fragment mFragments = new Fragment();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment) {
            mFragments = fragment;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments;
        }

        @Override
        public int getCount() {
            return 1;
        }

    }

    public Mountain getData() {
        return mountain;
    }
}
