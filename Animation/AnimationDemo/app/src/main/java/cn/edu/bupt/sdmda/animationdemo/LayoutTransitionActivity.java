package cn.edu.bupt.sdmda.animationdemo;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.GridLayoutAnimationController;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;

public class LayoutTransitionActivity extends AppCompatActivity
        implements CompoundButton.OnCheckedChangeListener {

    private ViewGroup viewGroup;
    private GridLayout gridLayout;
    private int cnt;
    private LayoutTransition transition;

    private CheckBox cbAppear, cbChangeAppear, cbDisAppear, cbChangeDisAppear;
    private Button btnAdd;
    private Context context;

    private ObjectAnimator oa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_transition);
        context = this;
        addGridView();
        initView();
        initAnimator();
        setTransition();
    }

    void addGridView() {
        viewGroup = findViewById(R.id.id_container);

        gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(4);
        viewGroup.addView(gridLayout);

        transition = new LayoutTransition();
        gridLayout.setLayoutTransition(transition);

    }

    void initView() {
        cbAppear = findViewById(R.id.id_appear);
        cbChangeAppear = findViewById(R.id.id_change_appear);
        cbDisAppear = findViewById(R.id.id_disappear);
        cbChangeDisAppear = findViewById(R.id.id_change_disappear);

        cbAppear.setOnCheckedChangeListener(this);
        cbChangeAppear.setOnCheckedChangeListener(this);
        cbDisAppear.setOnCheckedChangeListener(this);
        cbChangeDisAppear.setOnCheckedChangeListener(this);

        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(clickListener);
    }

    void initAnimator() {
        PropertyValuesHolder pvhx = PropertyValuesHolder.ofFloat("scaleX", 0, 1);
        PropertyValuesHolder pvhy = PropertyValuesHolder.ofFloat("scaleY", 0, 1);
        PropertyValuesHolder pvhrx = PropertyValuesHolder.ofFloat("rotationX", 0, 90, 0);
        oa = ObjectAnimator.ofPropertyValuesHolder((Object) null, pvhx, pvhy, pvhrx);
    }

    void setTransition() {
        transition = new LayoutTransition();
        transition.setAnimator(LayoutTransition.APPEARING,
                (cbAppear.isChecked() ? oa : null));
        transition.setAnimator(LayoutTransition.CHANGE_APPEARING,
                (cbChangeAppear.isChecked() ? transition
                        .getAnimator(LayoutTransition.CHANGE_APPEARING)
                        : null));
        transition.setAnimator(LayoutTransition.DISAPPEARING,
                (cbDisAppear.isChecked() ? transition
                        .getAnimator(LayoutTransition.DISAPPEARING) : null));
//        transition.setAnimator(LayoutTransition.DISAPPEARING,
//                (cbDisAppear.isChecked() ? oa : null));
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,
                (cbChangeDisAppear.isChecked() ? transition
                        .getAnimator(LayoutTransition.CHANGE_DISAPPEARING)
                        : null));
        gridLayout.setLayoutTransition(transition);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Button button = new Button(LayoutTransitionActivity.this);
            button.setText(String.format(getString(R.string.btn_number), ++cnt));
            button.setTextSize(
                    context.getResources().getDimensionPixelSize(R.dimen.text_size)
            );
            gridLayout.addView(button, Math.min(1, gridLayout.getChildCount()));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gridLayout.removeView(button);
                }
            });
        }
    };


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        setTransition();
    }
}
