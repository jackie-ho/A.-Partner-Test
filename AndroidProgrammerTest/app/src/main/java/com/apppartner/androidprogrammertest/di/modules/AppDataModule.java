package com.apppartner.androidprogrammertest.di.modules;

import android.content.Context;

import com.apppartner.androidprogrammertest.di.ActivityScope;
import com.ho.jackie.domain.ChatUseCase;
import com.ho.jackie.domain.LoginUseCase;
import com.ho.jackie.model.repository.Repository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by JH on 9/8/16.
 */
@Module (includes = ActivityModule.class)
public class AppDataModule {

    @Provides
    @ActivityScope
    LoginUseCase providesLoginUseCase(Repository repository,
    @Named("main_thread") Scheduler mainThread,
    @Named("executor_thread") Scheduler workerThread) {
        return new LoginUseCase(repository, mainThread, workerThread);
    }

    @Provides
    @ActivityScope
    ChatUseCase providesChatUseCase(Repository repository,
                                    @Named("main_thread") Scheduler mainThread,
                                    @Named("executor_thread") Scheduler workerThread) {
        return new ChatUseCase(repository, mainThread, workerThread);
    }

}
