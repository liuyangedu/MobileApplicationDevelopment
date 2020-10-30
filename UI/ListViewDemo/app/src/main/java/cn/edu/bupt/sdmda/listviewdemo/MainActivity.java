package cn.edu.bupt.sdmda.listviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAA, btnSA, btnCBA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    void initView() {
        btnAA = findViewById(R.id.btn_array_adapter);
        btnSA = findViewById(R.id.btn_simple_adapter);
        btnCBA = findViewById(R.id.btn_custom_base_adapter);

        btnAA.setOnClickListener(this);
        btnSA.setOnClickListener(this);
        btnCBA.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_array_adapter:
                intent = new Intent(this, ArrayAdapterActivity.class);
                break;
            case R.id.btn_simple_adapter:
                intent = new Intent(this, SimpleAdapterActivity.class);
                break;
            case R.id.btn_custom_base_adapter:
                intent = new Intent(this, CustomBaseAdapterActivity.class);
                break;
        }
        if (intent != null)
            startActivity(intent);
    }
}