package cn.edu.bupt.sdmda.fragmentdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReplaceActivity extends AppCompatActivity implements View.OnClickListener,
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
        // diff from hideshow
        tv_title.setText(tv_title.getText()+"(replace)");

        btn_w.setOnClickListener(this);
        btn_mom.setOnClickListener(this);
        btn_my.setOnClickListener(this);




        // init frag
        frag_wx = WXFragment.newInstance();

        fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.container, frag_wx)
                .addToBackStack(null)
                .commit();


    }

    @Override
    public void onClick(View v) {
        Fragment frag;
        switch (v.getId()){
            case R.id.btn_wx:
                frag = fm.findFragmentById(R.id.container);
                if(!(frag instanceof WXFragment)){
                    frag_wx = WXFragment.newInstance();
                    fm.beginTransaction()
                            .replace(R.id.container,frag_wx)
                            .addToBackStack(null)
                            .commit();
                }
                break;
            case R.id.btn_moment:
                frag = fm.findFragmentById(R.id.container);
                if(!(frag instanceof MomenetFragment)){
                    frag_mom = MomenetFragment.newInstance();
                    fm.beginTransaction()
                            .replace(R.id.container,frag_mom)
                            .addToBackStack(null)
                            .commit();
                }
                break;
            case R.id.btn_my:
                frag = fm.findFragmentById(R.id.container);
                if(!(frag instanceof MYFragment)){
                    frag_my = MYFragment.newInstance();
                    fm.beginTransaction()
                            .replace(R.id.container,frag_my)
                            .addToBackStack(null)
                            .commit();
                }
                break;
        }
    }

    @Override
    public void onETChanged(String txt) {
        tv_title.setText(txt);
    }
}
