package cn.edu.bupt.sdmda.activitystartmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    String TAG = this.getClass().getSimpleName();
    Button btnStand, btnSingleTop, btnSingleTask, BtnSingleTopAff, btnSingleIns;
    TextView tv;

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG, "onNewIntent");
        super.onNewIntent(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        initView();
        tv.setText(getTaskInfo());
    }

    String getTaskInfo(){
        return "task id:" + this.getTaskId()+"\t"+this;
    }

    void initView(){
        tv = findViewById(R.id.textview);
        btnStand = findViewById(R.id.btn_standard);
        btnSingleTop = findViewById(R.id.btn_singletop);
        btnSingleTask = findViewById(R.id.btn_singletask);
        BtnSingleTopAff = findViewById(R.id.btn_singletaskaff);
        btnSingleIns = findViewById(R.id.btn_singleinstance);

        btnStand.setOnClickListener(this);
        btnSingleTop.setOnClickListener(this);
        btnSingleTask.setOnClickListener(this);
        BtnSingleTopAff.setOnClickListener(this);
        btnSingleIns.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn_standard:
                intent = new Intent(this, StandardAct.class);
                break;
            case R.id.btn_singletop:
                intent = new Intent(this, SingleTopAct.class);
                break;
            case R.id.btn_singletask:
                intent = new Intent(this, SingleTaskAct.class);
                break;
            case R.id.btn_singletaskaff:
                intent = new Intent(this, SingleTaskAffAct.class);
                break;
            case R.id.btn_singleinstance:
                intent = new Intent(this, SingleInstance.class);
                break;
        }
        if(intent!=null)
            startActivity(intent);
    }
}
