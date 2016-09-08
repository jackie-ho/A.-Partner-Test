package com.ho.jackie.domain;

import com.ho.jackie.model.LoginInfo;
import com.ho.jackie.model.entities.ChatData;
import com.ho.jackie.model.entities.LoginData;
import com.ho.jackie.model.repository.Repository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by JH on 9/8/16.
 */
public class ChatUseCase extends UseCase<ChatData> {
    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;
    private final Repository mRepository;

    @Inject
    public ChatUseCase(Repository repository,
                       @Named("executor_thread") Scheduler executorThread,
                       @Named("main_thread") Scheduler mainThread) {
        mUiThread = mainThread;
        mExecutorThread = executorThread;
        mRepository = repository;
    }
    @Override
    public Observable<ChatData> buildObservable(LoginInfo loginInfo) {
        return mRepository.getChatData()
                .subscribeOn(mExecutorThread)
                .observeOn(mUiThread);
    }
}
