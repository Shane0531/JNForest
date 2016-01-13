package com.healingjeonnam.views;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.healingjeonnam.R;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Handler hd = new Handler();

        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(LoadingActivity.this,
                        MainActivity.class);

                //SplashScreen.this.startActivity(mainIntent);
                startActivity(mainIntent);
                     /* Finish splash activity so user cant go back to it. */
                LoadingActivity.this.finish();

                     /* Apply our splash exit (fade out) and main
                        entry (fade in) animation transitions. */
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
        }, 3000);

    }
}
