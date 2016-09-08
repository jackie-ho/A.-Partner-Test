package com.ho.jackie.model;

import com.ho.jackie.model.entities.LoginData;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by JH on 9/8/16.
 */
public interface AppPartnerApi {

    public static final String BASE_URL = "http://dev.apppartner.com/AppPartnerProgrammerTest/scripts/login.php";

    @POST()
    Observable<LoginData> login(String username, String password);
}
