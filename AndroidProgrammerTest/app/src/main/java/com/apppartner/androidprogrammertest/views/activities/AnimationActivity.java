package com.apppartner.androidprogrammertest.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.apppartner.androidprogrammertest.R;
import com.apppartner.androidprogrammertest.mvp.views.AnimationView;


public class AnimationActivity extends ActionBarActivity implements AnimationView
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void animateIcon() {

    }

    @Override
    public void dragIcon() {

    }
}
