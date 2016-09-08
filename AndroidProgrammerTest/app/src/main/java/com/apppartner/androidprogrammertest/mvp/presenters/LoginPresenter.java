package com.apppartner.androidprogrammertest.mvp.presenters;

import com.apppartner.androidprogrammertest.mvp.views.LoginView;
import com.apppartner.androidprogrammertest.mvp.views.Views;
import com.ho.jackie.domain.LoginUseCase;

import javax.inject.Inject;

/**
 * Created by JH on 9/7/16.
 */
public class LoginPresenter implements Presenter {

    private LoginView mView;

    @Inject
    public LoginPresenter(LoginUseCase loginUseCase){

    }

    @Override
    public void bindView(Views view) {
        mView = (LoginView)view;
    }
}
