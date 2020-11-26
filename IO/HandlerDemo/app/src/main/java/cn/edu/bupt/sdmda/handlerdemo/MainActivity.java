package cn.edu.bupt.sdmda.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnM, btnR;
    TextView tv;
    static Handler handler;

    final static int WHAT_MSG = 1;
    final static String KEY_MSG = "count";
    final static int N = 20;
    final static int SLEEP_MS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initHandler();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //.....
                //.
            }
        });
    }

    void initView() {
        btnM = findViewById(R.id.btn_message);
        btnR = findViewById(R.id.btn_runnable);
        tv = findViewById(R.id.tv_content);

        btnM.setOnClickListener(this);
        btnR.setOnClickListener(this);

    }


    void initHandler() {
        // init a handler attached to the main looper
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                // handle different message
                switch (msg.what) {
                    case WHAT_MSG:
                        tv.setText(msg.getData().getString(KEY_MSG));
                    break;
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        // start different thread
        switch (v.getId()) {
            case R.id.btn_message:
                MessageThread mt = new MessageThread();
                mt.start();
                break;
            case R.id.btn_runnable:
                RunnableThread rt = new RunnableThread();
                rt.start();
                break;
        }

    }


    class MessageThread extends Thread {
        @Override
        public void run() {
            for (int i = N; i > 0; --i) {
                // constuct a message and send it to looper
                Message msg = Message.obtain();
                msg.what = WHAT_MSG;
                Bundle data = new Bundle();
                data.putString(KEY_MSG,
                        String.format(getResources().getString(R.string.from_msg), i));
                msg.setData(data);
                handler.sendMessage(msg);

                try {
                    Thread.sleep(SLEEP_MS);
                } catch (InterruptedException exp) {
                    exp.printStackTrace();
                }
            }
        }
    }

    class RunnableThread extends Thread {
        @Override
        public void run() {
            for (int i = N; i > 0; --i) {
//                final int fi = i;
//                // construct a Runnable and post it to looper
//                Runnable r = new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                };
//                handler.post(r);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(String.format(
//                                getResources().getString(R.string.from_runnable), fi));
                    }
                });
                try {
                    Thread.sleep(SLEEP_MS);
                } catch (InterruptedException exp) {
                    exp.printStackTrace();
                }
            }
        }
    }
}
