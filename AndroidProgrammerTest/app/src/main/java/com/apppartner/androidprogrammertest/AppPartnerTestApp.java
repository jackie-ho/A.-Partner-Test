package com.apppartner.androidprogrammertest;

import android.app.Application;

import com.apppartner.androidprogrammertest.di.components.AppComponent;

/**
 * Created by JH on 9/7/16.
 */
public class AppPartnerTestApp extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
