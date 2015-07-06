package com.answerofgod.weather;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class iconTextView extends LinearLayout{
	
	private ImageView mIcon;
	private TextView mText01;
	 
	
	
	public iconTextView(Context context,iconTextItem aItem){
		super(context);
		
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.oms_listitem,this,true);
		
		mIcon=(ImageView)findViewById(R.id.iconItem);
		mIcon.setImageDrawable(aItem.getIcon());
	
		
		mText01=(TextView) findViewById(R.id.dataItem01);
		mText01.setText(aItem.getData());						//왜 안되지?
		
		
	}

	
	public void setText(String data){
		mText01.setText(data);
	}
	
	public void setIcon(Drawable icon){
		mIcon.setImageDrawable(icon);
	}
}