package com.apppartner.androidprogrammertest.mvp.presenters;

import com.apppartner.androidprogrammertest.mvp.views.LoginView;
import com.apppartner.androidprogrammertest.mvp.views.Views;
import com.ho.jackie.domain.LoginUseCase;
import com.ho.jackie.model.AppPartnerApi;
import com.ho.jackie.model.LoginInfo;
import com.ho.jackie.model.entities.LoginData;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Created by JH on 9/7/16.
 */
public class LoginPresenter implements Presenter {

    private LoginView mView;
    private LoginUseCase mLoginUseCase;
    private LoginData savedLoginData;

    @Inject
    public LoginPresenter(LoginUseCase loginUseCase){
        mLoginUseCase = loginUseCase;
    }

    @Override
    public void bindView(Views view) {
        mView = (LoginView)view;
    }

    public void login(LoginInfo loginInfo){
        mLoginUseCase.setLoginInfo(loginInfo);
        mLoginUseCase.execute()
                .subscribe(new Action1<LoginData>() {
                    @Override
                    public void call(LoginData loginData) {
                        onLoginReceived(loginData);
                    }
                });
    }
    @Override
    public void unBindView() {
        mView = null;
    }

    private void onLoginReceived(LoginData loginData){
        if (mView != null) {
            if (loginData.code.equals("Success")){
                mView.displayLoginSuccess(loginData);
            } else {
                mView.displayLoginFailure(loginData);
            }
        }
    }

}
