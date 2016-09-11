package com.apppartner.androidprogrammertest.views.activities;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.Calendar;

/**
 * Created by JH on 9/10/16.
 */
public class AppPartnerIcon extends ImageView implements View.OnTouchListener{

    float dX, dY;
    long startClickTime;
    private static final int MAX_CLICK_DURATION = 180;

    public AppPartnerIcon(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public AppPartnerIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    public AppPartnerIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }

    //Motion events, touch listener
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {

            //on user press down
            case MotionEvent.ACTION_DOWN:

                dX = v.getX() - event.getRawX();
                dY = v.getY() - event.getRawY();
                startClickTime = Calendar.getInstance().getTimeInMillis();
                break;
            // on user lift finger up
            case MotionEvent.ACTION_UP:
                long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if (clickDuration < MAX_CLICK_DURATION){
                } break;

            // on drag motion
            case MotionEvent.ACTION_MOVE:
                v.animate()
                        .x(event.getRawX() + dX)
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;

            default:
                return false;
        }
        return true;
    }
}
