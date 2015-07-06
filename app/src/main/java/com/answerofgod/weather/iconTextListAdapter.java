package com.answerofgod.weather;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 어댑터 클래스 정의
 * 
 * @author Mike
 *
 */
public class iconTextListAdapter extends BaseAdapter {

	private Context mContext;

	private List<iconTextItem> mItems = new ArrayList<iconTextItem>();

	public iconTextListAdapter(Context context) {
		mContext = context;
	}

	public void addItem(iconTextItem it) {
		mItems.add(it);
	}

	public void setListItems(List<iconTextItem> lit) {
		mItems = lit;
	}

	public int getCount() {
		return mItems.size();
	}

	public Object getItem(int position) {
		return mItems.get(position);
	}

	public boolean areAllItemsSelectable() {
		return false;
	}

/*
	public boolean isSelectable(int position) {
		try {
			return mItems.get(position).isSelectable();
		} catch (IndexOutOfBoundsException ex) {
			return false;
		}
	}
*/
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		iconTextView itemView;
		if (convertView == null) {
			itemView = new iconTextView(mContext, mItems.get(position));
		} else {
			itemView = (iconTextView) convertView;
			
			itemView.setIcon(mItems.get(position).getIcon());
			itemView.setText(mItems.get(position).getData());
			//itemView.setText(1, mItems.get(position).getData(1));
			//itemView.setText(2, mItems.get(position).getData(2));
		}

		return itemView;
	}

}
