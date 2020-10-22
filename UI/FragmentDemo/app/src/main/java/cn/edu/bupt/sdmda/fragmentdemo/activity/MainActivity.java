package cn.edu.bupt.sdmda.fragmentdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.bupt.sdmda.fragmentdemo.R;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    Button btn_rep, btn_has;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    void initView() {
        // Jump to Activity to show changing fragment by ``replace''
        btn_rep = findViewById(R.id.btn_rep_frag);
        // Jump to Activity to show changing fragment by ``hide and show''
        btn_has = findViewById(R.id.btn_hide_show_frag);

        btn_rep.setOnClickListener(this);
        btn_has.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_rep_frag:
                intent = new Intent(this, ReplaceFragmentActivity.class);
                break;
            case R.id.btn_hide_show_frag:
                intent = new Intent(this, HideAndShowFragmentActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}