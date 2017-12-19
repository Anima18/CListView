package chris.com.clistview.listview.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import chris.com.clistview.listview.itemevent.ItemEventAble;


public class UTArrayAdapter extends UTBaseAdapter {
	private static final String TAG = "UTArrayAdapter";

	public UTArrayAdapter(Context context, int layoutFile) {
		this(context, new ArrayList(), layoutFile);
	}

	private UTArrayAdapter(Context context, List list, int layoutFile) {
		this.mContext = context;
		this.itemLayoutFile = layoutFile;
		this.list = list;
		this.li = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Nullable
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = super.getView(position, convertView, parent);
		List<View> itemViews = (List<View>) convertView.getTag();

		for(ItemEventAble itemEvent : itemElementEventList) {
			itemEvent.bindEvent(convertView, position);
		}

		Object t = list.get(position);
		for(View v : itemViews) {
			showItemViewValue(v, t);
		}

		return convertView;
	}
}
