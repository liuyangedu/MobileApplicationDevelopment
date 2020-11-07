package cn.edu.bupt.sdmda.eventdemo.acts;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.bupt.sdmda.eventdemo.R;
import cn.edu.bupt.sdmda.eventdemo.views.MyEditText1;
import cn.edu.bupt.sdmda.eventdemo.views.MyEditText2;
import cn.edu.bupt.sdmda.eventdemo.views.MyEditText3;
import cn.edu.bupt.sdmda.eventdemo.views.MyEditText4;
import cn.edu.bupt.sdmda.eventdemo.views.MyLinearLayout1;
import cn.edu.bupt.sdmda.eventdemo.views.MyLinearLayout2;

public class EventActivity extends AppCompatActivity {

    final String TAG = getClass().getSimpleName();
    MyLinearLayout1 mll1;
    MyLinearLayout2 mll2;
    MyEditText1 et1;
    MyEditText2 et2;
    MyEditText3 et3;
    MyEditText4 et4;

    View.OnKeyListener keyListenerT = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            Log.e(TAG, "onKey True " + event.toString());
            return true;
        }
    };

    View.OnKeyListener keyListenerF = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            Log.e(TAG, "onKey False " + event.toString());
            return false;
        }
    };

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e(TAG, "onClick");
        }
    };

    View.OnTouchListener touchListenerT = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Log.e(TAG, "onTouch True " + event.toString());
            return true;
        }
    };

    View.OnTouchListener touchListenerF = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Log.e(TAG, "onTouch False " + event.toString());
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
//        setContentView(R.layout.empty);
        initView();
    }

    void initView() {
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        mll2 = findViewById(R.id.lld);

        et2.setOnKeyListener(keyListenerT);
        et3.setOnKeyListener(keyListenerF);
        et4.setOnKeyListener(keyListenerT);

        et1.setOnClickListener(clickListener);

        et2.setOnTouchListener(touchListenerF);
        et2.setOnClickListener(clickListener);
    }

}
