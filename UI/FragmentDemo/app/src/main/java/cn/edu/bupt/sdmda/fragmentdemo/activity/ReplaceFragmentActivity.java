package cn.edu.bupt.sdmda.fragmentdemo.activity;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import cn.edu.bupt.sdmda.fragmentdemo.R;
import cn.edu.bupt.sdmda.fragmentdemo.fragment.ETFragment;
import cn.edu.bupt.sdmda.fragmentdemo.fragment.ListFragment;
import cn.edu.bupt.sdmda.fragmentdemo.fragment.TVFragment;

public class ReplaceFragmentActivity extends AppCompatActivity
        implements IChangeFragment {

    FrameLayout left, right;
    FragmentManager fm;
    boolean isLandscape;
    @IdRes int layoutContainer;

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
        if (right != null) {
            isLandscape = true;
            layoutContainer = R.id.right_container;
        }
        else {
            isLandscape = false;
            layoutContainer = R.id.left_container;
        }
    }

    void initFrag(){
        fm = getSupportFragmentManager();

        ListFragment lf = ListFragment.newInstance();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.left_container, lf);

        if (isLandscape) {
            TVFragment tf = TVFragment.newInstance("Start fom ReplaceFragmentActivity!");
            ft.replace(R.id.right_container, tf);
        }
        ft.commit();
    }

    @Override
    public void changeTVFragment(String str) {

        Fragment frag = fm.findFragmentById(layoutContainer);
        if(frag instanceof TVFragment) return;
        TVFragment tf = TVFragment.newInstance(str);
        fm.beginTransaction().replace(layoutContainer, tf)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void changeETFragment(String str) {
        Fragment frag = fm.findFragmentById(layoutContainer);
        if(frag instanceof ETFragment) return;
        ETFragment ef = ETFragment.newInstance(str);
        fm.beginTransaction().replace(layoutContainer, ef)
                .addToBackStack(null)
                .commit();
    }
}