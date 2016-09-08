package com.apppartner.androidprogrammertest.di.components;

import com.apppartner.androidprogrammertest.AppPartnerTestApp;
import com.apppartner.androidprogrammertest.di.modules.AppModule;
import com.ho.jackie.model.repository.Repository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;

/**
 * Created by JH on 9/8/16.
 */
@Singleton
@Component (modules = AppModule.class)
public interface AppComponent {

    AppPartnerTestApp app();
    Repository repository();

    @Named("main_thread")
    Scheduler uiThread();
    @Named("executor_thread")
    Scheduler executorThread();
}
