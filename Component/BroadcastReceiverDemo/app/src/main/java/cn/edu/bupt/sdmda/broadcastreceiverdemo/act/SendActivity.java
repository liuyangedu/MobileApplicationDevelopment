package cn.edu.bupt.sdmda.broadcastreceiverdemo.act;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.bupt.sdmda.broadcastreceiverdemo.R;

public class SendActivity extends AppCompatActivity {

    Button btn;
    Receiver receiver;
    public static final String DEMO_ACTION = "cn.edu.bupt.sdmda.broadcastreceiverdemo.sendbroadcast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        initView();
        initReceiver();
    }

    void initView() {
        btn = findViewById(R.id.btn_send_broadcast);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(DEMO_ACTION);
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    void initReceiver() {
        receiver = new Receiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DEMO_ACTION);
        registerReceiver(receiver, intentFilter);
    }

    class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, R.string.broadcast_received, Toast.LENGTH_SHORT).show();
        }
    }
}
