package cn.edu.bupt.sdmda.netdemo;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ViewActivity extends AppCompatActivity {

    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        initView();
        openUrlFromIntent();
    }

    void initView() {
        wv = findViewById(R.id.webview);
        // this snip of code make new request in webview is handled by itself.
        // OR Android will launch its default browser to open it
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                } else {
                    view.loadUrl(request.toString());
                }
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        // we can change some settings here
//        WebSettings settings = wv.getSettings();
//        settings.setJavaScriptEnabled(true);
    }

    void openUrlFromIntent() {
        Intent intent = getIntent();
        String url = intent.getExtras().getString(MainActivity.KEY_URL, "");
        wv.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && wv.canGoBack()) {
            wv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
