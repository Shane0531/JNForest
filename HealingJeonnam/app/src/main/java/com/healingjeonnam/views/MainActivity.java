package com.healingjeonnam.views;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.healingjeonnam.R;
import com.healingjeonnam.menu.ImformationActivity;
import com.healingjeonnam.menu.SuggestionActivity;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private TabLayout tabLayout;
    private FloatingActionMenu fab;
    private final long	FINSH_INTERVAL_TIME    = 2000;
    private long		backPressedTime        = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }




        fab = (FloatingActionMenu) findViewById(R.id.fab);
        fab1 =(FloatingActionButton) findViewById(R.id.fab1);
        fab2 =(FloatingActionButton) findViewById(R.id.fab2);
        fab1.setLabelText("휴양림");
        fab2.setLabelText("산길");
        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);
        fab.setClosedOnTouchOutside(true);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    mDrawerLayout.openDrawer(GravityCompat.START);
                    return true;
            }
            return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ForestListFragment(), "휴양림");
        adapter.addFragment(new MountainListFragment(), "산길");
        viewPager.setAdapter(adapter);
    }

    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch(menuItem.getItemId()){
                            case R.id.nav_home:
                                mDrawerLayout.closeDrawer(navigationView);
                                return true;
                            case R.id.nav_information:
                                startActivity(new Intent(MainActivity.this, ImformationActivity.class));
                                mDrawerLayout.closeDrawer(navigationView);
                                break;
                            case R.id.nav_call:
                                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:119"));
                                startActivity(intent);
                                mDrawerLayout.closeDrawer(navigationView);
                                break;
                            case R.id.nav_help:
                                startActivity(new Intent(MainActivity.this, SuggestionActivity.class));
                                mDrawerLayout.closeDrawer(navigationView);
                                break;
                            case R.id.nav_search1:
                                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                                mDrawerLayout.closeDrawer(navigationView);
                                finish();
                                break;
                            case R.id.nav_search2:
                                startActivity(new Intent(MainActivity.this, MountainSearchActivity.class));
                                mDrawerLayout.closeDrawer(navigationView);
                                finish();
                                break;
                        }
                        return false;
                    }
                });
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab1:
                    startActivity(new Intent(MainActivity.this,MapActivity.class));
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                    break;
                case R.id.fab2:
                    startActivity(new Intent(MainActivity.this,MountainMapActivity.class));
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                    break;

            }
        }
    };
    @Override
         public void onBackPressed() {
                long tempTime        = System.currentTimeMillis();
                long intervalTime    = tempTime - backPressedTime;

                if ( 0 <= intervalTime && FINSH_INTERVAL_TIME >= intervalTime ) {
                        super.onBackPressed();
                    }
                else {
                       backPressedTime = tempTime;
                        Toast.makeText(getApplicationContext(), "뒤로버튼을한번더누르시면종료됩니다.", Toast.LENGTH_SHORT).show();
                    }
            }

}