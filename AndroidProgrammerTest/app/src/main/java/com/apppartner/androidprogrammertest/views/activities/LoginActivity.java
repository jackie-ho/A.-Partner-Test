package com.apppartner.androidprogrammertest.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.apppartner.androidprogrammertest.AppPartnerTestApp;
import com.apppartner.androidprogrammertest.R;
import com.apppartner.androidprogrammertest.di.components.DaggerAppDataComponent;
import com.apppartner.androidprogrammertest.mvp.presenters.LoginPresenter;
import com.apppartner.androidprogrammertest.mvp.views.LoginView;
import com.ho.jackie.model.LoginInfo;
import com.ho.jackie.model.entities.LoginData;
import com.jakewharton.rxbinding.widget.RxTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func2;

public class LoginActivity extends ActionBarActivity implements LoginView
{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.username_edit_text)
    EditText userNameEditText;
    @BindView(R.id.password_edit_text)
    EditText passwordEditText;
    @BindView(R.id.login_button)
    ImageButton loginButton;
    @Inject
    LoginPresenter mPresenter;

    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";
    private Observable<CharSequence> usernameChangeObservable;
    private Observable<CharSequence> passwordChangeObservable;

    private Subscription subscription = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setUpToolbar();
        initDepedencyInjection();
        initObservables();

    }

    private void initObservables() {
        usernameChangeObservable = RxTextView.textChanges(userNameEditText).skip(1);
        passwordChangeObservable = RxTextView.textChanges(passwordEditText).skip(1);
        combineObservables();
    }

    private void combineObservables() {
        subscription = Observable.combineLatest(usernameChangeObservable, passwordChangeObservable,
                new Func2<CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence, CharSequence charSequence2) {
                        boolean usernameValid = userNameEditText.getText().toString() != null &&
                                userNameEditText.getText().toString().length() > 0;
                        if (!usernameValid){
                            userNameEditText.setError(getString(R.string.username_error));
                        }

                        boolean passwordValid = passwordEditText.getText().toString() != null;

                        boolean passwordLengthValid = passwordEditText.getText().toString().length() > 4;
                        if (!passwordValid) {
                            passwordEditText.setError(getString(R.string.password_error));
                        } else if (!passwordLengthValid) {
                            passwordEditText.setError(getString(R.string.password_length_error));
                        }

                        return usernameValid && passwordValid && passwordLengthValid;

                    }
                })
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean loginVaid) {
                        if (loginVaid) {
                            loginButton.setEnabled(true);
                        } else {
                            loginButton.setEnabled(false);
                        }
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.bindView(this);
    }

    private void setUpToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.login));
    }

    private void initDepedencyInjection(){
        DaggerAppDataComponent.builder()
                .appComponent(((AppPartnerTestApp)getApplicationContext()).getAppComponent())
                .build()
                .inject(this);
    }

    @OnClick(R.id.login_button)
    public void loginClick(){
        login();
    }

    @Override
    public void login() {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.username = userNameEditText.getText().toString();
        loginInfo.password = passwordEditText.getText().toString();
        mPresenter.login(loginInfo);
    }

    @Override
    public void displayLoginSuccess(LoginData loginData) {
        Toast.makeText(LoginActivity.this, loginData.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayLoginFailure(LoginData loginData) {
        Toast.makeText(LoginActivity.this, loginData.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String username = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if ( username != null && username.length()> 0){
            outState.putString(USERNAME, username);
        }
        if ( password != null && password.length() > 0) {
            outState.putString(PASSWORD, password);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.getString(USERNAME) != null) {
            userNameEditText.setText(savedInstanceState.getString(USERNAME));
        }
        if(savedInstanceState.getString(PASSWORD) != null) {
            passwordEditText.setText(savedInstanceState.getString(PASSWORD));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.unBindView();
    }
}
