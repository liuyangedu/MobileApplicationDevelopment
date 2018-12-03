package cn.edu.bupt.sdmda.customview.act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.edu.bupt.sdmda.customview.views.MyGLSurfaceView;

public class MyGLSurfaceViewActivity extends AppCompatActivity {

    MyGLSurfaceView mglsfv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setContentView(mglsfv);
    }

    void initView() {
        mglsfv = new MyGLSurfaceView(this);
    }
}
