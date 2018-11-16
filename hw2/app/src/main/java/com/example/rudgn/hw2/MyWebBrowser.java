package com.example.rudgn.hw2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by rudgn on 2018-11-15.
 */

public class MyWebBrowser extends AppCompatActivity {
    Button go;
    EditText uri;
    WebView wv;
    View.OnClickListener cl;
    String weburi;

    class MyWeb extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywebbrowser);

        go = (Button) findViewById(R.id.searchButton);
        uri = (EditText) findViewById(R.id.uri);
        wv = (WebView) findViewById(R.id.wv);
        wv.setWebViewClient(new MyWeb());
        wv.getSettings().setJavaScriptEnabled(true);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_ALL_APPS);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.searchButton:
                        weburi = uri.getText().toString();
                            wv.loadUrl(weburi);
                        break;
                }
            }
        };
        go.setOnClickListener(cl);
    }
}

