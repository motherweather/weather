package com.answerofgod.weather;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.DigitalClock;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class BkUtils {
	/** 뷰페이저 아이템 종류 */

	// static listWeatherView Bklistadapter1;
	// static listWeatherView Bklistadapter2;
	static int flag = 0;
	public static final int TYPE_TEXTVIEW = 0, TYPE_SCROLLVIEW = 1,
			TYPE_SCROLLVIEW2 = 2, TYPE_SCROLLVIEW3 = 3, TYPE_SCROLLVIEW4 = 4,
			TYPE_SCROLLVIEW5 = 5, TYPE_SCROLLVIEW6 = 6, TYPE_SCROLLVIEW7 = 7,
			TYPE_SCROLLVIEW8 = 8, TYPE_SCROLLVIEW9 = 9;

	// public listWeatherView listadapter2;
	// public listWeatherView listadapter3;
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
		// showWeather layout=null;
		/*
		 * if(flag==1) { // Toast.makeText(getApplicationContext(),
		 * "aaaaa",Toast.LENGTH_SHORT).show(); LinearLayout
		 * topLL=(LinearLayout)findViewById(R.id.page_mark); TextView tv=new
		 * TextView(con.this); tv.setLayoutParams(new
		 * LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
		 * LayoutParams.WRAP_CONTENT)); tv.setText("aa"); topLL.addView(tv);
		 * 
		 * 
		 * }
		 */

		/*
		 * LayoutInflater inflater=(LayoutInflater)
		 * con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 * //inflater.inflate(R.layout.other2,null); LinearLayout
		 * yll=(LinearLayout)inflater.inflate(R.layout.other2,null); //TextView
		 * ytv=(TextView)findViewById(R.id.d) TextView aa=new TextView(con);
		 * aa.setText("aaaa");
		 */// 더럽게 안되는 부분

		if (type == TYPE_TEXTVIEW) // 첫 번째 리스트뷰
		{

			LinearLayout ll = new LinearLayout(con);
			ll.setOrientation(LinearLayout.VERTICAL);
			ll.addView(getTextView(con));
			ll.addView(getLinearLayout(con));
			ll.addView(getlistView(con));

			return ll;
		} else if (type == TYPE_SCROLLVIEW) {
			LinearLayout ll = new LinearLayout(con);
			ll.setOrientation(LinearLayout.VERTICAL);
			ll.addView(getTextView(con));
			ll.addView(getLinearLayout(con));
			ll.addView(getlistView(con));

			return ll;
		} else if (type == TYPE_SCROLLVIEW2) {
			LinearLayout ll = new LinearLayout(con);
			ll.setOrientation(LinearLayout.VERTICAL);
			ll.addView(getTextView(con));
			ll.addView(getLinearLayout(con));
			ll.addView(getlistView2(con));

			return ll;
		} else if (type == TYPE_SCROLLVIEW3) {
			LinearLayout ll = new LinearLayout(con);
			ll.setOrientation(LinearLayout.VERTICAL);
			ll.addView(getTextView(con));
			ll.addView(getLinearLayout(con));
			ll.addView(getlistView3(con));

			return ll;
		}

		else if (type == TYPE_SCROLLVIEW4)
			return getlistView4(con);

		else if (type == TYPE_SCROLLVIEW5)
			return getlistView5(con);

		else if (type == TYPE_SCROLLVIEW6)
			return getlistView6(con);

		else if (type == TYPE_SCROLLVIEW7)
			return getlistView7(con);

		else if (type == TYPE_SCROLLVIEW8)
			return getlistView8(con);

		else
			return getlistView(con);

	}

	/**
	 * 텍스트 뷰를 생성하여 반환한다
	 * 
	 * @param con
	 *            - 뷰 생성에 사용할 Context
	 * @return 텍스트 뷰
	 */

	private static TextView getTextView(Context con) {
		TextView tv = new TextView(con);
		tv.setText("TextView Item"); // 글자지정
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24); // 글자 크기 24sp

		tv.setTextColor(Color.rgb(0, 0, 0)); // 글자 색상은 배경과 다른 색으로
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

		// GridView gv=(GridView)findViewById(R.id.gridView1);
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
	// private Activity activity(Context con){

	// }

	public static ListView getlistView(Context con) {
		// listadapter=new listWeatherView(getBaseContext()); //리스트뷰를 만들어주자
		// gridView1.setAdapter(listadapter); //어댑터와 리스트뷰를 연결
		// listadapter=new listWeatherView(con); //리스트뷰를 만들어주자
		// MainActivity Yoon2=new MainActivity();
		// Bklistadapter2=Yoon2.listadapter;
		// MainActivity.listadapter3=MainActivity.listadapter;
		// getScrollView2(con);
		// getTextView(con);

		ListView gv = new ListView(con);
		gv.setScrollingCacheEnabled(false);
		gv.setAnimationCacheEnabled(false);
		gv.setBackgroundColor(Color.argb(80, 255, 255, 255));

		gv.setAdapter(MainActivity.listadapter);
		// gv.setBackgroundColor(#000000);
		// String strColor = "#000000";
		// gv.setTextColor(Color.parseColor("#000000"));
		// gv.setTextSize(16);

		// gv.setTextColor(#000000);

		return gv;
	}

	public static ListView getlistView2(Context con) {
		// listadapter=new listWeatherView(getBaseContext()); //리스트뷰를 만들어주자
		// gridView1.setAdapter(listadapter); //어댑터와 리스트뷰를 연결
		// listadapter=new listWeatherView(con); //리스트뷰를 만들어주자

		// MainActivity Yoon=new MainActivity();
		// Bklistadapter2=Yoon.listadapter;
		// getScrollView2(con);
		// getTextView(con);
		ListView gv = new ListView(con);
		gv.setScrollingCacheEnabled(false);
		gv.setAnimationCacheEnabled(false);
		gv.setBackgroundColor(Color.argb(80, 255, 255, 255));

		gv.setAdapter(MainActivity.listadapter2);
		// gv.setBackgroundColor(#000000);
		// String strColor = "#000000";
		// gv.setTextColor(Color.parseColor("#000000"));
		// gv.setTextSize(16);

		// gv.setTextColor(#000000);

		return gv;
	}

	public static ListView getlistView3(Context con) {
		// listadapter=new listWeatherView(getBaseContext()); //리스트뷰를 만들어주자
		// gridView1.setAdapter(listadapter); //어댑터와 리스트뷰를 연결
		// listadapter=new listWeatherView(con); //리스트뷰를 만들어주자

		// MainActivity Yoon=new MainActivity();
		// Bklistadapter2=Yoon.listadapter;
		// getScrollView2(con);
		// getTextView(con);
		ListView gv = new ListView(con);
		gv.setScrollingCacheEnabled(false);
		gv.setAnimationCacheEnabled(false);
		gv.setBackgroundColor(Color.argb(80, 255, 255, 255));

		gv.setAdapter(MainActivity.listadapter3);
		// gv.setBackgroundColor(#000000);
		// String strColor = "#000000";
		// gv.setTextColor(Color.parseColor("#000000"));
		// gv.setTextSize(16);

		// gv.setTextColor(#000000);

		return gv;
	}

	public static ListView getlistView4(Context con) {
		// listadapter=new listWeatherView(getBaseContext()); //리스트뷰를 만들어주자
		// gridView1.setAdapter(listadapter); //어댑터와 리스트뷰를 연결
		// listadapter=new listWeatherView(con); //리스트뷰를 만들어주자

		// MainActivity Yoon=new MainActivity();
		// Bklistadapter2=Yoon.listadapter;
		// getScrollView2(con);
		// getTextView(con);
		ListView gv = new ListView(con);
		gv.setScrollingCacheEnabled(false);
		gv.setAnimationCacheEnabled(false);
		gv.setBackgroundColor(Color.argb(80, 255, 255, 255));

		gv.setAdapter(MainActivity.listadapter4);
		// gv.setBackgroundColor(#000000);
		// String strColor = "#000000";
		// gv.setTextColor(Color.parseColor("#000000"));
		// gv.setTextSize(16);

		// gv.setTextColor(#000000);

		return gv;
	}

	public static ListView getlistView5(Context con) {

		ListView gv = new ListView(con);
		gv.setScrollingCacheEnabled(false);
		gv.setAnimationCacheEnabled(false);
		gv.setBackgroundColor(Color.argb(80, 255, 255, 255));

		gv.setAdapter(MainActivity.listadapter5);

		return gv;
	}

	public static ListView getlistView6(Context con) {

		ListView gv = new ListView(con);
		gv.setScrollingCacheEnabled(false);
		gv.setAnimationCacheEnabled(false);
		gv.setBackgroundColor(Color.argb(80, 255, 255, 255));

		gv.setAdapter(MainActivity.listadapter6);

		return gv;
	}

	public static ListView getlistView7(Context con) {

		ListView gv = new ListView(con);
		gv.setScrollingCacheEnabled(false);
		gv.setAnimationCacheEnabled(false);
		gv.setBackgroundColor(Color.argb(80, 255, 255, 255));

		gv.setAdapter(MainActivity.listadapter7);

		return gv;
	}

	public static ListView getlistView8(Context con) {

		ListView gv = new ListView(con);
		gv.setScrollingCacheEnabled(false);
		gv.setAnimationCacheEnabled(false);
		gv.setBackgroundColor(Color.argb(80, 255, 255, 255));

		gv.setAdapter(MainActivity.listadapter8);

		return gv;
	}

	private static DigitalClock getDigitalClock(Context con) {
		return new DigitalClock(con);
	}

	private static AnalogClock getAnalogClock(Context con) {
		AnalogClock clock = new AnalogClock(con);
		return clock;
	}

	private static LinearLayout getLinearLayout(Context con) {
		LinearLayout LL = new LinearLayout(con);
		return LL;
	}

}
