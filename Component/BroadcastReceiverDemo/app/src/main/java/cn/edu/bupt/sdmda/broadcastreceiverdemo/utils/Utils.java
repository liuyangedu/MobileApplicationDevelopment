package cn.edu.bupt.sdmda.broadcastreceiverdemo.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

import androidx.core.app.NotificationCompat;

import cn.edu.bupt.sdmda.broadcastreceiverdemo.R;

public class Utils {

    public static final int NOTIFICATION_ID = 1;
    public static final String CHANNEL_ID = "01";
    public static final String CHANNEL_NAME = "channel 01";
    public static final String CHANNEL_DESC = "This is my channel";
    public static final int CHANNEL_IMPORTANCE = NotificationManager.IMPORTANCE_HIGH;

    public static void createNotification(Context context, String msg, String title) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, CHANNEL_IMPORTANCE);
            mChannel.setDescription(CHANNEL_DESC);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(msg);


        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public static String readSMS(Intent intent) {
        Bundle bundle = intent.getExtras();
        Object[] pdusObj = (Object[]) bundle.get("pdus");
        String format = bundle.getString("format");
        String msg = "";
        for (int i = 0; i < pdusObj.length; i++) {
            SmsMessage currentMessage;
            if (Build.VERSION.SDK_INT >= 23) {
                currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);
            } else {
                currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
            }
            msg = currentMessage.getDisplayMessageBody();

        }
        return msg;
    }
}
