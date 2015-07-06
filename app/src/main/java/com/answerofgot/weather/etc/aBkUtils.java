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
	/** �������� ������ ���� */
	
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
	 * �� �������� �߰��� �並 �����Ͽ� ��ȯ�Ѵ�
	 * 
	 * @param type
	 *            - �߰��� �� Ÿ��
	 * @param con
	 *            - �� ������ ����� Context
	 * @return �� ��ü
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
	 * �ؽ�Ʈ �並 �����Ͽ� ��ȯ�Ѵ�
	 * 
	 * @param con
	 *            - �� ������ ����� Context
	 * @return �ؽ�Ʈ ��
	 */

	private static TextView getTextView(Context con) {
		int color = (int) (Math.random() * 256);
		TextView tv = new TextView(con);
		tv.setBackgroundColor(Color.rgb(color, color, color)); // ���� ����
		tv.setText("TextView Item"); // ��������
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24); // ���� ũ�� 24sp

		color = 255 - color;
		tv.setTextColor(Color.rgb(color, color, color)); // ���� ������ ���� �ٸ� ������
		tv.setGravity(Gravity.CENTER);
		return tv;
	}

	/**
	 * �������� �䰡 �ִ� ��ũ�� �並 ��ȯ�Ѵ�
	 * 
	 * @param con
	 *            - �� ������ ����� Context
	 * @return ��ũ�� ��
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
	 * ����Ʈ �並 �����Ͽ� ��ȯ�Ѵ�
	 * 
	 * @param con
	 *            - �� ������ ����� Context
	 * @return ����Ʈ ��
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
		//listadapter=new listWeatherView(getBaseContext());	//����Ʈ�並 ���������
	//	gridView1.setAdapter(listadapter);					//����Ϳ� ����Ʈ�並 ����
		//listadapter=new listWeatherView(con);	//����Ʈ�並 ���������
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
		//listadapter=new listWeatherView(getBaseContext());	//����Ʈ�並 ���������
	//	gridView1.setAdapter(listadapter);					//����Ϳ� ����Ʈ�並 ����
		//listadapter=new listWeatherView(con);	//����Ʈ�並 ���������
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
