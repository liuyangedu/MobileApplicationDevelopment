package cn.edu.bupt.sdmda.eventdemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;


public class MyEditText1 extends AppCompatEditText {
    final String TAG = getClass().getSimpleName() + ":" + getId();

    public MyEditText1(@NonNull Context context) {
        super(context);
    }

    public MyEditText1(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText1(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
}
