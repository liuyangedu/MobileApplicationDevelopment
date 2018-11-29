package cn.edu.bupt.sdmda.eventdemo.act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.edu.bupt.sdmda.eventdemo.R;
import cn.edu.bupt.sdmda.eventdemo.controls.MyButton;
import cn.edu.bupt.sdmda.eventdemo.controls.MyEditText;
import cn.edu.bupt.sdmda.eventdemo.controls.MyFrameLayout;
import cn.edu.bupt.sdmda.eventdemo.controls.MyLinearLayout;
import cn.edu.bupt.sdmda.eventdemo.controls.MyTextView;

public class PathActivity extends AppCompatActivity {

    String TAG = getClass().getSimpleName();

    MyFrameLayout mfl;
    MyLinearLayout mll;
    MyButton mbtn;
    MyEditText met1, met2;
    MyTextView mtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        initView();

    }

    void initView(){
        mfl = findViewById(R.id.my_framelayout);
        mll = findViewById(R.id.my_linearlayout);
        mbtn = findViewById(R.id.my_button);
        met1 = findViewById(R.id.my_edittext1);
        met2 = findViewById(R.id.my_edittext2);
        mtv = findViewById(R.id.my_textview);

        met1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction()== KeyEvent.ACTION_UP)
                switch (keyCode) {
                    case KeyEvent.KEYCODE_VOLUME_DOWN:
                        Log.i(TAG, "**************UP**************");
//                        return true;
                        break;
                }
                return false;
            }
        });

        met1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "**************ET CLICK**************");
            }
        });

        met1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                return true;
                return false;
            }
        });

        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, getResources().getString(R.string.on_click));
            }
        });

        mtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "**************TV CLICK**************");
            }
        });
        mtv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

       
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
        if(event.getAction()==KeyEvent.ACTION_DOWN && keyCode==KeyEvent.KEYCODE_VOLUME_UP)
            return true;
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
}
