package cn.edu.bupt.sdmda.fragmentdemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import java.util.List;

import cn.edu.bupt.sdmda.fragmentdemo.R;
import cn.edu.bupt.sdmda.fragmentdemo.fragment.ETFragment;
import cn.edu.bupt.sdmda.fragmentdemo.fragment.ListFragment;
import cn.edu.bupt.sdmda.fragmentdemo.fragment.TVFragment;

public class HideAndShowFragmentActivity extends AppCompatActivity
        implements IChangeFragment {

    FrameLayout left, right;
    FragmentManager fm;
    boolean isLandscape;
    ListFragment lf;
    ETFragment ef;
    TVFragment tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
        initFrag();
    }

    void initView() {
        left = findViewById(R.id.left_container);
        right = findViewById(R.id.right_container);
        if (right != null) isLandscape = true;
        else isLandscape = false;
    }

    void initFrag() {
        fm = getSupportFragmentManager();

        lf = ListFragment.newInstance();
        ef = ETFragment.newInstance("Start fom HideAndShowFragmentActivity!");
        tf = TVFragment.newInstance("Start fom HideAndShowFragmentActivity!");

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.left_container, lf);
        if (isLandscape) {
            ft.add(R.id.right_container, ef)
                    .add(R.id.right_container, tf)
                    .hide(tf).commit();
        } else {
            ft.add(R.id.left_container, ef)
                    .add(R.id.left_container, tf)
                    .hide(ef)
                    .hide(tf)
                    .commit();
        }
    }

    @Override
    public void changeTVFragment(String str) {
        hideAllFrag();
        FragmentTransaction ft = fm.beginTransaction();
        ft.show(tf);
        if (isLandscape)
            ft.show(lf);
        ft.commit();
        tf.setText(str);
    }

    @Override
    public void changeETFragment(String str) {
        hideAllFrag();
        FragmentTransaction ft = fm.beginTransaction();
        ft.show(ef);
        if (isLandscape)
            ft.show(lf);
        ft.commit();
        ef.setText(str);
    }

    void hideAllFrag() {
        List<Fragment> frags = fm.getFragments();
        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment f : frags) {
            ft.hide(f);
        }
        ft.commit();
    }
}