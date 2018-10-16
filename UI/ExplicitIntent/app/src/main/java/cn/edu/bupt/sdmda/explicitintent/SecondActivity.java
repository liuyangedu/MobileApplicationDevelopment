package cn.edu.bupt.sdmda.explicitintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    static String KEY_MSG = "MSG";

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        setTextViewFromIntent();
    }

    void initView() {
        tv = findViewById(R.id.tv_sec);
    }

    void setTextViewFromIntent(){
        Intent intent = getIntent();
        if(intent.getExtras()!=null) {
            String msg = intent.getExtras().getString(KEY_MSG);
            tv.setText(msg);
        }
    }
}
