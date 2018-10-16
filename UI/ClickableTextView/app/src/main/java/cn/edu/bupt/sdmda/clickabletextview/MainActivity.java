package cn.edu.bupt.sdmda.clickabletextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String TAG = this.getClass().getSimpleName();

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void fun1(View view) {
        Log.i(TAG, "clicked");
        tv.setText("I am clicked");
    }

    void initView() {
        tv = findViewById(R.id.textview);
    }
}
