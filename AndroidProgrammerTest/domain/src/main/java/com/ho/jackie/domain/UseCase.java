package com.ho.jackie.domain;

import com.ho.jackie.model.LoginInfo;

import rx.Observable;

public abstract class UseCase<T> {
    public abstract Observable<T> buildObservable(LoginInfo loginInfo);

    public Observable<T> execute(LoginInfo loginInfo) {
        return buildObservable(loginInfo);
    }

}
