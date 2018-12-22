package cn.edu.bupt.sdmda.broadcastreceiverdemo.act;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.edu.bupt.sdmda.broadcastreceiverdemo.R;

public class LocalActivity extends AppCompatActivity {

    Button btn;
    TextView tv;
    LocalBroadcastManager lbm;
    LocalReceiver receiver;

    public static final String KEY = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        initView();
        initReceiver();
    }

    void initView() {
        btn = findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyThread thread = new MyThread();
                thread.start();
            }
        });
        tv = findViewById(R.id.tv_info);
    }

    void initReceiver() {
        lbm = LocalBroadcastManager.getInstance(this);
        receiver = new LocalReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SendActivity.DEMO_ACTION);
        registerReceiver(receiver, intentFilter);
        lbm.registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        lbm.unregisterReceiver(receiver);
        super.onDestroy();
    }

    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int val = intent.getExtras().getInt(KEY, -1);
            tv.setText(val + "");
        }
    }

    class MyThread extends Thread {
        final int N = 20;
        final int SLEEP_MS = 1000;
        @Override
        public void run() {
            for (int i = N; i > 0; --i) {
                // constuct a message and send it to looper
                Intent intent = new Intent();
                intent.setAction(SendActivity.DEMO_ACTION);
                intent.putExtra(KEY, i);
                lbm.sendBroadcast(intent);

                try {
                    Thread.sleep(SLEEP_MS);
                } catch (InterruptedException exp) {
                    exp.printStackTrace();
                }
            }
        }
    }
}
