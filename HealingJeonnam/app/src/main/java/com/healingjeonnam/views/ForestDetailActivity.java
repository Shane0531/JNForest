package com.healingjeonnam.views;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.healingjeonnam.R;
import com.healingjeonnam.adapter.ImageAdapter;
import com.healingjeonnam.models.Forest;

import me.relex.circleindicator.CircleIndicator;

public class ForestDetailActivity extends AppCompatActivity {

    TextView addrview;
    TextView infoview;
    TextView pageview;
    TextView phoneview;
    FloatingActionButton homepagebutton;
    static Forest forest;
    NestedScrollView nestedScrollView;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forest_detail);

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedscrollview);

        Intent intent = getIntent();
        forest = (Forest) intent.getExtras().getSerializable("forest");

        ViewPager mapviewpager = (ViewPager) findViewById(R.id.mapviewpager);
        if (mapviewpager != null) {
            setupViewPager(mapviewpager);
        }
        mapviewpager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForestDetailActivity.this, MapFragment.class));
            }
        });
        infoview = (TextView) findViewById(R.id.infotextview);
        addrview = (TextView) findViewById(R.id.addrtext);
        phoneview = (TextView) findViewById(R.id.phonetext);
        pageview = (TextView) findViewById(R.id.homepagetext);
        homepagebutton = (FloatingActionButton) findViewById(R.id.homepagebutton);
        homepagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(forest.getPage()));
                    startActivity(myIntent);
                } catch (Exception e) {
                    Toast.makeText(ForestDetailActivity.this, "이 곳은 홈페이지가 없습니다.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
        infoview.setText(forest.getIntroduce());
        addrview.setText("주 소 : " + forest.getAddr());
        phoneview.setText("전화번호 : " + forest.getTelephone());
        if(forest.getPage() == null)
            pageview.setText("홈페이지 : 이 곳은 홈페이지가 없습니다.");
        else
            pageview.setText("홈페이지 : " + forest.getPage());

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.back);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ImageAdapter adapter = new ImageAdapter(this, forest);
        viewPager.setAdapter(adapter);
        CircleIndicator customIndicator = (CircleIndicator) findViewById(R.id.circleindicator);
        customIndicator.setViewPager(viewPager);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(forest.getName());
        collapsingToolbar.setCollapsedTitleTextColor(Color.BLACK);


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new MapFragment());
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

    public Forest getData() {
        return forest;
    }
}
