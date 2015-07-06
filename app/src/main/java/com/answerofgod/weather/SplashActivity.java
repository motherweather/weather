package com.answerofgod.weather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


public class SplashActivity extends Activity{

	

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		Handler hd=new Handler(){
			public void handleMessage(Message msg){
			
				finish();
			}
		};
		hd.sendEmptyMessageDelayed(0, 2500);
	}
}