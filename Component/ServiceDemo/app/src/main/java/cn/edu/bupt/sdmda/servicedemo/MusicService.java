package cn.edu.bupt.sdmda.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {

    final String TAG = getClass().getSimpleName();

    class MyBinder extends Binder {
        void play() {
            player.start();
        }

        void pause() {
            player.pause();
        }
    }

    MediaPlayer player;
    MyBinder binder;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        // TODO: Return the communication channel to the service.
        binder = new MyBinder();
        return binder;
    }


    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.xyy);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        player.release();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    public void play() {
        player.start();
    }

    public void pause() {
        player.pause();
    }

}
