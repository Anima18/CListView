package chris.com.clistview.listview.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import chris.com.clistview.R;
import chris.com.clistview.CListView;
import chris.com.clistview.listview.itemevent.ItemEventAble;

@SuppressLint("all")
public class CABAdapter extends CBaseAdapter {
	private static final String TAG = "UTArrayAdapter";

	private boolean isEditState = false;

	private ListView listView;

	private SparseBooleanArray dataStateArray;

	private SparseArray<CheckBox> checkBoxSparseArray;

	private MenuItem selectStatMenuItem;

	private boolean isSelectAll = true;

	private static final String MENU_NAME_SELECT_ALL = "全选";
	private static final String MENU_NAME_SELECT_ALL_OFF = "全不选";

	public CABAdapter(Context context, int layoutFile, ListView listView) {
		this.mContext = context;
		this.itemLayoutFile = layoutFile;
		this.list = new ArrayList();
		this.listView = listView;
		this.dataStateArray = new SparseBooleanArray();
		this.checkBoxSparseArray = new SparseArray<>();
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

		if(dataStateArray.get(position)) {
			convertView.setBackgroundColor(mContext.getResources().getColor(R.color.backgroundColor));
		}else {
			convertView.setBackground(mContext.getResources().getDrawable(R.drawable.selectable_item_background));
		}

		for(ItemEventAble itemEvent : itemElementEventList) {
			itemEvent.bindEvent(convertView, position);
		}

		Object t = list.get(position);
		for(View v : itemViews) {
			showCheckBox(v, position);
			showItemViewValue(v, t);
		}
		return convertView;
	}

	public AbsListView.MultiChoiceModeListener listener = new AbsListView.MultiChoiceModeListener() {
		private int nr = 0;
		@Override
		public void onItemCheckedStateChanged(ActionMode mode,
											  int position, long id, boolean checked) {
			if (checked) {
				nr++;
			} else {
				nr--;
			}
			if(checked) {
				setSelectedBackground(position);
			}else {
				defaultBackground(position);
			}

			dataStateArray.put(position, checked);

			CheckBox checkBox = checkBoxSparseArray.get(position);
			if(checkBox != null) {
				checkBox.setChecked(checked);
			}

			checkSelectMenuState();
			updateSelectMenuState();
			mode.setTitle(nr + " selected");

		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

			String itemName = item.getTitle().toString();
			if(MENU_NAME_SELECT_ALL.equals(itemName)) {
				selectAll();
			}else if(MENU_NAME_SELECT_ALL_OFF.equals(itemName)) {
				clearAll();
			}else {
				if(itemChoiceListener == null) {
					throw new IllegalArgumentException("listview contextual actionBar menu no register event");
				}

				List<Integer> positionList = new ArrayList<>();
				List dataList = new ArrayList();
				for(int i = 0; i < dataStateArray.size(); i++) {
					if(dataStateArray.get(i)) {
						positionList.add(i);
						dataList.add(list.get(i));
					}
				}

				itemChoiceListener.onClick(itemName, positionList, dataList);
				mode.finish();
			}

			return true;
		}

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			// Inflate the menu for the CAB
			nr = 0;
			/*MenuInflater inflater = mode.getMenuInflater();
			inflater.inflate(R.menu.menu_info, menu);*/

			menu.clear();
			selectStatMenuItem = menu.add(100, 200000, 100, "全选");
			selectStatMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			if(menuNameArray != null) {
				for(int i = 0; i < menuNameArray.length; i++) {
					menu.add(100, 200000 + i, i, menuNameArray[i]).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
				}
			}

			return true;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			// Here you can make any necessary updates to the activity when
			// the CAB is removed. By default, selected items are
			// deselected/unchecked.
			//toolbar.setVisibility(View.VISIBLE);
			isEditState = false;
			clearAll();
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			// Here you can perform updates to the CAB due to
			// an invalidate() request
			isEditState = true;
			dataStateArray.clear();
			for(int i = 0; i < list.size(); i++) {
				dataStateArray.put(i, false);
			}
			return false;
		}
	};

	@Override
	public void setData(List list) {
		super.setData(list);

	}

	private void clearAll() {
		for(int i = 0; i < dataStateArray.size(); i++) {
			if(dataStateArray.get(i)) {
				dataStateArray.put(i, false);
				listView.setItemChecked(i, false);
			}
		}
	}

	private void selectAll() {
		for(int i = 0; i < dataStateArray.size(); i++) {
			if(!dataStateArray.get(i)) {
				dataStateArray.put(i, true);
				listView.setItemChecked(i, true);
			}
		}
	}

	private void checkSelectMenuState() {
		for(int i = 0; i < dataStateArray.size(); i++) {
			if(!dataStateArray.get(i)) {
				isSelectAll = true;
				return;
			}
		}
		isSelectAll = false;
	}

	private void updateSelectMenuState() {
		if(isSelectAll) {
			selectStatMenuItem.setTitle(MENU_NAME_SELECT_ALL);
		}else {
			selectStatMenuItem.setTitle(MENU_NAME_SELECT_ALL_OFF);
		}
	}

	private void setSelectedBackground(int position){
		int visiblePosition = listView.getFirstVisiblePosition();
		View view = listView.getChildAt(position - visiblePosition);
		if(view != null) {
			view.setBackgroundColor(mContext.getResources().getColor(R.color.backgroundColor));
		}
	}

	private void defaultBackground(int position){
		int visiblePosition = listView.getFirstVisiblePosition();
		View view = listView.getChildAt(position - visiblePosition);
		if(view != null) {
			view.setBackground(mContext.getResources().getDrawable(R.drawable.selectable_item_background));
		}
	}

	private void showCheckBox(View view, int position) {
		if((view.getClass() == CheckBox.class || view.getClass() == AppCompatCheckBox.class) && mode.equals(CListView.MODE_MULTICHOICE)) {
			CheckBox checkBox = ((CheckBox)view);
			checkBoxSparseArray.put(position, checkBox);

			if(isEditState) {
				view.setVisibility(View.VISIBLE);
			}else {
				view.setVisibility(View.GONE);
			}
			checkBox.setChecked(dataStateArray.get(position));
		}
	}
}
