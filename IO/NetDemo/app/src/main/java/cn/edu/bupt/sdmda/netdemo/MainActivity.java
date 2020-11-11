package cn.edu.bupt.sdmda.netdemo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUrl;
    Button btnStart, btnView;
    TextView tvStatue, tvContent;

    public static final String KEY_URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    void initView() {
        etUrl = findViewById(R.id.url);
        tvStatue = findViewById(R.id.status);
        tvContent = findViewById(R.id.content);
        btnStart = findViewById(R.id.btn_start);
        btnView = findViewById(R.id.btn_view);


        btnStart.setOnClickListener(this);
        btnView.setOnClickListener(this);

        tvContent.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                tvContent.setText("");
                // Check network state
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    // if network is ready, start a asynctask to connect to the url
                    new NetTask(this, tvContent, tvStatue).execute(etUrl.getText().toString());
                } else {
                    tvStatue.setText(R.string.no_network_tips);
                }
                break;
            case R.id.btn_view:
                Intent intent = new Intent(this, ViewActivity.class);
                intent.putExtra(KEY_URL, etUrl.getText().toString());
                startActivity(intent);
                break;
        }

    }
}
