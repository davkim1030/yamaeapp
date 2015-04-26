package com.yamae.yamaeapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

/**
 * Created by HyunWook Kim on 2014-11-02.
 */
public class YaMaeApp extends Application {
    private static final String APPLICATION_ID="ipzVuiKWPL38Zqfbvnr2150SdtHNkQu0uZKfjToQ";
    private static final String CLIENT_KEY="nbvzhWRtlSnZyHGzsxFNrLdW7Aeo54RUCzxVfyc5";

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

        ParseACL defaultACL=new ParseACL();
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL,true);
    }
}
