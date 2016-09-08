package com.ho.jackie.model;

import com.ho.jackie.model.entities.ChatData;
import com.ho.jackie.model.entities.LoginData;
import com.ho.jackie.model.repository.Repository;

import java.io.InputStream;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by JH on 9/8/16.
 */
public class SourceData implements Repository {

    private final AppPartnerApi mAppPartnerApi;

    @Inject
    public SourceData(){
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppPartnerApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpBuilder.build())
                .build();

        mAppPartnerApi = retrofit.create(AppPartnerApi.class);
    }

    @Override
    public Observable<LoginData> login(String username, String password) {
         return mAppPartnerApi.login(username, password)
                .flatMap(new Func1<LoginData, Observable<LoginData>>() {
                    @Override
                    public Observable<LoginData> call(LoginData loginData) {
                        return Observable.just(loginData);
                    }
                });

    }

    @Override
    public Observable<ChatData> getChatData() {
        return null;
    }
}
