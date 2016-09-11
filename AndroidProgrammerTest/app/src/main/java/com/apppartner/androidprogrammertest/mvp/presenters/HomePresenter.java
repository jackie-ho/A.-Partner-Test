package com.apppartner.androidprogrammertest.mvp.presenters;

import com.apppartner.androidprogrammertest.mvp.views.HomeView;
import com.apppartner.androidprogrammertest.mvp.views.Views;

/**
 * Created by JH on 9/7/16.
 */
public class HomePresenter implements Presenter {
    private HomeView mView;

    @Override
    public void bindView(Views view) {
        mView = (HomeView)view;
    }

    @Override
    public void unBindView() {

    }
}
