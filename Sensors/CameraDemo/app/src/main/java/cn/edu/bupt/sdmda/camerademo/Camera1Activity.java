package cn.edu.bupt.sdmda.camerademo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Camera1Activity extends AppCompatActivity implements View.OnClickListener {

    SurfaceView sv;
    SurfaceHolder holder;
    ImageView iv;
    Button btnFlash, btnCapture, btn;
    ArrayList<Integer> cameraInfos;
    int currentCameraId = 0;
    Camera currentCam;
    ArrayList<String> ALL_FLASH_MODE = new ArrayList<>(Arrays.asList(
            Camera.Parameters.FLASH_MODE_AUTO,
            Camera.Parameters.FLASH_MODE_OFF,
            Camera.Parameters.FLASH_MODE_ON));
    int currentFlashIdx;

    Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            currentCam.takePicture(null, null, new Camera.PictureCallback() {
                @Override
                public void onPictureTaken(byte[] data, Camera camera) {
                    final Bitmap resource = BitmapFactory.decodeByteArray(data, 0, data.length);
                    if (resource == null) {
                        Toast.makeText(Camera1Activity.this, "failed!", Toast.LENGTH_SHORT).show();
                    }
                    final Matrix matrix = new Matrix();
                    matrix.setRotate(cameraInfos.get(currentCameraId));
                    final Bitmap bitmap = Bitmap.createBitmap(resource, 0, 0, resource.getWidth(), resource.getHeight(), matrix, true);
                    if (bitmap != null && iv != null && iv.getVisibility() == View.GONE) {
                        currentCam.stopPreview();
                        iv.setVisibility(View.VISIBLE);
                        sv.setVisibility(View.GONE);
                        Toast.makeText(Camera1Activity.this, "capture!", Toast.LENGTH_SHORT).show();
                        iv.setImageBitmap(bitmap);
                    }
                }
            });
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera1);
        initView();
        getCameraInfo();
    }

    void initView() {
        sv = findViewById(R.id.surfaceView);
        holder = sv.getHolder();
        iv = findViewById(R.id.imageView);
        btnFlash = findViewById(R.id.btn_flash);
        btnCapture = findViewById(R.id.btn_capture);
        btn = findViewById(R.id.btn_facing);


        btn.setOnClickListener(this);
        btnCapture.setOnClickListener(this);
        btnFlash.setOnClickListener(this);

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                openCamera();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                closeCamera();
            }
        });
    }

    void getCameraInfo() {
        cameraInfos = new ArrayList<>();
        int n = Camera.getNumberOfCameras();
        for (int i = 0; i < n; ++i) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraInfos.add(info.orientation);
            }
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraInfos.add(info.orientation);
            }
        }
    }

    void openCamera() {
        currentCam = Camera.open(currentCameraId);
        currentCam.setDisplayOrientation(90);
        if (currentCam != null) {
            if (iv.getVisibility() != View.GONE) {
                iv.setVisibility(View.GONE);
            }
            if (sv.getVisibility() != View.VISIBLE) {
                sv.setVisibility(View.VISIBLE);
            }
            Camera.Parameters parameters = currentCam.getParameters();
            try {
                currentCam.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentFlashIdx = ALL_FLASH_MODE.indexOf(parameters.getFlashMode());
            btnFlash.setText(parameters.getFlashMode());
            btnCapture.setText("Capture");
            currentCam.setParameters(parameters);
            try {
                currentCam.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentCam.startPreview();
        }
    }

    void closeCamera() {
        currentCam.release();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_flash:
                Camera.Parameters parameters = currentCam.getParameters();
                currentFlashIdx++;
                if (currentFlashIdx >= ALL_FLASH_MODE.size())
                    currentFlashIdx = 0;
                parameters.setFlashMode(ALL_FLASH_MODE.get(currentFlashIdx));
                currentCam.setParameters(parameters);
                btnFlash.setText(ALL_FLASH_MODE.get(currentFlashIdx));
                break;
            case R.id.btn_facing:
                closeCamera();
                currentCameraId++;
                if (currentCameraId >= cameraInfos.size())
                    currentCameraId = 0;
                openCamera();
                break;
            case R.id.btn_capture:
                if (btnCapture.getText() == "Capture") {
                    currentCam.autoFocus(autoFocusCallback);
                    btnCapture.setText("Preview");
                } else {
                    openCamera();
                }

                break;
        }
    }
}