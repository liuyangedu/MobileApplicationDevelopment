package cn.edu.bupt.sdmda.lifecycledemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class BaseActivity extends AppCompatActivity {

    String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }


    @Override
    protected void onStart() {
        Log.i(TAG, "onStart");
        super.onStart();
    }


    @Override
    protected void onStop() {
        Log.i(TAG, "onStop");
        super.onStop();
    }


    @Override
    protected void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
    }


    @Override
    protected void onPause() {
        Log.i(TAG, "onPause");
        super.onPause();
    }
}
