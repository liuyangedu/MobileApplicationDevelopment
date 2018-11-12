package cn.edu.bupt.sdmda.listviewandrecycleview.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.edu.bupt.sdmda.listviewandrecycleview.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAL, btnSL, btnCL, btnRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    void initView(){
        btnAL = findViewById(R.id.btn_array_lv);
        btnSL = findViewById(R.id.btn_simple_lv);
        btnCL = findViewById(R.id.btn_custom_lv);
        btnRV = findViewById(R.id.btn_rv);

        btnAL.setOnClickListener(this);
        btnSL.setOnClickListener(this);
        btnCL.setOnClickListener(this);
        btnRV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn_array_lv:
                intent = new Intent(this, ArrayListViewActivity.class);
                break;
            case R.id.btn_simple_lv:
                intent = new Intent(this, SimpleListViewActivity.class);
                break;
            case R.id.btn_custom_lv:
                intent = new Intent(this, CustomListViewActivity.class);
                break;
            case R.id.btn_rv:
                intent = new Intent(this, RecycleViewActivity.class);
                break;
        }
        if(intent!=null){
            startActivity(intent);
        }
    }
}
