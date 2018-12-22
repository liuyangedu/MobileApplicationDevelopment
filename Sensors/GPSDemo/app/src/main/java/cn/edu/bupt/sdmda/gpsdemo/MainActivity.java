package cn.edu.bupt.sdmda.gpsdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnAP, btnCP, btnGPS;
    TextView content;
    Context context;
    LocationManager lm;
    final static int ACCESS_LOCATION_REQUEST_CODE = 0;

    LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            content.setText(extractLocationInfo(location));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!isGPSEnabled(lm)) {
            openGPS();
        }
        if (!checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            applyPermission(android.Manifest.permission.ACCESS_FINE_LOCATION,
                    ACCESS_LOCATION_REQUEST_CODE);
        }
        initView();
    }

    @Override
    protected void onDestroy() {
        lm.removeUpdates(listener);
        super.onDestroy();
    }

    void initView() {
        btnAP = findViewById(R.id.btn_get_all_providers);
        btnCP = findViewById(R.id.btn_get_provider_with_criteria);
        btnGPS = findViewById(R.id.get_information_from_gps);
        content = findViewById(R.id.content);

        btnAP.setOnClickListener(clickListener);
        btnCP.setOnClickListener(clickListener);
        btnGPS.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_get_all_providers:
                    content.setText(joinString(lm.getAllProviders()));
                    break;
                case R.id.btn_get_provider_with_criteria:
                    Criteria c = new Criteria();
                    c.setCostAllowed(false);
                    c.setAltitudeRequired(true);
                    c.setBearingRequired(true);
                    content.setText(joinString(lm.getProviders(c, true)));
                    break;
                case R.id.get_information_from_gps:
                    try {
//                        Location lc = lm.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
//                        Location lc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        Location lc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        content.setText(extractLocationInfo(lc));
                        long minTime = 2000;
                        float minDis = 8;
                        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                                minTime, minDis, listener);
                    } catch (SecurityException e) {
                        Toast.makeText(context, R.string.read_loc_info_error,
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    String joinString(List<String> strs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : strs) {
            stringBuilder.append(s);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private boolean isGPSEnabled(LocationManager lm) {
        return lm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
    }

    private void openGPS() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }

    private String extractLocationInfo(Location location) {
        if (location != null) {
            return String.format(
                    getResources().getString(R.string.loc_info),
                    location.getLongitude(),
                    location.getLatitude(),
                    location.getAltitude(),
                    location.getAltitude(),
                    location.getBearing(),
                    location.getAccuracy()
            );
        } else {
            return "";
        }
    }

    boolean checkPermission(String permission) {
        return ContextCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    void applyPermission(String permission, int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                (Activity) context, permission)) {
            Toast.makeText(context, "explanation", Toast.LENGTH_SHORT).show();

        }
        ActivityCompat.requestPermissions(
                (Activity) context, new String[]{permission}, requestCode);

    }
}
