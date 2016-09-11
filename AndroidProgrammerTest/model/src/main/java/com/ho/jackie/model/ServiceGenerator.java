package com.ho.jackie.model;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JH on 9/11/16.
 */
public class ServiceGenerator {

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
            .baseUrl(AppPartnerApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

//    public static <S> S createService(Class<S> serviceClass, String username, String password) {
//        if (username != null && password != null) {
//            String credentials = username + ":" + password;
//        }
//    }
}
