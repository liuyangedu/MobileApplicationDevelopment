package cn.edu.bupt.sdmda.netdemo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by liuyang on 12/1/16.
 */
// first String is the input of the task.
// second Integer is the progress of the task
// third String is the result of the task
public class NetTask extends AsyncTask<String, Integer, String> {
    // Weak reference of two textviews
    private WeakReference<TextView> content;
    private WeakReference<TextView> statue;
    private WeakReference<Context> ctx;
    // some const
    private static final int SIZE = 1024;
    private static final int TIMEOUT = 10000;
    // the total length of the content
    private int contentLength;

    // init these weak reference
    NetTask(Context context, TextView c, TextView s) {
        super();
        ctx= new WeakReference<>(context);
        content = new WeakReference<>(c);
        statue = new WeakReference<>(s);
    }


    // main funtion, it will be called in execute()
    @Override
    protected String doInBackground(String... params) {
        try {
            // build the connection
            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIMEOUT);
            conn.setConnectTimeout(TIMEOUT);
            conn.setRequestMethod("GET");
            // connect to the url
            conn.connect();
            // input stream with data
            InputStream is;
            // response code
            int response = conn.getResponseCode();
            switch (response) {
                case HttpURLConnection.HTTP_OK:
                    is = conn.getInputStream();
                    contentLength = conn.getContentLength();

                    // Convert the InputStream into a string
                    byte[] buffer = new byte[SIZE];
                    int count;
                    int cum = 0;
                    final int MAX = SIZE * 10;
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    while ((count = is.read(buffer, 0, SIZE)) != -1) {
                        os.write(buffer, 0, count);
                        cum += count;
                        // tell user the progress
                        publishProgress(cum);
                        if (contentLength == -1 && cum > MAX) break;
                    }
                    return new String(os.toByteArray(), "UTF-8");
                default:
                    return "response:" + response;
            }
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    // this function will be called after the task is finished
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // change the text of TextView
        content.get().setText(s);
        statue.get().setText(R.string.network_success);
    }

    @Override
    protected void onPreExecute() {
        statue.get().setText(ctx.get().getResources().getString(R.string.connecting));
        super.onPreExecute();
    }

    // notify user of the progress
    // will be called in publicProgress
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (contentLength == -1) {
            statue.get().setText(String.format(
                    ctx.get().getResources().getString(R.string.process), values[0]));
        } else {
            statue.get().setText(String.format(
                    ctx.get().getResources().getString(
                            R.string.process_known_all), values[0], contentLength));
        }
    }
}
