package cn.edu.bupt.sdmda.filedemo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnReadInternal, btnWriteInternal, btnReadExternalPrivate, btnWriteExternalPrivate;
    Button btnReadExternalPublic, btnWriteExternalPublic;
    EditText content;
    TextView statusBar;

    Context context;

    final String FILENAME = "data.txt";
    final int EXTERNAL_READ_PERMISSION_REQUEST_CODE = 1;
    final int EXTERNAL_WRITE_PERMISSION_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
    }

    void initView() {
        btnReadInternal = findViewById(R.id.read_internal);
        btnWriteInternal = findViewById(R.id.write_internal);
        btnReadExternalPrivate = findViewById(R.id.read_external_private);
        btnWriteExternalPrivate = findViewById(R.id.write_external_private);
        btnReadExternalPublic = findViewById(R.id.read_external_public);
        btnWriteExternalPublic = findViewById(R.id.write_external_public);
        content = findViewById(R.id.content);
        statusBar = findViewById(R.id.statue_bar);

        btnReadInternal.setOnClickListener(this);
        btnWriteInternal.setOnClickListener(this);
        btnReadExternalPrivate.setOnClickListener(this);
        btnWriteExternalPrivate.setOnClickListener(this);
        btnReadExternalPublic.setOnClickListener(this);
        btnWriteExternalPublic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        File file;
        String sRead = getResources().getString(R.string.read_from);
        String sWrite = getResources().getString(R.string.write_to);
        switch (v.getId()) {
            case R.id.read_internal:
                file = new File(getInternalFile(FILENAME));
                content.setText(read(file));
                statusBar.setText(String.format(sRead, file.getAbsolutePath()));
                break;
            case R.id.write_internal:
                file = new File(getInternalFile(FILENAME));
                write(file, content.getText().toString());
                statusBar.setText(String.format(sWrite, file.getAbsolutePath()));
                break;
            case R.id.read_external_private:
                file = new File(getExternalPrivateFile(FILENAME, null));
                content.setText(read(file));
                statusBar.setText(String.format(sRead, file.getAbsolutePath()));
                break;
            case R.id.write_external_private:
                file = new File(getExternalPrivateFile(FILENAME, null));
                write(file, content.getText().toString());
                statusBar.setText(String.format(sWrite, file.getAbsolutePath()));
                break;
            case R.id.read_external_public:
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    file = new File(getExternalPublicFile(FILENAME, Environment.DIRECTORY_DCIM));
                    content.setText(read(file));
                    statusBar.setText(String.format(sRead, file.getAbsolutePath()));
                } else {
                    applyPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                            EXTERNAL_READ_PERMISSION_REQUEST_CODE);
                }

                break;
            case R.id.write_external_public:
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    file = new File(getExternalPublicFile(FILENAME, Environment.DIRECTORY_DCIM));
                    write(file, content.getText().toString());
                    statusBar.setText(String.format(sWrite, file.getAbsolutePath()));
                } else {
                    applyPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            EXTERNAL_WRITE_PERMISSION_REQUEST_CODE);
                }
                break;
        }
    }

    String read(File file) {
        try {
            Scanner in = new Scanner(file);
            StringBuilder stringBuilder = new StringBuilder();
            while (in.hasNext()) {
                stringBuilder.append(in.nextLine() + "\n");
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException exp) {
            Toast.makeText(this,
                    String.format(getResources().getString(R.string.file_not_found),
                            file.getAbsoluteFile()),
                    Toast.LENGTH_SHORT)
                    .show();
            exp.printStackTrace();
        }
        return null;
    }

    void write(File file, String data) {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.write(data);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException exp) {
            Toast.makeText(this,
                    String.format(getResources().getString(R.string.file_not_found),
                            file.getAbsoluteFile()),
                    Toast.LENGTH_SHORT)
                    .show();
            exp.printStackTrace();
        }
    }

    String getInternalFile(String fileName) {
        return getFilesDir().getAbsolutePath() + "/" + fileName;
    }

    String getExternalPrivateFile(String fileName, String type) {
        return getExternalFilesDir(type).getAbsolutePath() + "/" + fileName;
    }


    String getExternalPublicFile(String fileName, String type) {
        return Environment.getExternalStoragePublicDirectory(type).getAbsolutePath()
                + "/" + fileName;
    }

    boolean checkPermission(String permission) {
        return ContextCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    void applyPermission(String permission, int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this, permission)) {
            Toast.makeText(this, "explanation", Toast.LENGTH_SHORT).show();

        }
        ActivityCompat.requestPermissions(
                this, new String[]{permission}, requestCode);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case EXTERNAL_READ_PERMISSION_REQUEST_CODE:
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
            case EXTERNAL_WRITE_PERMISSION_REQUEST_CODE:
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
