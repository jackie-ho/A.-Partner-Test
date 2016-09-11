package com.ho.jackie.model.repository;

import com.ho.jackie.model.LoginInfo;
import com.ho.jackie.model.entities.ChatData;
import com.ho.jackie.model.entities.LoginData;

import java.util.List;

import rx.Observable;

/**
 * Created by JH on 9/7/16.
 */
public interface Repository {

    Observable<LoginData> login(LoginInfo loginInfo);

    Observable<List<ChatData>> getChatData(String datas);
}
