package com.apppartner.androidprogrammertest.views.activities;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;

import com.apppartner.androidprogrammertest.R;
import com.apppartner.androidprogrammertest.mvp.views.AnimationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AnimationActivity extends ActionBarActivity implements AnimationView
{
    @BindView(R.id.fade_anim_button)
    ImageButton animateButton;
    @BindView(R.id.app_partner_icon)
    AppPartnerIcon mIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
    }

    @OnClick
    public void animateOnClick(){
        animateIcon();
    }

    @Override
    public void animateIcon() {
        ObjectAnimator fadeAway = ObjectAnimator.ofFloat(mIcon,"alpha",1.0f,0.0f).setDuration(500);
        fadeAway.setInterpolator(new LinearInterpolator());
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(mIcon,"alpha",0f,1f).setDuration(500);
        fadeIn.setInterpolator(new LinearInterpolator());
        AnimatorSet fadeSet = new AnimatorSet();
        fadeSet.play(fadeAway).before(fadeIn);
        fadeSet.start();
    }

}
