package com.answerofgot.weather.etc;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.DigitalClock;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.answerofgod.weather.MainActivity;
import com.answerofgod.weather.SpinnerActivity;

public class aBkUtils {
	/** 뷰페이저 아이템 종류 */
	
	SpinnerActivity SA1 = new SpinnerActivity();
	
		MainActivity Yoon=new MainActivity();
		
	//Ym1.closeDatabase();
		//data=3;
		 //Yoon.geodata=3;
		
	
	public static final int TYPE_TEXTVIEW = 0, TYPE_SCROLLVIEW = 1,
			TYPE_SCROLLVIEW2  = 2,TYPE_SCROLLVIEW3  = 3,TYPE_SCROLLVIEW4  = 4,TYPE_SCROLLVIEW5  = 5;
	//public listWeatherView listadapter2;
	//public listWeatherView listadapter3;
	/**
	 * 뷰 페이저에 추가할 뷰를 생성하여 반환한다
	 * 
	 * @param type
	 *            - 추가할 뷰 타입
	 * @param con
	 *            - 뷰 생성에 사용할 Context
	 * @return 뷰 객체
	 */
	
	public static View getView(int type, Context con) {
		//showWeather layout=null;
		
		
		
		if(type == TYPE_TEXTVIEW)
			return getlistView(con);
		else if(type == TYPE_SCROLLVIEW)
			return getlistView(con);
		else if(type == TYPE_SCROLLVIEW2)
			return getlistView(con);
		else
			return getlistView2(con);
	}

	/**
	 * 텍스트 뷰를 생성하여 반환한다
	 * 
	 * @param con
	 *            - 뷰 생성에 사용할 Context
	 * @return 텍스트 뷰
	 */

	private static TextView getTextView(Context con) {
		int color = (int) (Math.random() * 256);
		TextView tv = new TextView(con);
		tv.setBackgroundColor(Color.rgb(color, color, color)); // 배경색 지정
		tv.setText("TextView Item"); // 글자지정
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24); // 글자 크기 24sp

		color = 255 - color;
		tv.setTextColor(Color.rgb(color, color, color)); // 글자 색상은 배경과 다른 색으로
		tv.setGravity(Gravity.CENTER);
		return tv;
	}

	/**
	 * 여러개의 뷰가 있는 스크롤 뷰를 반환한다
	 * 
	 * @param con
	 *            - 뷰 생성에 사용할 Context
	 * @return 스크롤 뷰
	 */
	private static ScrollView getScrollView(Context con) {
		ScrollView sv = new ScrollView(con);

		LinearLayout ll = new LinearLayout(con);
		ll.setOrientation(LinearLayout.VERTICAL);
		
	

		sv.addView(ll);
		return sv;
	}
	
	
	private static ScrollView getScrollView2(Context con) {
		ScrollView sv = new ScrollView(con);
		
		//GridView gv=(GridView)findViewById(R.id.gridView1);
		LinearLayout ll = new LinearLayout(con);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.addView(getDigitalClock(con));
		ll.addView(getAnalogClock(con));

		sv.addView(ll);
		return sv;
	}


	/**
	 * 리스트 뷰를 생성하여 반환한다
	 * 
	 * @param con
	 *            - 뷰 생성에 사용할 Context
	 * @return 리스트 뷰
	 */
//private Activity activity(Context con){
	
//}
	
	private static DigitalClock getDigitalClock(Context con) {
		return new DigitalClock(con);
	}
	
	private static AnalogClock getAnalogClock(Context con) {
		AnalogClock clock = new AnalogClock(con);
		return clock;
	}

	public static ListView getlistView(Context con){
		//listadapter=new listWeatherView(getBaseContext());	//리스트뷰를 만들어주자
	//	gridView1.setAdapter(listadapter);					//어댑터와 리스트뷰를 연결
		//listadapter=new listWeatherView(con);	//리스트뷰를 만들어주자
		//MainActivity.listadapter3=MainActivity.listadapter;
		ListView gv=new ListView(con);
		gv.setScrollingCacheEnabled(false);
		gv.setAnimationCacheEnabled(false);
		gv.setBackgroundColor(Color.argb(80, 255, 255, 255));
		
		//gv.setAdapter(MainActivity.listadapter);
		//gv.setBackgroundColor(#000000);
		//String strColor = "#000000";
	//	gv.setTextColor(Color.parseColor("#000000"));
		//gv.setTextSize(16);

		//gv.setTextColor(#000000);
		
		return  gv;
	}
	
	public static ListView getlistView2(Context con){
		//listadapter=new listWeatherView(getBaseContext());	//리스트뷰를 만들어주자
	//	gridView1.setAdapter(listadapter);					//어댑터와 리스트뷰를 연결
		//listadapter=new listWeatherView(con);	//리스트뷰를 만들어주자
		ListView gv=new ListView(con);
		gv.setScrollingCacheEnabled(false);
		gv.setAnimationCacheEnabled(false);
		gv.setBackgroundColor(Color.argb(80, 2, 255, 255));
		
	//	gv.setAdapter(MainActivity.listadapter);
		//gv.setBackgroundColor(#000000);
		//String strColor = "#000000";
	//	gv.setTextColor(Color.parseColor("#000000"));
		//gv.setTextSize(16);

		//gv.setTextColor(#000000);
		
		return  gv;
	}

	
	
}
