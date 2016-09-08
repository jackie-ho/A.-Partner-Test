package com.apppartner.androidprogrammertest.di.modules;

import com.apppartner.androidprogrammertest.AppPartnerTestApp;
import com.ho.jackie.model.SourceData;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JH on 9/8/16.
 */
@Module
public class AppModule {

    private final AppPartnerTestApp app;

    public AppModule(AppPartnerTestApp app){
        this.app = app;
    }

    @Provides
    @Singleton
    public AppPartnerTestApp providesAppContext(){
        return app;
    }

    @Provides
    @Singleton
    public SourceData providesDataSource(SourceData sourceData){
        return sourceData;
    }

    @Provides
    @Named("executor_thread")
    Scheduler providesExecutorThread(){
        return Schedulers.newThread();
    }

    @Provides
    @Named("main_thread")
    Scheduler providesMainThread(){
        return AndroidSchedulers.mainThread();
    }
}
