package cn.edu.bupt.sdmda.customview.act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import cn.edu.bupt.sdmda.customview.views.MyView;

public class MyViewActivity extends AppCompatActivity {

    MyView mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setContentView(mv);
    }

    void initView(){
        mv = new MyView(this);
        mv.setOnTouchListener(new View.OnTouchListener() {
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
