package com.yamae.yamaeapp;

/**
 * Created by HyunWook Kim on 2014-09-18.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;


public class SplashActivity extends Activity {
    protected final static int SPLASH_TIMEOUT = 3000; // mili-seconds

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Remove title bar
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        useHandler();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Intent startMain=new Intent(this,MainActivity.class);
        //startActivity(startMain);
    }

    protected void dismissSplash() {
        overridePendingTransition(0, android.R.anim.fade_out);
        startActivity(new Intent(SplashActivity.this, MainActivity2.class));
        finish();
    }

    protected void useThread() {
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(SPLASH_TIMEOUT);
                    dismissSplash();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        background.start();
    }

    protected void useHandler() {
        new Handler().postDelayed(new Runnable() {
            // Using handler with postDelayed called runnable run method
            @Override
            public void run() {
                dismissSplash();
            }
        }, SPLASH_TIMEOUT);
    }
}