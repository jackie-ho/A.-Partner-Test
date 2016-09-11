package com.apppartner.androidprogrammertest.mvp.views;

import com.ho.jackie.model.LoginInfo;
import com.ho.jackie.model.entities.LoginData;

/**
 * Created by JH on 9/7/16.
 */
public interface LoginView extends Views {

    void login();
    void displayLoginResult(LoginData loginData);
}
