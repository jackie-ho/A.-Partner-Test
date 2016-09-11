package com.apppartner.androidprogrammertest.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;

import com.apppartner.androidprogrammertest.R;
import com.apppartner.androidprogrammertest.mvp.views.HomeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends ActionBarActivity implements HomeView
{

    @BindView(R.id.chat_screen_button)
    ImageButton chatRoomButton;
    @BindView(R.id.login_screen_button)
    ImageButton loginScreenButton;
    @BindView(R.id.animation_screen_button)
    ImageButton animationScreenButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.chat_screen_button)
    public void onChatButtonClicked(){
        navigateToChat();
    }

    @OnClick(R.id.login_screen_button)
    public void onLoginButtonClicked(){
        navigateToLogin();
    }

    @OnClick(R.id.animation_screen_button)
    public void onAnimationButtonClicked(){
        navigateToAnimation();
    }

    @Override
    public void navigateToChat() {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToAnimation() {
        Intent intent = new Intent(this, AnimationActivity.class);
        startActivity(intent);
    }
}
