package com.answerofgod.weather;

import android.app.TabActivity;
import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;


public  class TabViewActivity extends TabActivity implements OnTabChangeListener {

	TabHost tabhost;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//startActivity(new Intent(this,SplashActivity.class));	//로딩화면
		setContentView(R.layout.main);
		
		//Resources res=this.getResources();
		 tabhost=this.getTabHost();
		TabHost.TabSpec spec;
		Intent intent=null;
		Intent intent2=null;
		tabhost.setOnTabChangedListener(this);
		
		
		
		
		ImageView tabwidget01=new ImageView(this);
		tabwidget01.setImageResource(R.drawable.img1);		//첫 번째 탭의 이미지 추가
		
		
		ImageView tabwidget02=new ImageView(this);
		tabwidget02.setImageResource(R.drawable.img2);	//두 번째 탭의 이미지 추가
		
		ImageView tabwidget03=new ImageView(this);
		tabwidget03.setImageResource(R.drawable.img3);	//세 번째 탭의 이미지 추가
		
		ImageView tabwidget04=new ImageView(this);
		tabwidget04.setImageResource(R.drawable.img4);	//네 번째 탭의 이미지 추가
		
		tabhost.setOnTabChangedListener(this);
		
		intent=new Intent().setClass(this,MainActivity.class);
		spec=tabhost.newTabSpec("tab1").setIndicator(tabwidget01).setContent(intent);	////탭에 그림 넣을 수 있는 소스인데 왜 안될까?
		tabhost.addTab(spec);
		
		intent=new Intent().setClass(this,MinSun.class);
		spec=tabhost.newTabSpec("tab2").setIndicator(tabwidget02).setContent(intent);	////탭에 그림 넣을 수 있는 소스인데 왜 안될까?
		tabhost.addTab(spec);
		
		intent=new Intent().setClass(this,theboki.class);
		spec=tabhost.newTabSpec("tab3").setIndicator(tabwidget03).setContent(intent);	////탭에 그림 넣을 수 있는 소스인데 왜 안될까?
		tabhost.addTab(spec);
		
		intent2=new Intent().setClass(this,argumentedreality.class);
		intent2.addFlags(intent2.FLAG_ACTIVITY_NO_HISTORY);
		spec=tabhost.newTabSpec("tab4").setIndicator(tabwidget04).setContent(intent2);	////탭에 그림 넣을 수 있는 소스인데 왜 안될까?
		tabhost.addTab(spec);
		
		
		for(int i=0;i<tabhost.getTabWidget().getChildCount();i++){
			tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#696969"));
		}
		
		
		tabhost.setCurrentTab(0);
		tabhost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#C0C0C0"));
		
		
	}

	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		for(int i=0;i<tabhost.getTabWidget().getChildCount();i++){
			tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#696969"));
		}
		tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#C0C0C0"));
		
	}

}
