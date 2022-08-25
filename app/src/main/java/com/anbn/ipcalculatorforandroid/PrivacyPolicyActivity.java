package com.anbn.ipcalculatorforandroid;

import android.content.Intent;
import android.net.Uri;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import javax.security.auth.callback.Callback;

public class PrivacyPolicyActivity extends AppCompatActivity {

    WebView web;

    // нарисуем экран
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        web = findViewById(R.id.webView);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.setWebViewClient(new Callback());
        web.loadUrl("https://aleksandrbutakov.github.io/IPCalculatorForAndroid/");
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }

    // метод нажатия на кнопку
    public void onClickButtonBack(View v) {
        // возврат на предыдущий activity
        onBackPressed();
    }

}
