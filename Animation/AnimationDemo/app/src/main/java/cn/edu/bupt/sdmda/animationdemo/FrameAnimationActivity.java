package cn.edu.bupt.sdmda.animationdemo;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FrameAnimationActivity extends AppCompatActivity {

    LinearLayout root;
    Button btnStart, btnStop;
    ImageView iv;
    AnimationDrawable anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);
        initView();
    }

    void initView() {
        root = findViewById(R.id.root_drawable_animation);
        btnStart = root.findViewById(R.id.btn_start);
        btnStop = root.findViewById(R.id.btn_stop);
        iv = root.findViewById(R.id.iv);

        iv.setImageDrawable(getResources().getDrawable(R.drawable.infantry));
        btnStart.setOnClickListener(clickListener);
        btnStop.setOnClickListener(clickListener);

        anim = (AnimationDrawable) iv.getDrawable();

    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_start:
                    anim.start();
                    break;
                case R.id.btn_stop:
                    anim.stop();
                    break;
            }
        }
    };

}
