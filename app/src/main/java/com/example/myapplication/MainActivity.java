package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(new WebViewInterface(), "Android");
        webView.loadUrl("file:///android_asset/index.html");
    }

    // Phương thức để chuyển hướng đến trang apps.html
    public void loadAppsPage() {
        // chạy cùng luồng @@
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("file:///android_asset/index.html");
            }
        });
    }

    public class WebViewInterface {
        @android.webkit.JavascriptInterface
        public void handleLoginSuccess() {
            loadAppsPage();
        }

        @android.webkit.JavascriptInterface
        public void showAlert(String message) {
            Log.d("WebViewInterface", "showAlert called with message: " + message);
            // Show alert dialog here
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage(message)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Dismiss the dialog if needed
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
    }
}

