package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private SharedPreferences sharedPreferences;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        webView.addJavascriptInterface(new WebViewInterface(), "Android");
        webView.loadUrl("file:///android_asset/homev2.html");
        WebView.setWebContentsDebuggingEnabled(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
    }

    private class JavaScriptInterface {
        @JavascriptInterface
        public void setLoggedInState(boolean isLoggedIn) {
            // Lưu giá trị isLoggedIn vào SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", isLoggedIn);
            editor.apply();
        }

        @JavascriptInterface
        public boolean getLoggedInState() {
            // Lấy giá trị isLoggedIn từ SharedPreferences
            return sharedPreferences.getBoolean("isLoggedIn", false);
        }

        // Các phương thức khác (nếu có)
    }
    // Phương thức để chuyển hướng đến trang apps.html
    public void loadAppsPage() {
        // chạy cùng luồng @@
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("file:///android_asset/homev2.html");
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

