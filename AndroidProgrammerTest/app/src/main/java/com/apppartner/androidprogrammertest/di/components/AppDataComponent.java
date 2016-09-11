package com.apppartner.androidprogrammertest.di.components;

import com.apppartner.androidprogrammertest.di.ActivityScope;
import com.apppartner.androidprogrammertest.di.modules.AppDataModule;
import com.apppartner.androidprogrammertest.views.activities.LoginActivity;

import dagger.Component;

/**
 * Created by JH on 9/8/16.
 */
@ActivityScope
@Component (dependencies = AppComponent.class, modules = AppDataModule.class)
public interface AppDataComponent {

    void inject(LoginActivity loginActivity);

}
