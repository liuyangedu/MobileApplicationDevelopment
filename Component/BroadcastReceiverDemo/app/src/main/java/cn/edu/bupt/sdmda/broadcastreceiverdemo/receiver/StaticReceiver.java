package cn.edu.bupt.sdmda.broadcastreceiverdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import cn.edu.bupt.sdmda.broadcastreceiverdemo.R;
import cn.edu.bupt.sdmda.broadcastreceiverdemo.utils.Utils;

public class StaticReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, R.string.content_static_notification, Toast.LENGTH_SHORT).show();
        String msg = Utils.readSMS(intent);
        Utils.createNotification(context,
                context.getResources().getString(R.string.title_static_notification),
                msg);
    }

}
