package cn.edu.bupt.sdmda.eventdemo.act;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import cn.edu.bupt.sdmda.eventdemo.R;

public class CustomGestureActivity extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {

    GestureOverlayView golv;
    GestureLibrary library;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_gesture);
        initGesture();
        initView();

    }

    void initView(){
        golv = findViewById(R.id.gestureoverlay);

        golv.addOnGesturePerformedListener(this);
    }

    void initGesture(){
        library = GestureLibraries.fromRawResource(this, R.raw.gestures);
        library.load();
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = library.recognize(gesture);
        double threshold = 5.0;
        if(predictions.get(0).score>=threshold) {
            switch (predictions.get(0).name) {
                case "gou":
                    Toast.makeText(CustomGestureActivity.this,
                            "gou:"+predictions.get(0).score, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
