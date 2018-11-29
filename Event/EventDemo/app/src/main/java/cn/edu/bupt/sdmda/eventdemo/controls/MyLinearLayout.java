package cn.edu.bupt.sdmda.eventdemo.controls;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import cn.edu.bupt.sdmda.eventdemo.R;

public class MyLinearLayout extends LinearLayout {

    String TAG = getClass().getSimpleName();


    public MyLinearLayout(@NonNull Context context) {
        super(context);
    }

    public MyLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i(TAG, String.format(getResources().getString(R.string.dispatch_event), event.toString()));
        boolean ret = super.dispatchKeyEvent(event);
        if(ret)
            Log.i(TAG, String.format(getResources().getString(R.string.event_handled_success), event.toString()));
        else
            Log.i(TAG, String.format(getResources().getString(R.string.event_handled_failed), event.toString()));
        return ret;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i(TAG, String.format(getResources().getString(R.string.on_keydown), event.toString()));
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.i(TAG, String.format(getResources().getString(R.string.on_keyup), event.toString()));
        return super.onKeyUp(keyCode, event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, String.format(getResources().getString(R.string.dispatch_event), ev.toString()));
        boolean ret = super.dispatchTouchEvent(ev);
        if(ret)
            Log.i(TAG, String.format(getResources().getString(R.string.event_handled_success), ev.toString()));
        else
            Log.i(TAG, String.format(getResources().getString(R.string.event_handled_failed), ev.toString()));
        return ret;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, String.format(getResources().getString(R.string.on_touch), event.toString()));
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, String.format(getResources().getString(R.string.intercept_event), ev.toString()));
        return super.onInterceptTouchEvent(ev);
//        return  true;
    }

}
