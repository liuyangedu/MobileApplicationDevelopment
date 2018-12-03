package cn.edu.bupt.sdmda.animationdemo;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.Math;

public class PropertyAnimationActivity extends AppCompatActivity {

    Button btnVA, btnOA, btnViewPA, btnMOA, btnCancel;
    ImageView iv;
    Context context;
    LinearLayout root;

    ValueAnimator va;
    ObjectAnimator oa;
    ViewPropertyAnimator vpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        context = this;
        initView();
    }

    void initView() {
        root = findViewById(R.id.root_property_animation);
        btnVA = root.findViewById(R.id.btn_value_animator);
        btnOA = root.findViewById(R.id.btn_object_animator);
        btnViewPA = root.findViewById(R.id.btn_view_property_animator);
        btnMOA = root.findViewById(R.id.btn_multi_object_animator);
        btnCancel = root.findViewById(R.id.btn_cancel);
        iv = root.findViewById(R.id.iv);
        iv.bringToFront();


        btnVA.setOnClickListener(clickListener);
        btnOA.setOnClickListener(clickListener);
        btnViewPA.setOnClickListener(clickListener);
        btnMOA.setOnClickListener(clickListener);
        btnCancel.setOnClickListener(clickListener);


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, getString(R.string.imageview_clicked), Toast.LENGTH_SHORT).show();
            }
        });

    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_value_animator:
                    if (va != null && va.isPaused()) {
                        va.resume();
                    } else {
//                    oa = ValueAnimator.ofFloat(0,0.9f,0.5f,0.9f,0.7f,0.9f,0.8f,0.9f,0.88f,0.9f);
                        // Load from XML
                        va = (ValueAnimator) AnimatorInflater
                                .loadAnimator(context, R.animator.for_value);
                        va.setDuration(10000);
                        va.setInterpolator(TweenAnimationActivity.interpolator);
                        va.addUpdateListener(circularUpdateListener);
                        va.start();
                    }
                    break;
                case R.id.btn_object_animator:
                    if (oa != null && oa.isPaused()) {
                        oa.resume();
                    } else {
//                    oa = ObjectAnimator.ofFloat(iv, "Alpha", 0, 1);
                        oa = (ObjectAnimator) AnimatorInflater
                                .loadAnimator(context, R.animator.for_object);
                        oa.setTarget(iv);
                        oa.setDuration(3000);
                        oa.start();
                    }
                    break;

                case R.id.btn_multi_object_animator:
                    if (oa != null && oa.isPaused()) {
                        oa.resume();
                    } else {
                        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("X", 0, 500);
                        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("Y", 0, 500);
                        oa = ObjectAnimator.ofPropertyValuesHolder(iv, pvhX, pvhY);
                        oa.setDuration(1000);
                        oa.start();
                    }
                    break;
                case R.id.btn_view_property_animator:
                    // ViewPropertyAnimator
                    vpa = iv.animate()
                            .scaleX(2).rotationBy(90).setDuration(2000);
                    vpa.withStartAction(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, getString(R.string.vpa_startaction),
                                    Toast.LENGTH_SHORT).show();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    btnViewPA.setText("Runnable");
                                }
                            });
                        }
                    });
                    vpa.start();
                    break;
                case R.id.btn_cancel:
                    if (va != null)
                        va.pause();
                    if (oa != null)
                        oa.pause();
                    if (vpa != null)
                        vpa.cancel();
                    break;
            }
        }
    };

    ValueAnimator.AnimatorUpdateListener linearUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            int height = root.getHeight();
            int y = (int) ((float) animation.getAnimatedValue() * height);
            iv.setY(y);
        }
    };

    ValueAnimator.AnimatorUpdateListener circularUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            int height = ((ViewGroup) iv.getParent()).getHeight();
            int width =  ((ViewGroup) iv.getParent()).getWidth();

            int R = width / 4;
            int x = (int) (Math.cos((float) animation.getAnimatedValue()) * R + width / 3);
            int y = (int) (Math.sin((float) animation.getAnimatedValue()) * R + height / 3);
            iv.setX(x);
            iv.setY(y);
        }
    };


}
