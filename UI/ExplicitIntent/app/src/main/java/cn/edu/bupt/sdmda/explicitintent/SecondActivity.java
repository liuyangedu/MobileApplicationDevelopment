package cn.edu.bupt.sdmda.explicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String KEY_MSG = "MSG";
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();

        Intent intent = getIntent();
        String data;
        if (intent.getExtras()!=null &&
                (data = intent.getExtras().getString(KEY_MSG))!=null) {
            tv.setText(data);
        }
    }

    void initView() {
        tv = findViewById(R.id.textview2);
    }
}