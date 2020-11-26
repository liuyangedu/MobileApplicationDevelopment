package cn.edu.bupt.sdmda.surfaceviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MySurfaceView mfv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setContentView(mfv);

    }
    void initView(){
        mfv = new MySurfaceView(this);
        mfv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setX(event.getX());
                v.setY(event.getY());
                v.invalidate();
                return true;
            }
        });
    }
}