package com.answerofgod.weather;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * ������ ������ �ѷ��ִ� ���Ͼ� ���̾ƿ�
 * weather.xml�� inflater�ߴ�
 * 
 *@author Ans
 */
public class showWeather extends LinearLayout {

	TextView Tdate;
	TextView Ttime;
	TextView Ttemp;
	TextView Twind;
	TextView Thum;
	TextView Tweather;
	ImageView Iweather;
	
	
	public showWeather(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	public showWeather(Context context) {
		super(context);
		init(context);
		
	}
	
	public void setDate(String data) {
		Tdate.setText(data+" ");			//��¥
		// TODO Auto-generated method stub

	}
	public void setTime(String data) {
		Ttime.setText(data+"�� ");			//�ð�
		// TODO Auto-generated method stub

	}
	public void setTemp(String data) {
		Ttemp.setText(data+"�� ");			//�µ�
		// TODO Auto-generated method stub

	}
	public void setWind(String data) {
		Twind.setText(data+"ǳ ");			//�ٶ�
		// TODO Auto-generated method stub

	}
	public void setHum(String data) {
		Thum.setText(data+"% ");			//����
		// TODO Auto-generated method stub

	}
	public void setWeather(String data) {
		Tweather.setText(data);				//����
											//�̰��� ����������
		if(data.toString().equals("����")){
			Iweather.setImageResource(R.drawable.clear);
		}else if(data.toString().equals("�帲")){
			Iweather.setImageResource(R.drawable.cloudy);
		}else if(data.toString().equals("���� ����")){
			Iweather.setImageResource(R.drawable.mostly_cloudly);
		}else if(data.toString().equals("���� ����")){
			Iweather.setImageResource(R.drawable.partly_cloudy);
		}else if(data.toString().equals("��")){
			Iweather.setImageResource(R.drawable.snow);
		}else if(data.toString().equals("��")){
			Iweather.setImageResource(R.drawable.rain);
		}else if(data.toString().equals("��/��")){
			Iweather.setImageResource(R.drawable.snow_rain);
		}else{
			Iweather.setImageResource(R.drawable.ic_launcher);
		}if(data.toString()==null){
			Iweather.setImageResource(R.drawable.ic_launcher);
		}
		

	}
	
	public void init(Context context){
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.weather,this,true);
		
		 Tdate=(TextView)findViewById(R.id.Tdate);
		 Ttime=(TextView)findViewById(R.id.Ttime);
		 Ttemp=(TextView)findViewById(R.id.Ttemp);
		 Twind=(TextView)findViewById(R.id.Twind);
		 Thum=(TextView)findViewById(R.id.Thum);
		 Tweather=(TextView)findViewById(R.id.Tweather);
		 Iweather=(ImageView) findViewById(R.id.Iweather);
		
	}

}
