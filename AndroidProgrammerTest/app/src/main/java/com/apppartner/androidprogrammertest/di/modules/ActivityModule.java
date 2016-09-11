package com.apppartner.androidprogrammertest.di.modules;

import android.content.Context;

import com.apppartner.androidprogrammertest.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JH on 9/10/16.
 */
@Module
public class ActivityModule {

    private final Context mContext;

    public ActivityModule(Context context){
        mContext = context;
    }

    @Provides
    @ActivityScope
    public Context providesActivityContext(){
        return mContext;
    }
}
