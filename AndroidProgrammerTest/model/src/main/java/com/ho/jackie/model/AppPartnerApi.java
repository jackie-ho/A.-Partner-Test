package com.ho.jackie.model;

import com.ho.jackie.model.entities.LoginData;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by JH on 9/8/16.
 */
public interface AppPartnerApi {

    @FormUrlEncoded
    @POST("AppPartnerProgrammerTest/scripts/login.php/")
    Observable<LoginData> login(@Field("username") String username, @Field("password")String password);
}
