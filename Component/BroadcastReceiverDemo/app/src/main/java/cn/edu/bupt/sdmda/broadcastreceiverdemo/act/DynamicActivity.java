package cn.edu.bupt.sdmda.broadcastreceiverdemo.act;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.bupt.sdmda.broadcastreceiverdemo.R;
import cn.edu.bupt.sdmda.broadcastreceiverdemo.utils.Utils;

public class DynamicActivity extends AppCompatActivity {

    DynamicReceiver receiver;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        initView();
        initReceiver();

    }

    void initView() {
        tv = findViewById(R.id.tv_code);
    }

    void initReceiver() {
        receiver = new DynamicReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    class DynamicReceiver extends BroadcastReceiver {
        final static String PREFIX = "验证码：";
        final static int CODE_LENGTH = 4;

        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = Utils.readSMS(intent);
            int idx = msg.indexOf(PREFIX);
            tv.setText(msg.substring(idx + PREFIX.length(), idx + PREFIX.length() + CODE_LENGTH));
        }
    }
}
