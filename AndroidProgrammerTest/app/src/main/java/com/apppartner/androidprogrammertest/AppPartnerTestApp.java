package com.apppartner.androidprogrammertest;

import android.app.Application;

import com.apppartner.androidprogrammertest.di.components.AppComponent;
import com.apppartner.androidprogrammertest.di.components.DaggerAppComponent;
import com.apppartner.androidprogrammertest.di.modules.AppModule;

/**
 * Created by JH on 9/7/16.
 */
public class AppPartnerTestApp extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
