package com.answerofgot.weather.etc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.answerofgod.weather.R;


public class BkListViewAdapter extends BaseAdapter{
	private Context mContext;
	private LayoutInflater mInflater;
	private String[][] mItems;
	
	public BkListViewAdapter(Context con) {
		mContext = con;
		mInflater = LayoutInflater.from(mContext);
		mItems = new String[2][];
		mItems[0] = mContext.getResources().getStringArray(R.array.avengers);
		mItems[1] = mContext.getResources().getStringArray(R.array.actor);
	}

	@Override
	public int getCount() { return mItems == null ? 0:mItems[0].length * 1; }

	@Override
	public Object getItem(int position) { return position; }

	@Override
	public long getItemId(int position) { return position; }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.list_item, null);
			
			//holder.poster = (ImageView)convertView.findViewById(R.id.poster);
			holder.character = (TextView)convertView.findViewById(R.id.character);
			holder.actor = (TextView)convertView.findViewById(R.id.actor);
			//Toast.makeText(mContext, "DB를 만들어요",Toast.LENGTH_SHORT).show();
			convertView.setTag(holder);
		}
		else
			holder = (ViewHolder)convertView.getTag();
		
		int index = position %4;
		//holder.poster.setImageResource(R.drawable.avengers1 + index);
		holder.character.setText(mItems[0][index]);
		holder.actor.setText(mItems[1][index]);
		return convertView;
	}
	
	private class ViewHolder {
		TextView character, actor;
		//ImageView poster;
	}
}