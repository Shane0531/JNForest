package com.healingjeonnam.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
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
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.healingjeonnam.R;
import com.healingjeonnam.menu.ImformationActivity;
import com.healingjeonnam.menu.SuggestionActivity;

import java.util.ArrayList;
import java.util.List;

public class MountainSearchActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    static EditText editText;
    ImageButton imageButton;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain_search);

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

        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        editText = (EditText) findViewById(R.id.edittext1);

        editText.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && KeyEvent.ACTION_DOWN == event.getAction()) {
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    name = editText.getText().toString();
                    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager2);
                    if (viewPager != null) {
                        setupViewPager(viewPager);
                    }
                    return true;
                }
                // TODO Auto-generated method stub
                return false;
            }
        });


        imageButton = (ImageButton) findViewById(R.id.imageButton1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                name = editText.getText().toString();
                ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager2);
                if (viewPager != null) {
                    setupViewPager(viewPager);
                }

            }
        });
    }

    public String getData() {
        return name;
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

    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                startActivity(new Intent(MountainSearchActivity.this , MainActivity.class));
                                finish();
                                return true;
                            case R.id.nav_information:
                                startActivity(new Intent(MountainSearchActivity.this, ImformationActivity.class));
                                mDrawerLayout.closeDrawer(navigationView);
                                break;
                            case R.id.nav_call:
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:119"));
                                startActivity(intent);
                                mDrawerLayout.closeDrawer(navigationView);
                                break;
                            case R.id.nav_help:
                                startActivity(new Intent(MountainSearchActivity.this, SuggestionActivity.class));
                                mDrawerLayout.closeDrawer(navigationView);
                                break;
                            case R.id.nav_search1:
                                startActivity(new Intent(MountainSearchActivity.this, SearchActivity.class));
                                finish();
                                break;

                            case R.id.nav_search2:
                                startActivity(new Intent(MountainSearchActivity.this, MountainSearchActivity.class));
                                finish();
                                break;
                        }
                        return false;
                    }
                });
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new SMountainListFragment(),"휴양림");
        viewPager.setAdapter(adapter);
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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MountainSearchActivity.this , MainActivity.class));
        finish();
    }

}
