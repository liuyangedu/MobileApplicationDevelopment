package cn.edu.bupt.sdmda.eventdemo.acts;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.bupt.sdmda.eventdemo.R;
import cn.edu.bupt.sdmda.eventdemo.views.DrawTouchView;

public class TouchViewActivity extends AppCompatActivity {

    DrawTouchView dtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_view);
        dtv = findViewById(R.id.draw_touch);
        dtv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dtv.setData(event);
                dtv.invalidate();
                return true;
            }
        });
    }
}