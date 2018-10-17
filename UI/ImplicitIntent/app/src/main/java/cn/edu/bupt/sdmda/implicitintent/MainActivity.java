package cn.edu.bupt.sdmda.implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String TAG = this.getClass().getSimpleName();

    Button btnD, btnW, btnA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    void initView() {
        btnD = findViewById(R.id.btn_dial);
        btnW = findViewById(R.id.btn_webpage);
        btnA = findViewById(R.id.btn_alipay);

        btnD.setOnClickListener(this);
        btnW.setOnClickListener(this);
        btnA.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dial:
                Dial();
                break;
            case R.id.btn_webpage:
                Surf();
                break;
            case R.id.btn_alipay:
                Alipay();
                break;
        }
    }

    void Dial(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        Uri num = Uri.parse("tel:1234567890");
        intent.setData(num);
        startActivity(intent);
    }

    void Surf(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri web = Uri.parse("http://www.bupt.edu.cn");
        intent.setData(web);

        Intent chooser = Intent.createChooser(intent,
                getResources().getString(R.string.chooser_title));

        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(chooser);
        }
    }

    void Alipay(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri ali = Uri.parse("alipayqr://platformapi/startapp?saId=10000007");
        intent.setData(ali);

        if(intent.resolveActivity(getPackageManager())!=null) {
            startActivity(intent);
        } else{
            Log.e(TAG, getResources().getString(R.string.alipay_notfound));
        }
    }
}
