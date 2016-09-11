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
                        @Named("main_thread") Scheduler mainThread,
                        @Named("executor_thread") Scheduler executorThread) {
        mUiThread = mainThread;
        mExecutorThread = executorThread;
        mRepository = repository;

    }

    public void setLoginInfo(LoginInfo loginInfo){
        if (loginInfo != null) {
            mLogin = loginInfo;
        }
    }

    @Override
    public Observable<LoginData> buildObservable() {
        return mRepository.login(mLogin)
                .subscribeOn(mExecutorThread)
                .observeOn(mUiThread);
    }
}
