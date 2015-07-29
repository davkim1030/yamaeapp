package com.yamae.yamaeapp;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.parse.Parse;
import com.parse.ParseACL;
import io.fabric.sdk.android.Fabric;

/**
 * Created by HyunWook Kim on 2014-11-02.
 */
public class YaMaeApp extends Application {
    private static final String APPLICATION_ID="ipzVuiKWPL38Zqfbvnr2150SdtHNkQu0uZKfjToQ";
    private static final String CLIENT_KEY="nbvzhWRtlSnZyHGzsxFNrLdW7Aeo54RUCzxVfyc5";

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

        ParseACL defaultACL=new ParseACL();
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL,true);
    }
}
