package com.answerofgod.weather;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Web extends Activity {
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view);
		
		
		
		WebView web_view = (WebView) findViewById(R.id.webView1);

		String url = getIntent().getExtras().getString("url");

		web_view.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});

		web_view.loadUrl(url);
		web_view.getSettings().setJavaScriptEnabled(true);

	}
}