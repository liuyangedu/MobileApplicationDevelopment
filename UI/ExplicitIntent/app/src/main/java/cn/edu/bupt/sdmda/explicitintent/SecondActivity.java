package cn.edu.bupt.sdmda.explicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String KEY_MSG = "MSG";
    public static final String KEY_DATA = "DATA";
    TextView tv;
    EditText et;
    Button btn;
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
        et = findViewById(R.id.edittext1);
        btn = findViewById(R.id.button1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getCallingActivity()!=null){
                    Intent data = new Intent();
                    data.putExtra(KEY_DATA, et.getText().toString());
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
    }
}