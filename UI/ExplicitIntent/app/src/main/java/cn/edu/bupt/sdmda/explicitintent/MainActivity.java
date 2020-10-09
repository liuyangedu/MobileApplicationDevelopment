package cn.edu.bupt.sdmda.explicitintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    final int REQUST_CODE1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    void initView() {
        tv = findViewById(R.id.textview1);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MainActivity.this,
                        SecondActivity.class
                );
                intent.putExtra(SecondActivity.KEY_MSG,
                        getResources().getString(R.string.message));
                // startActivity(intent);
                startActivityForResult(intent, REQUST_CODE1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                String str;
                if (resultCode == RESULT_OK && data.getExtras() != null &&
                        (str = data.getExtras().getString(SecondActivity.KEY_DATA)) != null) {
                    tv.setText(str);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}