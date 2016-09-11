package com.apppartner.androidprogrammertest.mvp.presenters;

import com.apppartner.androidprogrammertest.mvp.views.AnimationView;
import com.apppartner.androidprogrammertest.mvp.views.Views;

/**
 * Created by JH on 9/7/16.
 */
public class AnimationPresenter implements Presenter {

    private AnimationView mView;

    @Override
    public void bindView(Views view) {
        mView = (AnimationView)view;
    }

    @Override
    public void unBindView() {
        mView = null;
    }
}
