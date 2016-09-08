package com.apppartner.androidprogrammertest.di.components;

import com.apppartner.androidprogrammertest.di.ActivityScope;

import dagger.Component;

/**
 * Created by JH on 9/8/16.
 */
@ActivityScope
@Component (dependencies = AppComponent.class)
public class AppDataComponent {

}
