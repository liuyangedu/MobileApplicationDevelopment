package cn.edu.bupt.sdmda.broadcastreceiverdemo.act;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.edu.bupt.sdmda.broadcastreceiverdemo.R;

public class MainActivity extends AppCompatActivity {

    final int SMS_READ_REQUEST_CODE = 0;

    Button btnD, btnS, btnL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // apply permission for receiving and reading sms
        if (!checkPermission(Manifest.permission.READ_SMS) ||
                !checkPermission(Manifest.permission.RECEIVE_SMS)) {
            applyPermission(
                    new String[]{Manifest.permission.READ_SMS,
                            Manifest.permission.RECEIVE_SMS},
                    SMS_READ_REQUEST_CODE);
        }

        initView();
    }

    void initView() {
        btnD = findViewById(R.id.btn_dynamic);
        btnS = findViewById(R.id.btn_send);
        btnL = findViewById(R.id.btn_local);

        btnD.setOnClickListener(clickListener);
        btnS.setOnClickListener(clickListener);
        btnL.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        Intent intent = null;

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_dynamic:
                    intent = new Intent(MainActivity.this, DynamicActivity.class);
                    break;
                case R.id.btn_send:
                    intent = new Intent(MainActivity.this, SendActivity.class);
                    break;
                case R.id.btn_local:
                    intent = new Intent(MainActivity.this, LocalActivity.class);
                    break;
            }
            if (intent != null) {
                startActivity(intent);
            }
        }
    };

    boolean checkPermission(String permission) {
        return ContextCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    void applyPermission(String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(
                this, permissions, requestCode);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case SMS_READ_REQUEST_CODE:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,
                            getResources().getString(R.string.permission_granted),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,
                            getResources().getString(R.string.permission_not_granted),
                            Toast.LENGTH_SHORT).show();
                }
                break;

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
