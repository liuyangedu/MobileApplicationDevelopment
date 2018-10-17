package cn.edu.bupt.sdmda.intentforresult;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    static final String KEY_DATA = "DATA";

    EditText et;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    void initView(){
        btn = findViewById(R.id.btn_back);
        et = findViewById(R.id.edittext);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getCallingActivity()==null) return;
                Intent ret = new Intent();
                ret.putExtra(KEY_DATA, et.getText().toString());
                setResult(Activity.RESULT_OK, ret);
                finish();
            }
        });
    }
}
