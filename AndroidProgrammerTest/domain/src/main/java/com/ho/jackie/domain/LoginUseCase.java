package com.ho.jackie.domain;

import com.ho.jackie.model.LoginInfo;
import com.ho.jackie.model.entities.LoginData;
import com.ho.jackie.model.repository.Repository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;

/**
 * Created by JH on 9/8/16.
 */
public class LoginUseCase extends UseCase<LoginData> {
    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;
    private final Repository mRepository;
    private LoginInfo mLogin;

    @Inject
    public LoginUseCase(Repository repository,
                        @Named("executor_thread") Scheduler executorThread,
                        @Named("main_thread") Scheduler mainThread
                        )
    {
        mUiThread = mainThread;
        mExecutorThread = executorThread;
        mRepository = repository;

    }
    @Override
    public Observable<LoginData> buildObservable(LoginInfo loginInfo) {
        return mRepository.login(mLogin.username, mLogin.password)
                .subscribeOn(mExecutorThread)
                .observeOn(mUiThread);
    }
}
