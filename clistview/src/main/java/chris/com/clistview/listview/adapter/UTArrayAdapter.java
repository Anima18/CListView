package chris.com.clistview.listview.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.ut.xmlparser.inflat.LayoutInflater;
import com.ut.xmlparser.listview.itemevent.ItemEventAble;
import com.ut.xmlparser.util.TM;

import java.util.ArrayList;
import java.util.List;


public class UTArrayAdapter extends UTBaseAdapter {
	private static final String TAG = "UTArrayAdapter";

	public UTArrayAdapter(Context context, String layoutFile) {
		this(context, new ArrayList(), layoutFile);
	}

	private UTArrayAdapter(Context context, List list, String layoutFile) {
		this.mContext = context;
		this.itemLayoutFile = layoutFile;
		this.list = list;
		this.li = new LayoutInflater(mContext);
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
		TM.set();
		convertView = super.getView(position, convertView, parent);

		List<View> itemViews = (List<View>) convertView.getTag();


		for(ItemEventAble itemEvent : itemElementEventList) {
			itemEvent.bindEvent(convertView, position);
		}

		Object t = list.get(position);
		for(View v : itemViews) {
			showItemViewValue(v, t);
		}

		TM.drop();
		return convertView;
	}
}
