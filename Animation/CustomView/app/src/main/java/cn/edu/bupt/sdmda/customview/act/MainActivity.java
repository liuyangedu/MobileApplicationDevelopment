package cn.edu.bupt.sdmda.customview.act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.edu.bupt.sdmda.customview.R;
import cn.edu.bupt.sdmda.customview.views.MySurfaceView;

public class MainActivity extends AppCompatActivity {

    Button btnV, btnSV, btnGLSV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    void initView() {
        btnV = findViewById(R.id.btn_myview);
        btnSV = findViewById(R.id.btn_mysurfaceview);
        btnGLSV = findViewById(R.id.btn_myglsurfaceview);

        btnV.setOnClickListener(clickListener);
        btnSV.setOnClickListener(clickListener);
        btnGLSV.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.btn_myview:
                    intent = new Intent(MainActivity.this, MyViewActivity.class);
                    break;
                case R.id.btn_mysurfaceview:
                    intent = new Intent(MainActivity.this, MySurfaceViewActivity.class);
                    break;
                case R.id.btn_myglsurfaceview:
                    intent = new Intent(MainActivity.this, MyGLSurfaceViewActivity.class);
                    break;
            }
            if (intent != null)
                startActivity(intent);
        }
    };
}
