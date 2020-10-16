package cn.edu.bupt.sdmda.backstackandlaunchmode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG = getClass().getSimpleName();
    Button btnStandard, btnSingleTop, btnSingleTask;
    Button btnSingleTaskAffinity, btnSingleInstance, btnDialog;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        initView();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        Log.e(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.e(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.e(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.e(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.e(TAG, "onNewIntent");
        super.onNewIntent(intent);
    }

    void initView() {
        btnStandard = findViewById(R.id.btn_standard);
        btnSingleTop = findViewById(R.id.btn_single_top);
        btnSingleTask = findViewById(R.id.btn_single_task);
        btnSingleTaskAffinity = findViewById(R.id.btn_single_task_affinity);
        btnSingleInstance = findViewById(R.id.btn_single_instance);
        btnDialog = findViewById(R.id.btn_dialog);

        btnStandard.setOnClickListener(this);
        btnSingleTop.setOnClickListener(this);
        btnSingleTask.setOnClickListener(this);
        btnSingleTaskAffinity.setOnClickListener(this);
        btnSingleInstance.setOnClickListener(this);
        btnDialog.setOnClickListener(this);

        tv = findViewById(R.id.text_name);
        tv.setText(getInfo());
    }

    String getInfo() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String[] names = this.toString().split("\\.");
        return "Activity: " + names[names.length - 1] + "\n"
                + "task id: " + getTaskId() + "\n"
                + "time: " + df.format(System.currentTimeMillis());
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_standard:
                intent = new Intent(this, StandardActivity.class);
                break;
            case R.id.btn_single_top:
                intent = new Intent(this, SingleTopActivity.class);
                break;
            case R.id.btn_single_task:
                intent = new Intent(this, SingleTaskActivity.class);
                break;
            case R.id.btn_single_task_affinity:
                intent = new Intent(this, SingleTaskAffinityActivity.class);
                break;
            case R.id.btn_single_instance:
                intent = new Intent(this, SingleInstanceActivity.class);
                break;
            case R.id.btn_dialog:
                intent = new Intent(this, DialogActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}