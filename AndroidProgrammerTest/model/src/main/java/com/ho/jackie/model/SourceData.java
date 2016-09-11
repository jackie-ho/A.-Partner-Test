package com.ho.jackie.model;

import com.ho.jackie.model.entities.ChatData;
import com.ho.jackie.model.entities.LoginData;
import com.ho.jackie.model.repository.Repository;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Created by JH on 9/8/16.
 */
public class SourceData implements Repository {

    private final AppPartnerApi mAppPartnerApi;
    public static final String BASE_URL = "http://dev.apppartner.com/";
    private int responseTime;

    @Inject
    public SourceData() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpBuilder.addNetworkInterceptor(loggingInterceptor);
        okHttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Response response = chain.proceed(original);
                responseTime = (int)(response.receivedResponseAtMillis() -
                        response.sentRequestAtMillis());
                return response;
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpBuilder.build())
                .build();

        mAppPartnerApi = retrofit.create(AppPartnerApi.class);
    }

    @Override
    public Observable<LoginData> login(final LoginInfo loginInfo) {
        return mAppPartnerApi.login(loginInfo.username, loginInfo.password)
                .flatMap(new Func1<LoginData, Observable<LoginData>>() {
                    @Override
                    public Observable<LoginData> call(LoginData loginData) {
                        loginData.setLengthOfApiCall(responseTime);
                        return Observable.just(loginData);
                    }
                });
    }

    @Override
    public Observable<List<ChatData>> getChatData(String chatData) {
        return null;
    }
}
