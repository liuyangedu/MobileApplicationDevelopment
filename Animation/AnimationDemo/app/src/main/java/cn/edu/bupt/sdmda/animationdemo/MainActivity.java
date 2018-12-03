package cn.edu.bupt.sdmda.animationdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnDA, btnTA, btnPA, btnLT;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
    }

    void initView() {
        btnDA = findViewById(R.id.btn_drawable_animation);
        btnTA = findViewById(R.id.btn_tween_animation);
        btnPA = findViewById(R.id.btn_property_animation);
        btnLT = findViewById(R.id.btn_layout_transition);

        btnDA.setOnClickListener(clickListener);
        btnTA.setOnClickListener(clickListener);
        btnPA.setOnClickListener(clickListener);
        btnLT.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.btn_drawable_animation:
                    intent = new Intent(context, FrameAnimationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_tween_animation:
                    intent = new Intent(context, TweenAnimationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_property_animation:
                    intent = new Intent(context, PropertyAnimationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_layout_transition:
                    intent = new Intent(context, LayoutTransitionActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
