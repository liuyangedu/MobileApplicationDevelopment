package cn.edu.bupt.sdmda.servicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    Button btnPlay, btnPause, btnChange, btnStart, btnStop, btnUnbind, btnBind;
    TextView tv;
    MusicService.MyBinder binder;
    Context context;
    final String TAG = getClass().getSimpleName();

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected");
            binder = (MusicService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };
    boolean isBinded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        doBindService();
        initView();
    }

    @Override
    protected void onDestroy() {
        doUnbindService();
        super.onDestroy();
    }

    void initView() {
        btnPlay = findViewById(R.id.btn_play);
        btnPause = findViewById(R.id.btn_pause);
        btnChange = findViewById(R.id.btn_change);
        btnStart = findViewById(R.id.btn_start_service);
        btnStop = findViewById(R.id.btn_stop_service);
        btnUnbind = findViewById(R.id.btn_unbind_service);
        btnBind = findViewById(R.id.btn_bind_service);
        tv = findViewById(R.id.tv_info);

        tv.setText(this.toString());

        btnPlay.setOnClickListener(clickListener);
        btnPause.setOnClickListener(clickListener);
        btnStart.setOnClickListener(clickListener);
        btnStop.setOnClickListener(clickListener);
        btnUnbind.setOnClickListener(clickListener);
        btnBind.setOnClickListener(clickListener);
    }

    void doBindService() {
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
        isBinded = true;
    }

    void doUnbindService() {
        if (isBinded) {
            unbindService(conn);
            isBinded = false;
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        Intent intent = null;

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_play:
                    if (isBinded) binder.play();
                    break;
                case R.id.btn_pause:
                    if (isBinded) binder.pause();
                    break;
                case R.id.btn_start_service:
                    intent = new Intent(context, MusicService.class);
                    startService(intent);
                    break;
                case R.id.btn_stop_service:
                    intent = new Intent(context, MusicService.class);
                    stopService(intent);
                    break;
                case R.id.btn_unbind_service:
                    doUnbindService();
                    break;
                case R.id.btn_bind_service:
                    doBindService();
                    break;
            }
        }
    };

}
