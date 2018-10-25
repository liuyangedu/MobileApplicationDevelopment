package cn.edu.bupt.sdmda.commoncontrols;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    Button btnT, btnAN, btnAS, btnAM,  btnN, btn9;

    final int NOTIFICATION_ID = 1;
    final String CHANNEL_ID = "01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_toast:
                Toast.makeText(this, getResources().getString(R.string.toast_demo),
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_alertdialog_normal:
                createNormalAlertDialog();
                break;
            case R.id.btn_alertdialog_single:
                createSingleAlertDialog();
                break;
            case R.id.btn_alertdialog_multi:
                createMultiAlertDialog();
                break;
            case R.id.btn_notification:
                createNotification();
                break;
            case R.id.btn_ninepatch:
                Intent intent = new Intent(this, NinePatchAct.class);
                startActivity(intent);
                break;
        }
    }

    void initView(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setLogo(android.R.drawable.btn_star);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setTitle(R.string.title_of_toolbar);
        toolbar.setSubtitle(R.string.subtitle_of_toolbar);

        setSupportActionBar(toolbar);

        btnT = findViewById(R.id.btn_toast);
        btnAN= findViewById(R.id.btn_alertdialog_normal);
        btnAS= findViewById(R.id.btn_alertdialog_single);
        btnAM= findViewById(R.id.btn_alertdialog_multi);
        btnN = findViewById(R.id.btn_notification);
        btn9 = findViewById(R.id.btn_ninepatch);


        btnT.setOnClickListener(this);
        btnAN.setOnClickListener(this);
        btnAS.setOnClickListener(this);
        btnAM.setOnClickListener(this);
        btnN.setOnClickListener(this);
        btn9.setOnClickListener(this);

        registerForContextMenu(btnT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()){
            case R.id.btn_toast:
                getMenuInflater().inflate(R.menu.ctx_toast, menu);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.ctx_hh:
                Toast.makeText(this, getResources().getString(R.string.ctxmenu_hh), Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.op_add:
                break;
            case R.id.op_del:
                Toast.makeText(this, "menu del clicked", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.op_search:
                SearchView searchView = (SearchView) item.getActionView();
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        Toast.makeText(MainActivity.this,
                                "searching: " + s,
                                Toast.LENGTH_SHORT).show();
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        return false;
                    }
                });
                break;
        }
        return true;
    }

    void createNormalAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.title_normal_alertdialog));
        builder.setPositiveButton(getResources().getString(R.string.pos_btn),
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,
                        String.format( getResources().getString(R.string.clicked), which),
                        Toast.LENGTH_SHORT).show();
            }

        });
        builder.setNeutralButton("neu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,
                        String.format( getResources().getString(R.string.clicked), which),
                        Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("neg", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,
                        String.format( getResources().getString(R.string.clicked), which),
                        Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    void createSingleAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.title_single_alertdialog));
        builder.setItems(R.array.single_alertdialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,
                        String.format( getResources().getString(R.string.clicked), which),
                        Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    void createMultiAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.title_multi_alertdialog));
        final boolean[] defaults = new boolean[getResources().getStringArray(R.array.multi_alertdialog).length];
        for(int i=0;i<defaults.length; ++i){
            if(0==i)    defaults[i] = true;
            else        defaults[i] = false;
        }
        builder.setMultiChoiceItems(R.array.multi_alertdialog, defaults, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(MainActivity.this,
                        String.format( getResources().getString(R.string.clicked), which),
                        Toast.LENGTH_SHORT).show();
                defaults[which] = isChecked;
                int cnt = 0;
                for(boolean b : defaults){
                    if(b)   ++cnt;
                    if(cnt>3){
                        dialog.cancel();
                        break;
                    }
                }
            }
        });
        builder.create().show();
    }

    void createNotification(){

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            String channel_name = "channel 01";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, channel_name, importance);
            mChannel.setDescription(Description);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getResources().getString(R.string.title_notification))
                .setContentText(getResources().getString(R.string.content_notification));

        Intent resultIntent = new Intent(this, FromNotificationAct.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pi);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
