package cn.edu.bupt.sdmda.animationdemo;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TweenAnimationActivity extends AppCompatActivity {

    LinearLayout root;
    Button btnAlpha, btnTranslation, btnTransMove, btnCancel;
    ImageView iv;
    Context context;
    Animation anim;
    AnimationDrawable animDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_animation);
        context = this;
        initView();
    }

    void initView() {
        root=findViewById(R.id.root_tween_animation);
        btnAlpha = root.findViewById(R.id.btn_alpha);
        btnTranslation = root.findViewById(R.id.btn_translation);
        btnTransMove = root.findViewById(R.id.btn_trans_run);
        btnCancel =root. findViewById(R.id.btn_cancel);
        iv = root.findViewById(R.id.iv);

        btnAlpha.setOnClickListener(clickListener);
        btnTranslation.setOnClickListener(clickListener);
        btnTransMove.setOnClickListener(clickListener);
        btnCancel.setOnClickListener(clickListener);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, getString(R.string.imageview_clicked),
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_alpha:
                    anim = AnimationUtils.loadAnimation(context, R.anim.infantry_alpha);
                    anim.setRepeatMode(Animation.REVERSE);
                    anim.setRepeatCount(Animation.INFINITE);
                    anim.setAnimationListener(animationListener);
                    iv.startAnimation(anim);
                    break;
                case R.id.btn_translation:
                    anim = AnimationUtils.loadAnimation(context, R.anim.infantry_translation);
                    anim.setRepeatMode(Animation.RESTART);
                    anim.setRepeatCount(1);
                    // use different interpolator
                    anim.setInterpolator(new AnticipateInterpolator());
//                    anim.setInterpolator(interpolator);
                    iv.startAnimation(anim);
                    break;
                case R.id.btn_trans_run:
                    anim = AnimationUtils.loadAnimation(context, R.anim.infantry_translation);
                    anim.setAnimationListener(animationListener);
                    iv.setImageResource(R.drawable.infantry);
                    animDrawable = (AnimationDrawable) iv.getDrawable();
                    iv.startAnimation(anim);
                    animDrawable.start();
                    break;
                case R.id.btn_cancel:
                    if (anim != null)
                        anim.cancel();
//                    iv.clearAnimation();
                    if (animDrawable != null)
                        animDrawable.stop();
                    break;

            }
        }
    };

    Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            Toast.makeText(context, getString(R.string.tween_start),
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Toast.makeText(context,  getString(R.string.tween_end),
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            Toast.makeText(context, getString(R.string.tween_repeat),
                    Toast.LENGTH_SHORT).show();
        }
    };


    public static Interpolator interpolator = new Interpolator() {
        @Override
        public float getInterpolation(float input) {
            if (input <= 0.4) return 1.25f * input;
            else if (input > 0.4 && input <= 0.8) return -1.25f * input + 1;
            else return 5 * input - 4;
        }
    };

}

