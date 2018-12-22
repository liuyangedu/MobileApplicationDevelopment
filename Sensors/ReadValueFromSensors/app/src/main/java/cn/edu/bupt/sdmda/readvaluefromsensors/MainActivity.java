package cn.edu.bupt.sdmda.readvaluefromsensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    TextView tv;
    ArrayAdapter<String> aa;
    SensorManager sensorManager;
    Sensor sensor = null;
    List<Sensor> sensors;
    List<String> strSensors;

    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            tv.setText(floats2String(event.values));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSensors();
        initView();
    }

    void initSensors(){
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        strSensors = new ArrayList<>();
        for (Sensor s : sensors) {
            strSensors.add(s.getName() + "\n" + getTypeFromInt(s.getType()));
        }
    }

    void initView(){
        tv =  findViewById(R.id.values);
        lv =  findViewById(R.id.listview);

        aa = new ArrayAdapter<>(this,
                R.layout.listview_items, strSensors);
        lv.setAdapter(aa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (sensor != null) {
                    sensorManager.unregisterListener(listener, sensor);
                    sensor = null;
                }
                sensor = sensors.get(position);
                sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
    }



    public static String getTypeFromInt(int t) {
        switch (t) {
            case Sensor.TYPE_ACCELEROMETER:
                return "TYPE_ACCELEROMETER";
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                return "TYPE_AMBIENT_TEMPERATURE";
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                return "TYPE_GAME_ROTATION_VECTOR";
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                return "TYPE_GEOMAGNETIC_ROTATION_VECTOR";
            case Sensor.TYPE_GRAVITY:
                return "TYPE_GRAVITY";
            case Sensor.TYPE_GYROSCOPE:
                return "TYPE_GYROSCOPE";
            case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                return "TYPE_GYROSCOPE_UNCALIBRATED";
            case Sensor.TYPE_HEART_RATE:
                return "TYPE_HEART_RATE";
            case Sensor.TYPE_LIGHT:
                return "TYPE_LIGHT";
            case Sensor.TYPE_LINEAR_ACCELERATION:
                return "TYPE_LINEAR_ACCELERATION";
            case Sensor.TYPE_MAGNETIC_FIELD:
                return "TYPE_MAGNETIC_FIELD";
            case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                return "TYPE_MAGNETIC_FIELD_UNCALIBRATED";
            case Sensor.TYPE_ORIENTATION:
                return "TYPE_ORIENTATION";
            case Sensor.TYPE_PRESSURE:
                return "TYPE_PRESSURE";
            case Sensor.TYPE_PROXIMITY:
                return "TYPE_PROXIMITY";
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                return "TYPE_RELATIVE_HUMIDITY";
            case Sensor.TYPE_ROTATION_VECTOR:
                return "TYPE_ROTATION_VECTOR";
            case Sensor.TYPE_SIGNIFICANT_MOTION:
                return "TYPE_SIGNIFICANT_MOTION";
            case Sensor.TYPE_STEP_COUNTER:
                return "TYPE_STEP_COUNTER";
            case Sensor.TYPE_STEP_DETECTOR:
                return "TYPE_STEP_DETECTOR";
            case Sensor.TYPE_TEMPERATURE:
                return "TYPE_TEMPERATURE";
            default:
                return null;
        }
    }


    public static String floats2String(float[] datas) {
        StringBuilder ret = new StringBuilder();
        for (float f : datas) {
            ret.append(f);
            ret.append(" * ");
        }
        return ret.toString();
    }

    @Override
    protected void onDestroy() {
        if (sensor != null) {
            sensorManager.unregisterListener(listener, sensor);
            sensor = null;
        }
        super.onDestroy();
    }
}
