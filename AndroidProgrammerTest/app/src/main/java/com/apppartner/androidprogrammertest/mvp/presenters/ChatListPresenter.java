package com.apppartner.androidprogrammertest.mvp.presenters;

import com.apppartner.androidprogrammertest.mvp.views.ChatListView;
import com.apppartner.androidprogrammertest.mvp.views.Views;

/**
 * Created by JH on 9/7/16.
 */
public class ChatListPresenter implements Presenter {
    private ChatListView mView;
    @Override
    public void bindView(Views view) {
        mView = (ChatListView)view;
    }
}
