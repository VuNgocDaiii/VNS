package com.example.myapplication;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(new WebViewInterface(), "Android");
        webView.loadUrl("file:///android_asset/apps.html");
    }

    // Phương thức để chuyển hướng đến trang apps.html
    public void loadAppsPage() {
        // chạy cùng luồng @@
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("file:///android_asset/apps.html");
            }
        });
    }

    public class WebViewInterface {
        @android.webkit.JavascriptInterface
        public void handleLoginSuccess() {
            loadAppsPage();
        }
    }
}

