package cn.edu.bupt.sdmda.fragmentdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class HideShowActivity extends AppCompatActivity implements View.OnClickListener,
        MomenetFragment.OnETChangedListener{

    WXFragment frag_wx;
    MYFragment frag_my;
    MomenetFragment frag_mom;

    Button btn_w, btn_mom, btn_my;
    TextView tv_title;

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frag);
        initView();
    }

    void initView(){
        btn_w = findViewById(R.id.btn_wx);
        btn_mom = findViewById(R.id.btn_moment);
        btn_my = findViewById(R.id.btn_my);
        tv_title = findViewById(R.id.title);
        // diff from replace
        tv_title.setText(tv_title.getText()+"(hideshow)");

        btn_w.setOnClickListener(this);
        btn_mom.setOnClickListener(this);
        btn_my.setOnClickListener(this);




        // init frag
        frag_wx = WXFragment.newInstance();
        frag_mom = MomenetFragment.newInstance();
        frag_my  = MYFragment.newInstance();

        fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.container, frag_wx)
                .add(R.id.container, frag_mom)
                .add(R.id.container, frag_my)
                .hide(frag_mom)
                .hide(frag_my)
                .commit();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_wx:
                hideAllFragment(fm);
                fm.beginTransaction()
                        .show(frag_wx)
                        .commit();
                break;
            case R.id.btn_moment:
                hideAllFragment(fm);
                fm.beginTransaction()
                        .show(frag_mom)
                        .commit();
                break;
            case R.id.btn_my:
                hideAllFragment(fm);
                fm.beginTransaction()
                        .show(frag_my)
                        .commit();
                break;
        }
    }

    void hideAllFragment(FragmentManager fm){
        List<Fragment> frags = fm.getFragments();
        FragmentTransaction ft = fm.beginTransaction();
        for(Fragment f : frags){
            ft.hide(f);
        }
        ft.commit();
    }

    @Override
    public void onETChanged(String txt) {
        tv_title.setText(txt);
    }
}
