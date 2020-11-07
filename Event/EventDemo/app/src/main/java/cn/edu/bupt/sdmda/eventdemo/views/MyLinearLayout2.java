package cn.edu.bupt.sdmda.eventdemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MyLinearLayout2 extends LinearLayout {
    final String TAG = getClass().getSimpleName() + ":" + getId();

    public MyLinearLayout2(Context context) {
        super(context);
    }

    public MyLinearLayout2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.e(TAG, "before dispatchKeyEvent");
        boolean ret = super.dispatchKeyEvent(event);
        Log.e(TAG, "after dispatchKeyEvent");
        return ret;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e(TAG, "onKeyDown");
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            Log.e(TAG, "onKeyDown:" + keyCode);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.e(TAG, "onKeyUp");
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            Log.e(TAG, "onKeyUp:" + keyCode);
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent");
        return super.onTouchEvent(event);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, "onInterceptTouchEvent" + ev.toString());
        return false;
        // not clickable
        // return true

    }
}
