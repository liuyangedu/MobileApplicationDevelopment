package cn.edu.bupt.sdmda.implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = getClass().getSimpleName();

    Button btnD, btnW, btnA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    void initView() {
        btnD = findViewById(R.id.btn_dial);
        btnW = findViewById(R.id.btn_web);
        btnA = findViewById(R.id.btn_alipay);

        btnD.setOnClickListener(this);
        btnW.setOnClickListener(this);
        btnA.setOnClickListener(this);
    }


    boolean checkIntent(Intent intent) {
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(
                intent, PackageManager.MATCH_DEFAULT_ONLY);
        return activities.size() > 0;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        Uri uri;
        switch (v.getId()) {
            case R.id.btn_dial:
                intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                uri = Uri.parse("tel:1234567890");
                intent.setData(uri);
                break;
            case R.id.btn_web:
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                uri = Uri.parse("http://www.bupt.edu.cn");
                intent.setData(uri);
                break;
            case R.id.btn_alipay:
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                uri = Uri.parse("alipayqr://platformapi/startapp?saId=20000123");
                intent.setData(uri);
                break;
        }
        if (intent != null && checkIntent(intent)) {
            startActivity(intent);
        } else {
            Log.e(TAG, getResources().getString(R.string.error_msg));
        }
    }
}