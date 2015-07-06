package com.answerofgod.weather;

import android.graphics.drawable.Drawable;

public class iconTextItem {
	private Drawable mIcon;
	private String mData;
	
	
	public iconTextItem(Drawable icon,String obj){
		mIcon=icon;
		mData=obj;
	}


	
	public String getData(){
		return mData;
	}
	
	public void setData(String obj){
		mData=obj;
	}

	public void setIcon(Drawable icon){
		mIcon=icon;
	}
	
	public Drawable getIcon(){
		return mIcon;
	}
}