package cn.edu.bupt.sdmda.sharedpreferencedemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsr, etPwd;
    CheckBox cb;
    Button btn;
    SharedPreferences sp;

    public static String KEY_USR = "username";
    public static String KEY_PWD = "password";
    public static String KEY_DT = "datetime";

    // interval for timeout
    // 1000ms, 60s, 60min, 24hour, 7day
    final Long INTERVAL = 1000 * 60 * 60 * 24 * 7L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        readPreviousAccount();
    }

    void initView() {
        etUsr = findViewById(R.id.usr);
        etPwd = findViewById(R.id.pwd);
        btn = findViewById(R.id.btn_login);
        cb = findViewById(R.id.cb_save);

        btn.setOnClickListener(this);

        sp = getPreferences(MODE_PRIVATE);
    }

    void readPreviousAccount() {
        Long dt = sp.getLong(KEY_DT, 0);
        // read previous account info if not time out
        if(System.currentTimeMillis()-dt<=INTERVAL){
            etUsr.setText(sp.getString(KEY_USR, ""));
            etPwd.setText(sp.getString(KEY_PWD, ""));
            cb.setChecked(true);
        }
    }

    void clear(){
        etUsr.setText("");
        etPwd.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                // check username and password first
                final String USR = "class";
                final String PWD = "428";
                if(etUsr.getText().toString().equals(USR) &&
                        etPwd.getText().toString().equals(PWD)) {
                    if (cb.isChecked()) {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(KEY_USR, etUsr.getText().toString());
                        editor.putString(KEY_PWD, etPwd.getText().toString());
                        editor.putLong(KEY_DT, System.currentTimeMillis());

                        // asynchronously
                        editor.apply();
                        // synchronously
//                        editor.commit();
                    }
                    Toast.makeText(this, getResources().getString(R.string.login_success), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, EmptyActivity.class);
                    // NOTE: for security, we should clear the usr and pwd after login
                    clear();
                    readPreviousAccount();

                    startActivity(intent);
                } else{
                    Toast.makeText(this, getResources().getString(R.string.login_fail), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
