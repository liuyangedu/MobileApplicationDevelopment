package cn.edu.bupt.sdmda.eventdemo.act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.edu.bupt.sdmda.eventdemo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPath, btnGest, btnCGest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    void initView(){
        btnPath = findViewById(R.id.btn_path);
        btnPath.setOnClickListener(this);


        btnGest = findViewById(R.id.btn_gest);
        btnGest.setOnClickListener(this);

        btnCGest = findViewById(R.id.btn_cust_gest);
        btnCGest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn_path:
                intent = new Intent(this, PathActivity.class);
                break;
            case R.id.btn_gest:
                intent = new Intent(this, GestureActivity.class);
                break;
            case R.id.btn_cust_gest:
                intent = new Intent(this, CustomGestureActivity.class);
                break;
        }
        if(intent==null)    return;
        startActivity(intent);
    }

}
